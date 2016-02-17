package dao;

import entity.Game;
import entity.GamePlatform;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Анатолий on 14.02.2016.
 */
@Stateless
public class GameService implements GameLocal {

    @PersistenceContext(name = "persistence")
    EntityManager entityManager;


    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<GamePlatform> getGamePlatforms() {
        String jpa = "Select gamePl FROM GamePlatform gamePl";
        return entityManager.createQuery(jpa).getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public GamePlatform getGamePlatformById(int id) {
        String jpa = "Select gamePl FROM GamePlatform gamePl where gamePl.id = :id";
        try {
            return (GamePlatform) entityManager.createQuery(jpa).setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public GamePlatform getGamePlatformByName(String name) {
        String jpa = "Select gamePl FROM GamePlatform gamePl where gamePl.name = :name";
        try {
            return (GamePlatform) entityManager.createQuery(jpa).setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Game> getGamesByGamePlatformId(int id) {
        String jpa = "Select game FROM Game game where game.gamePlatform.id = :id";
        return entityManager.createQuery(jpa).setParameter("id", id).getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Game getGameById(int id) {
        String jpa = "Select game FROM Game game where game.id = :id";
        try {
            return (Game) entityManager.createQuery(jpa).setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Game getGameByName(String name) {
        String jpa = "Select game FROM Game game where game.name = :name";
        try {
            return (Game) entityManager.createQuery(jpa).setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Game> getGames() {
        String jpa = "Select game FROM Game game ";
        return entityManager.createQuery(jpa).getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createGamePlatform(GamePlatform gamePlatform) {
        entityManager.persist(gamePlatform);
        entityManager.flush();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createGame(Game game) {
        entityManager.persist(game);
        entityManager.flush();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateGamePlatform(GamePlatform gamePlatform) {
        entityManager.merge(gamePlatform);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateGame(Game game) {
        entityManager.merge(game);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteGamePlatform(int id) {
        GamePlatform gamePlatform = entityManager.find(GamePlatform.class, id);
        if (gamePlatform != null) {
            List<Game> games = getGamesByGamePlatformId(id);
            for (int i = 0; i < games.size(); i++) {
                entityManager.remove(games.get(i));
            }
            entityManager.remove(gamePlatform);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteGame(int id) {
        Game game = entityManager.find(Game.class, id);
        if (game != null) {
            entityManager.remove(game);
        }
    }
}
