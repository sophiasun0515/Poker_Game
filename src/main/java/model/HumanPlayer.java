package model;
import java.io.Serializable;

/**
 * @author CS1331 TAs
 * @version 1.0
 */
public class HumanPlayer extends Player implements Serializable {

    /**
     * Constructor for a HumanPlayer
     * @param  name  The Player's name
     * @param  money The starting money for the Player
     */
    public HumanPlayer(String name, int money) {
        super(name, money);
    }
}
