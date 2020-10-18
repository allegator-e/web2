import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AreaCheckServlet extends HttpServlet {

    ArrayList<String> points = new ArrayList<>();
    ArrayList<String> rows = new ArrayList<>();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/controller");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try (PrintWriter out = response.getWriter()) {
            long startTime = System.nanoTime();
            boolean flagX = false, flagY = false, flagR = false;
            double x = 0, y = 0, r = 0;
            try {
                x = Double.parseDouble(request.getParameter("x").replace(',', '.').trim());
                if (x < -2 || x > 2) {
                    out.println("X не входит в диапазон</br>");
                } else
                    flagX = true;
            } catch (NumberFormatException e) {
                out.println("X не число</br>");
            }

            try {
                y = Double.parseDouble(request.getParameter("y").replace(',', '.').trim());
                if (y < -3 || y > 5) {
                    out.println("Y не входит в диапазон</br>");
                } else
                    flagY = true;
            } catch (NumberFormatException e) {
                out.println("Y не число</br>");
            }

            try {
                r = Double.parseDouble(request.getParameter("r").replace(',', '.').trim());
                if (r < 2 || r > 5) {
                    out.println("R не входит в диапазон</br>");
                } else
                    flagR = true;
            } catch (NumberFormatException e) {
                out.println("R не число</br>");
            }

            long endTime = System.nanoTime();
            long runtime = (endTime - startTime)/100;

            if (flagX && flagY && flagR) {
                Point point = new Point(x, y, r, runtime);
                points.add(point.getPoint());
                rows.add(point.getTableRow());
                request.getServletContext().setAttribute("point", point.getPoint());
                request.getServletContext().setAttribute("row", point.getTableRow());
                request.getServletContext().setAttribute("points", points);
                request.getServletContext().setAttribute("rows", rows);
                request.getServletContext().getRequestDispatcher("/answer.jsp").forward(request, response);
            } else
                out.println("<p>Incorrect input</p>");
        }
    }
}
