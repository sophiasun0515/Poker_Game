package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;



/**
 * @author CS1331 TAs
 * @version 1.0
 */
public class StartScreen extends StackPane {

    // Path to the image file for the background
    private static final String BACK_LOCATION = "File:./src/main/res"
        + "/poker-game-background.png";
    private int chips = 100;
    private int newDialog() {
        TextInputDialog chipDialog = new TextInputDialog();
        chipDialog.setTitle("Enter Chips");
        chipDialog.setHeaderText("Enter chips to start with");
        chipDialog.setContentText("Please enter a valid number "
            + "range from 1 - 500, or click the cancel and get "
            + "the defult as 100");
        Optional<String> chipResult = chipDialog.showAndWait();
        if (chipResult.isPresent()) {
            try {
                chips = Integer.parseInt(chipResult.get().toString());
                if (chips <= 0 || chips > 500) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException n) {
                newDialog();
            }
        }
        return chips;
    }
    /**
     * StartScreen's constructor
     * @param cont The PokerGame to interact with
     */
    public StartScreen(PokerGame cont) {

        Image backgroundPic = new Image(BACK_LOCATION);
        ImageView backgroundView = new ImageView();
        backgroundView.setImage(backgroundPic);
        backgroundView.fitWidthProperty()
            .bind(cont.getPrimaryStage().widthProperty());
        backgroundView.fitHeightProperty()
            .bind(cont.getPrimaryStage().heightProperty());

        //Add Button for users to start new game
        Button button = new Button("Start New Game");
        button.setTranslateX(-400);
        button.setTranslateY(200);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                TextInputDialog nameDialog = new TextInputDialog();
                nameDialog.setTitle("New Game");
                nameDialog.setHeaderText("Please enter your name");
                Optional<String> result = nameDialog.showAndWait();
                String entered = "";
                if (result.isPresent()) {
                    entered = result.get();
                    newDialog();
                    cont.startGame(entered, chips);
                }
            }
        });



        //Add Button for users to resume old gameScreen
        Button button2 = new Button("Resume Game");
        button2.setTranslateX(-400);
        button2.setTranslateY(150);
        button2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                cont.resumeGame();
                cont.updatesMade();
            }
        });

        this.getChildren().addAll(backgroundView, button, button2);
    }
}
