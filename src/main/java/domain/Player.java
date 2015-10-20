package domain;

import javax.persistence.*;

@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "player_name", nullable = false, unique = true)
    private String name;

    @Column(name = "cash")
    private Double cash;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
        this.cash = 100D;
    }


    public String getName() {
        return name;
    }

    public Double getCash() {
        return cash;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public int getId() {
        return id;
    }
}
