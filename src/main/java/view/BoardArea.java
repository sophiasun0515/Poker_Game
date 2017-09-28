package view;

import javafx.scene.layout.HBox;
import model.Board;
import model.Card;
import javafx.scene.control.Label;
import javafx.geometry.Pos;

/**
 * @author CS1331 TAs
 * @version 1.0
 */
public class BoardArea {

    private HBox pane;

    private Board board;
    private CardView[] cardviews = new CardView[5];
    private Label pot;
    /**
     * Constructor for the board's display
     * @param  board The Board object that contains data associated with the
     * board
     */
    public BoardArea(Board board) {
        this.board = board;
        pane = new HBox(5);
        for (int i = 0; i < 5; i++) {
            cardviews[i] = new CardView();
        }
        pot = new Label("Pot: " + board.getPot());
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(cardviews[0], cardviews[1], cardviews[2],
            cardviews[3], cardviews[4], pot);



    }

    /**
     * Getter for the HBox that all UI elements are on
     * @return the HBox that all Board UI elements are on
     */
    public HBox getPane() {
        return pane;
    }

    /**
     * Updates UI elements
     */
    public void update() {

        int num = board.getNumCards();
        pot.setText("Pot: " + board.getPot());
        Card[] tableCards = board.getCards();

        for (int i = 0; i < tableCards.length; i++) {
            cardviews[i].setCard(tableCards[i]);
        }

        for (int i = 0; i < num; i++) {
            cardviews[i].show();
        }
        for (int i = num; i < 5; i++) {
            cardviews[i].hide();
        }

    }

}
