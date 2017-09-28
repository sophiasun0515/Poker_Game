package view;

import javafx.scene.layout.HBox;
import viewcontroller.PokerGameController;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

/**
 * @author CS1331 TAs
 * @version 1.0
 */
public class ControlPane extends HBox {

    private PokerGameController cont;
    private Button raise = new Button("Raise");
    private Button call = new Button("Call");
    private Button fold = new Button("Fold");
    private Button restart = new Button("Start New Round");
    private TextField input = new TextField();
    private Button save = new Button("Save and Exit");
    private String musicFile = "src/main/res/"
        + "music.mp3";
    private PokerGame gameView;

    /**
      * The getter method for raise button
      * @return raise button
      */
    public Button getRaise() {
        return raise;
    }

    /**
      * The getter method for call button
      * @return call button
      */
    public Button getCall() {
        return call;
    }

    /**
      * The getter method for fold button
      * @return fold button
      */
    public Button getFold() {
        return fold;
    }

    /**
      * The getter method for restart button
      * @return restart button
      */
    public Button getRestart() {
        return restart;
    }

    /**
      * The getter method for save button
      * @return save button
      */
    public Button getSave() {
        return save;
    }

    /**
     * Constructor for ControlPane
     * @param  cont The PokerGameController to interact with
     */
    public ControlPane(PokerGameController cont) {
        this.cont = cont;
        this.getChildren().addAll(input, raise, call, fold, restart, save);
        restart.setVisible(false);
        this.setAlignment(Pos.CENTER);

    }

    /**
     * Called whenever it becomes the player's turn again
     */
    public void playerTurn() {
        raise.setDisable(false);
        call.setDisable(false);
        fold.setDisable(false);

        raise.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    int entered = Integer.valueOf(input.getText());
                    if (entered != 0) {
                        boolean raiseMgs = cont.humanBet(entered);
                    }
                    Media sound = new Media(new File(musicFile).
                        toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(sound);
                    mediaPlayer.play();
                } catch (NumberFormatException n) {
                    return;
                }
            }
        });

        call.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                boolean callMsg = cont.humanCall();
                Media sound = new Media(new File(musicFile).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();
            }
        });

        fold.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                boolean foldMsg = cont.humanFold();
                Media sound = new Media(new File(musicFile).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();
            }
        });

        restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                cont.startNewPokerHand();
                gameView.updatesMade();
                restart.setVisible(false);
                Media sound = new Media(new File(musicFile).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();
            }
        });

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                gameView.saveGame();
            }
        });

    }

    /**
     * Method called when the round ends.
     */
    public void endOfRound() {
        restart.setVisible(true);
        restart.setDisable(false);
    }

}
