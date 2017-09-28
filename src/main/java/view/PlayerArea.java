package view;

import javafx.scene.layout.Pane;
import model.Player;
import javafx.scene.control.Label;


/**
 * @author CS1331 TAs
 * @version 1.0
 */
public abstract class PlayerArea {

    private Pane pane;

    private Player player;

    private CardView c1;
    private CardView c2;

    private Label name;
    private Label chips;
    private Label outOfPlayIndicator;
    /**
     * PlayerArea's constructor
     * @param  pane   The Pane where all UI elements will be added. The type of
     * pane is decided by subclasses
     * @param  player The Player who's information will be tracked
     */
    public PlayerArea(Pane pane, Player player) {
        this.pane = pane;
        this.player = player;
        c1 = new CardView();
        c2 = new CardView();
        name = new Label(player.toString());
        chips = new Label("Chips: " + player.getMoney());
        outOfPlayIndicator = new Label("Out of Play");
        outOfPlayIndicator.setVisible(false);
        pane.getChildren().addAll(c1, c2, name, chips,
            outOfPlayIndicator);
    }

    /**
     * Getter for the Pane that contains all of the UI elements.
     * @return The Pane that contains all of the UI elements.
     */
    public Pane playerPane() {
        return pane;
    }

    /**
     * This method is called whenever an update to the UI needs to be made.
     * @param showDetails is true whenever the details of the front of the
     * cards are supposed to be shown false otherwise
     */
    public void update(boolean showDetails) {
        c1.setCard(player.getCard(0));
        c2.setCard(player.getCard(1));

        chips.setText("Chips: " + player.getMoney());

        if (player.getOutOfPlay()) {
            outOfPlayIndicator.setVisible(true);
        } else {
            outOfPlayIndicator.setVisible(false);
        }

        if (c1 != null) {
            if (player.getOutOfPlay()) {
                c1.hide();
            } else if (!showDetails) {
                c1.hideDetails();
            } else {
                c1.show();
            }
        }

        if (c2 != null) {
            if (player.getOutOfPlay()) {
                c2.hide();
            } else if (!showDetails) {
                c2.hideDetails();
            } else {
                c2.show();
            }
        }

    }
}
