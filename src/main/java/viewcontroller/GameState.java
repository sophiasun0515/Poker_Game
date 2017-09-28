package viewcontroller;
import java.io.Serializable;
/**
 * The enum that represents what state the Poker Game currently is in
 * @author CS1331 TAs
 * @version 1.0
 */
public enum GameState implements Serializable {
    HUMAN_BET, AI_BET, DEALING, DONE
}
