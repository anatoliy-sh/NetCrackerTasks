package dao;

import entity.Game;
import entity.GamePlatform;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Анатолий on 14.02.2016.
 */
@Local
public interface GameLocal {

    void createGamePlatform(GamePlatform gamePlatform);
    void updateGamePlatform(GamePlatform gamePlatform);
    void deleteGamePlatform(int id);

    GamePlatform getGamePlatformById(int id);
    GamePlatform getGamePlatformByName(String name);
    List<GamePlatform> getGamePlatforms();


    void createGame(Game game);
    void updateGame(Game game);
    void deleteGame(int id);

    List<Game> getGames();
    Game getGameById(int id);
    Game getGameByName(String name);
    List<Game> getGamesByGamePlatformId(int id);


}
