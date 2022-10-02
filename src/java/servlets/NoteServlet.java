
package servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Note;

/**
 *
 * @author Kylan Kidd
 */
public class NoteServlet extends HttpServlet {
    Note note = new Note();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        String edit = request.getParameter("edit");

        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        String title = br.readLine();
        String contents = br.readLine();
        br.close();

        note = new Note(title, contents);
        request.setAttribute("note", note);

        if (edit == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp")
                    .forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/editnote.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)));

        pw.println(title);
        pw.println(contents);
        pw.close();
        
        
          note = new Note(title, contents);
            request.setAttribute("note", note);

        getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp")
                .forward(request, response);

    }
}
