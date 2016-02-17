package entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by roman on 30.11.15.
 */
@Entity
@Table(name = "GAME")
public class Game {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "NAME", nullable = false)
    String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GAME_PLATFORM_ID", nullable = false)
    GamePlatform gamePlatform;




    public Game() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GamePlatform getGamePlatform() {
        return gamePlatform;
    }

    public void setGame(GamePlatform gamePlatform) {
        this.gamePlatform = gamePlatform;
    }



}
