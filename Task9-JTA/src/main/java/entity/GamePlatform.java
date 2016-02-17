package entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "GAME_PLATFORM")
public class GamePlatform {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "NAME", nullable = false)
    String name;

    @OneToMany(mappedBy = "gamePlatform",fetch = FetchType.EAGER)
    List<Game> games;




    public GamePlatform() {
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

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }


}
