package model;
import java.io.Serializable;

/**
  * Represents a conservative AI which is an AIPlayer
  * that will always give away the minnimum Bet
  * if it has to
  * @author rsun65
  * @version 1.0
  */
public class AITwo extends AIPlayer implements Serializable {

  /**
   * AITwo's constructor
   * @param  name  The name of the AI
   * @param  money The amount of money the AI starts with
   */
    public AITwo(String name, int money) {
        super(name, money);
    }

    /**
     * A method that return a Turn that the AI wants to take
     * @param  minBet     The minimum the AI can bet
     * @param  maxBet     The maximum the AI can bet
     * @param  boardCards The cards that are on the board
     * @return            The turn that the AI should take
     */
    @Override
    public Turn getTurn(int minBet, int maxBet, Card[] boardCards) {
        if (minBet > (double) getMoney()) {
            return Turn.getFoldTurn();
        } else if (minBet == (double) getMoney()) {
            return Turn.getCallTurn(placeBet(minBet));
        } else {
            int iDontWantToBet = (Integer) (minBet);
            return Turn.getRaiseTurn(placeBet(iDontWantToBet), iDontWantToBet);
        }
    }
}
