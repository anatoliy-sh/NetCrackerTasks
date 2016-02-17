import dao.GameLocal;
import dao.GameService;
import entity.Game;
import entity.GamePlatform;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Анатолий on 04.02.2016.
 */
@WebServlet("/game")
public class GameServlet extends HttpServlet {

    @EJB
    GameLocal gameService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getAttribute("gamePlatforms") == null)
            request.setAttribute("gamePlatforms", gameService.getGamePlatforms());
        if (request.getAttribute("games") == null)
            request.setAttribute("games", gameService.getGames());

        request.getRequestDispatcher("games.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("insertGamePlatform") != null) {
            GamePlatform gamePlatform = new GamePlatform();
            gamePlatform.setName(request.getParameter("gamePlatformName"));
            gameService.createGamePlatform(gamePlatform);
        }
        if (request.getParameter("updateGamePlatform") != null) {
            GamePlatform gamePlatform = gameService.getGamePlatformById(Integer.parseInt(request.getParameter("gamePlatformId")));
            gamePlatform.setName(request.getParameter("gamePlatformName"));
            gameService.updateGamePlatform(gamePlatform);
        }
        if(request.getParameter("deleteGamePlatform") != null){
            gameService.deleteGamePlatform(Integer.parseInt(request.getParameter("gameDelPlatformId")));
        }

        if (request.getParameter("insertGame") != null) {
            Game game = new Game();
            game.setName(request.getParameter("gameName"));
            game.setGame(gameService.getGamePlatformById(Integer.parseInt(request.getParameter("gamePlatformIdG"))));
            gameService.createGame(game);
        }
        if (request.getParameter("updateGame") != null) {
            Game game = gameService.getGameById(Integer.parseInt(request.getParameter("gameId")));
            game.setName(request.getParameter("gameName"));
            game.setGame(gameService.getGamePlatformById(Integer.parseInt(request.getParameter("gamePlatformIdG"))));
            gameService.updateGame(game);
        }
        if(request.getParameter("deleteGame") != null){
            gameService.deleteGame(Integer.parseInt(request.getParameter("gameDelId")));
        }
        if(request.getParameter("getGameByName") != null){
            request.setAttribute("game", gameService.getGameByName(request.getParameter("search")));
        }
        if(request.getParameter("getGameById") != null){
            request.setAttribute("game", gameService.getGameById(Integer.parseInt(request.getParameter("search"))));
        }
        if(request.getParameter("getGamePlatformByName") != null){
            request.setAttribute("gamePlatform", gameService.getGamePlatformByName(request.getParameter("search")));
        }
        if(request.getParameter("getGamePlatformById") != null){
            request.setAttribute("gamePlatform", gameService.getGamePlatformById(Integer.parseInt(request.getParameter("search"))));
        }
        doGet(request, response);
    }

}