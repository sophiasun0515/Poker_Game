package view;

import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import model.Card;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;


/**
 * @author CS1331 TAs
 * @version 1.0
 */
public class CardView extends StackPane {

    private static final int CARD_HEIGHT = 110;
    private static final int CARD_WIDTH = 67;
    private static final String BACK_LOCATION = "File:./src/main/res/"
        + "playing-card-back.png";
    private static final String FRONT_LOCATION = "File:./src/main/res/"
        + "playing-card-front.png";
    private static final String JACK_LOCATION = "File:./src/main/res/"
        + "J.jpg";
    private static final String QUEEN_LOCATION = "File:./src/main/res/"
        + "Q.jpg";
    private static final String KING_LOCATION = "File:./src/main/res/"
        + "K.jpg";
    private static Image backIm;
    private static Image frontIm;
    private static Image jackIm;
    private static Image queenIm;
    private static Image kingIm;


    // statically loads Images
    static {
        backIm = new Image(BACK_LOCATION);
        frontIm = new Image(FRONT_LOCATION);
        jackIm = new Image(JACK_LOCATION);
        queenIm = new Image(QUEEN_LOCATION);
        kingIm = new Image(KING_LOCATION);
    }

    // the background image of the card
    private ImageView background;
    // the top left label where card value is displayed
    private Label topL;
    // the top left label where card suit is displayed
    private Label topSuit;
    // the bottom right label where card value is displayed
    private Label botR;
    // the bottom right label where card suit is displayed
    private Label botSuit;
    // the middle label where card suit is displayed
    private Label midOne;
    // the bottom middle label where card suit is displayed
    private Label midTwo;
    // the card where card info is found
    private Card card;

    /**
     * Constructor for CardView
     */
    public CardView() {
        this.background = new ImageView(frontIm);
        this.background.setFitHeight(CARD_HEIGHT);
        this.background.setFitWidth(CARD_WIDTH);
        topL = new Label();
        midOne = new Label();
        midTwo = new Label();
        botR = new Label();
        this.getChildren().addAll(background, topL, midOne, midTwo, botR);
    }

    /**
     * Gives the CardView a Card object which contains information on the Card
     * @param c The Card to display
     */
    public void setCard(Card c) {
        card = c;
        topL.setText(c.getCardValue().getStr());
        midOne.setText(c.getSuit().getStr());
        midOne.setFont(new Font(20));
        botR.setText(c.getCardValue().getStr());
        if (midOne.getText().equals("\u2665") | midOne.getText().
            equals("\u2666")) {
            midOne.setTextFill(Color.RED);
            midTwo.setTextFill(Color.RED);
        } else {
            midOne.setTextFill(Color.BLACK);
            midTwo.setTextFill(Color.BLACK);
        }
        topL.setTextFill(midOne.getTextFill());
        botR.setTextFill(midOne.getTextFill());

        if (c.getCardValue().getStr().equals("J")) {
            this.background.setImage(jackIm);
            midTwo.setText(c.getSuit().getStr());
            midTwo.setFont(new Font(20));
            midOne.setTranslateX(15 - CARD_WIDTH / 2);
            midOne.setTranslateY(topL.getTranslateY() + 15);
            midTwo.setTranslateX(CARD_WIDTH / 2 - 15);
            midTwo.setTranslateY(botR.getTranslateY() - 15);

        } else if (c.getCardValue().getStr().equals("Q")) {
            this.background.setImage(queenIm);
            midTwo.setText(c.getSuit().getStr());
            midTwo.setFont(new Font(20));
            midOne.setTranslateX(15 - CARD_WIDTH / 2);
            midOne.setTranslateY(topL.getTranslateY() + 15);
            midTwo.setTranslateX(CARD_WIDTH / 2 - 15);
            midTwo.setTranslateY(botR.getTranslateY() - 15);

        } else if (c.getCardValue().getStr().equals("K")) {
            this.background.setImage(kingIm);
            midTwo.setText(c.getSuit().getStr());
            midTwo.setFont(new Font(20));
            midOne.setTranslateX(15 - CARD_WIDTH / 2);
            midOne.setTranslateY(topL.getTranslateY() + 15);
            midTwo.setTranslateX(CARD_WIDTH / 2 - 15);
            midTwo.setTranslateY(botR.getTranslateY() - 15);
        }
        topL.setTranslateX(15 - CARD_WIDTH / 2);
        topL.setTranslateY(topL.getLayoutBounds().getHeight() / 2 + 5
            - CARD_HEIGHT / 2);
        botR.setTranslateX(CARD_WIDTH / 2 - 15);
        botR.setTranslateY(botR.getLayoutBounds().getHeight() / -2 - 5
            + CARD_HEIGHT / 2);

    }

    /**
     * Shows the front of the Card
     */
    public void show() {

        if (this.background.getImage() == jackIm) {
            this.background.setImage(jackIm);
        } else if (this.background.getImage() == queenIm) {
            this.background.setImage(queenIm);
        } else if (this.background.getImage() == kingIm) {
            this.background.setImage(kingIm);
        } else {
            this.background.setImage(frontIm);
        }
        this.background.setVisible(true);
        topL.setVisible(true);
        botR.setVisible(true);
        midOne.setVisible(true);
        midTwo.setVisible(true);

    }

    /**
     * Makes the card not display at all
     */
    public void hide() {
        this.background.setImage(null);
        topL.setVisible(false);
        botR.setVisible(false);
        midOne.setVisible(false);
        midTwo.setVisible(false);

    }

    /**
     * Shows the back of the card.
     */
    public void hideDetails() {
        this.background.setImage(backIm);
        this.background.setVisible(true);
        topL.setVisible(false);
        botR.setVisible(false);
        midOne.setVisible(false);
        midTwo.setVisible(false);


    }
}
