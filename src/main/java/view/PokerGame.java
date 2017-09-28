package view;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;
import java.util.Optional;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import viewcontroller.PokerGameController;
import javafx.scene.layout.VBox;
import viewcontroller.GameState;


/**
 * @author CS1331 TAs
 * @version 1.0
 */

public class PokerGame extends Application implements Serializable {

    private static final long serialVersionUID = 6128016096756071380L;
    private static Stage primaryStage;
    private static PokerGameController pokerGameController;
    private transient GameScreen gameScreen;
    private transient ControlPane controlPane;
    private transient Console console;

    /**
     * getter method for primaryStage.
     * @return primaryStage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * this method is called upon running/launching the application
     * this method should display a scene on the stage
     * @param ps The primary Stage
     */
    public void start(Stage ps) {
        primaryStage = ps;
        Scene scene = new Scene(new StartScreen(this));
        ps.setScene(scene);
        ps.sizeToScene();
        ps.show();
    }

    /**
     * Starts the Game
     * This is called by StartScreen whenever it is done and the GameScreen,
     * ControlPane, and Console should be displayed
     * @param name The name of the human player
     * @param chips the chips the human player chose
     */
    public void startGame(String name, int chips) {
        pokerGameController = new PokerGameController(this, name, chips);
        VBox vBox = new VBox(3);
        gameScreen = new GameScreen(pokerGameController);
        controlPane = new ControlPane(pokerGameController);
        console = new Console();
        vBox.getChildren().addAll(gameScreen);
        vBox.getChildren().addAll(controlPane);
        vBox.getChildren().addAll(console);

        pokerGameController.start();

        Scene page = new Scene(vBox);
        primaryStage.setScene(page);

    }

    /**
     * Resume the old game
     * This is called by StartScreen whenever the user choose to resumeGame
     * the old game
     */
    public void resumeGame() {
        try {
            FileInputStream fileIn = new FileInputStream("gameData.ser");
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            pokerGameController = (PokerGameController) objIn.readObject();
            objIn.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Game file not found");
            c.printStackTrace();
            return;
        }

        VBox vBox2 = new VBox(3);
        gameScreen = new GameScreen(pokerGameController);
        controlPane = new ControlPane(pokerGameController);
        console = new Console();
        console.putMessage("Welcome back!");
        vBox2.getChildren().addAll(gameScreen);
        vBox2.getChildren().addAll(controlPane);
        vBox2.getChildren().addAll(console);

        Scene page = new Scene(vBox2);
        //updatesMade();
        primaryStage.setScene(page);

    }

    /**
     * Save the current game and ask the user if exit
     * This is called by ControlPane whenever the user choose to save and exit
     */
    public static void saveGame() {
        try {
            FileOutputStream fileOut = new FileOutputStream("gameData.ser");
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(pokerGameController);
            objOut.close();
            fileOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Game Saved!");
        alert.setHeaderText("Congratulations!");
        alert.setContentText("Your game is succesfully saved!");

        ButtonType buttonTypeOne = new ButtonType("Exit");
        ButtonType buttonTypeTwo = new ButtonType("Stay",
            ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            primaryStage.close();
        } else {
            alert.close();
        }
    }


    /**
     * This is called by PokerGameController whenever updates are made. You
     * must handle updating the UI here.
     */
    public void updatesMade() {
        if (pokerGameController.getState().equals(GameState.DONE)) {
            gameScreen.endOfRound();
            controlPane.endOfRound();
        } else {
            gameScreen.updatesMade();
        }
        if (pokerGameController.getState().equals(GameState.AI_BET)) {
            controlPane.getRaise().setDisable(true);
            controlPane.getFold().setDisable(true);
            controlPane.getCall().setDisable(true);
            controlPane.getRestart().setDisable(true);
        }
        if (pokerGameController.getState().equals(GameState.HUMAN_BET)) {
            controlPane.playerTurn();
        }

    }

    /**
     * This is the main method that launches the javafx application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
