package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/games")
public class gameSelection extends HttpServlet {
	@Inject
	public DatabaseService databaseService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		List<Game> gamesD = databaseService.selectGames();
		int count = 0;
		for (Game g : gamesD) {
			System.out.println(g);
			out.print("<a href=\"index.xhtml?path=" + g.getGamePath() + "\"><img src=\"" + g.getGamePicture()
					+ "\"/alt=\"" + g.getGameName() + "  height=\"150\" width=\"150\" \"></a>");
			count++;
			if (count % 2 == 0) {
				out.println("<br>");
			}
		}

		// List<Game> games =new ArrayList();
		// Game game=new Game("npuzzle", "stones",
		// "resources/npuzzle/npuzzle.png");
		// games.add(game);
		// game=new Game("minesweeper", "mines",
		// "resources/minesweeper/minesweeper.png");
		// games.add(game);
		// game=new Game("puzzle", "pictures", "resources/puzzle/picture.png");
		// games.add(game);
		// game=new
		// Game("GuessNumber","greeting.xhtml","resources/number/number.png");
		// games.add(game);
		// count=0;
		// for (Game g : games) {
		// out.print("<a href=\"index.xhtml?path=" + g.getGamePath() + "\"><img
		// src=\"" + g.getGamePicture() + "\"/alt=\""
		// + g.getGameName() + " height=\"150\" width=\"150\" \"></a>");
		// count++;
		// if (count % 2 == 0) {
		// out.println("<br>");
		// }
		// }
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
