package view;

import javafx.scene.layout.BorderPane;
import viewcontroller.PokerGameController;

/**
 * @author CS1331 TAs
 * @version 1.0
 */
public class GameScreen extends BorderPane {

    private HorizontalPlayer topArea;
    private HorizontalPlayer bottomArea;
    private VerticalPlayer leftArea;
    private VerticalPlayer rightArea;
    private BoardArea boardArea;

    /**
     * GameScreen's constructor
     * @param controller The PokerGameController to interact with
     */
    public GameScreen(PokerGameController controller) {
        topArea = new HorizontalPlayer(controller.getTopPlayer());
        bottomArea = new HorizontalPlayer(controller.getBottomPlayer());
        leftArea = new VerticalPlayer(controller.getLeftPlayer());
        rightArea = new VerticalPlayer(controller.getRightPlayer());
        boardArea = new BoardArea(controller.getBoard());
        super.setTop(topArea.playerPane());
        super.setBottom(bottomArea.playerPane());
        super.setLeft(leftArea.playerPane());
        super.setRight(rightArea.playerPane());
        super.setCenter(boardArea.getPane());

    }

    /**
     * This method is called whenever normal updates to the UI need to be made.
     */
    public void updatesMade() {
        topArea.update(false);
        bottomArea.update(true);
        rightArea.update(false);
        leftArea.update(false);
        boardArea.update();
    }

    /**
     * This method is called whenever a round of poker ends
     */
    public void endOfRound() {
        topArea.update(true);
        bottomArea.update(true);
        rightArea.update(true);
        leftArea.update(true);
        boardArea.update();
    }

}
