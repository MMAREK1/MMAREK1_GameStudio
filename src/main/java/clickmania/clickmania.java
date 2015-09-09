package clickmania;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/clickmania")
public class clickmania extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		HttpSession session = req.getSession();
		FieldC fieldC = (FieldC) session.getAttribute("fieldC");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("Clickmania");
		out.println("</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		if (fieldC == null || "new".equals(req.getParameter("move"))) {
			fieldC = new FieldC(10, 10);
			session.setAttribute("fieldC", fieldC);
		} else {
			try {
				int chosenRow = Integer.parseInt(req.getParameter("row"));
				int chosenColumn = Integer.parseInt(req.getParameter("column"));
				int colorValue = fieldC.getColor(chosenRow, chosenColumn).getValue();
				if ((chosenRow != 0 && colorValue == fieldC
						.getColor(chosenRow - 1, chosenColumn).getValue())
						|| (chosenRow != fieldC.getRowCount() - 1 && colorValue == fieldC.getColor(chosenRow + 1, chosenColumn).getValue())
						|| (chosenColumn != 0 && colorValue == fieldC
								.getColor(chosenRow, chosenColumn - 1).getValue())
						|| (chosenColumn != fieldC.getColumnCount() - 1 && colorValue == fieldC.getColor(chosenRow, chosenColumn + 1).getValue())) {
					if (colorValue != 0) {
						fieldC.remove(chosenRow, chosenColumn);
					}
				}
			} catch (Exception e) {
			}
		}
		if (fieldC.endOfGame()) {
			out.println("Out of moves<br>");
			session.setAttribute("fieldC", null);

		}

		for (int row = 0; row < fieldC.getRowCount(); row++) {
			for (int column = 0; column < fieldC.getColumnCount(); column++) {
				Color color = fieldC.getColor(row, column);
				out.print("<a href=\"?row=" + row + "&column=" + column + "\"><img src=\"resources/clickmania/"
						+ color.getValue() + ".png\" alt=\"sdf\"/></a>");
			}
			out.println("<br>");
		}

		out.println("<a href=\"?move=new\">New Game</a>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
