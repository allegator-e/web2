import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AreaCheckServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/controller");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
            Point point = new Point(x, y, r);


            long endTime = System.nanoTime();
            long runtime = (endTime - startTime)/100;
            if (flagX && flagY && flagR) {
                out.println(
                        "<tr class='result_php'>" +
                        "<td>" + point.getX() + "</td>" +
                        "<td>" + point.getY() + "</td>" +
                        "<td>" + point.getR() + "</td>" +
                        "<td>" + point.isInArea() + "</td>" +
                        "<td>" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "</td>" +
                        "<td>" + runtime + "ms" + "</td>" +
                        "</tr>");
            } else
                out.println("<p>Incorrect input</p>");
        }
    }

    public static class Point{

        private double x;
        private double y;
        private double r;
        private boolean isInArea;

        public Point(double x, double y, double r){
            this.x = x;
            this.y = y;
            this.r = r;
            isInArea = setInArea(x,y,r);
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getR() {
            return r;
        }

        public boolean isInArea() {
            return isInArea;
        }

        public boolean setInArea(double x, double y, double r) {
            if (x <= 0 && y >= 0 && y <= 2*x + r) return true;
            if (x >= 0 && y >= 0 && x*x + y*y <= r*r) return true;
            return x <= 0 && y <= 0 && x >= r/2 && y >= r;
        }
    }
}
