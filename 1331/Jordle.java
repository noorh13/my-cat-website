import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.effect.DropShadow;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Jordle.
 *
 * @author nhasan36
 * @version 2.0
 */
public class Jordle extends Application {
    private static final int WORD_LENGTH = 5;
    private Backend backend = new Backend();
    private Label[][] labels = new Label[6][5];
    private Label statusLabel = new Label("Try guessing a word!");
    private StringBuilder currentGuess = new StringBuilder();
    private int currentRow = 0;
    private int currentCol = 0;

    /**
     * main method.
     *
     * @param args args
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Jordle");
        GridPane grid = createGameGrid();
        StackPane welcomeRoot = new StackPane();
        Scene welcomeScene = createWelcomeScene(primaryStage, welcomeRoot);
        primaryStage.setScene(welcomeScene);
        primaryStage.show();
    }
    private Scene createWelcomeScene(Stage primaryStage, StackPane root) {
        Image image = new Image("jordleImage.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(650);
        imageView.setFitHeight(700);
        imageView.setPreserveRatio(true);
        Text myText = new Text("Jordle");
        myText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 60));
        DropShadow ds = new DropShadow();
        ds.setOffsetY(5.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        myText.setEffect(ds);
        myText.setFill(Color.GREEN);
        Button btn = new Button("Play");
        btn.setFont(Font.font("Times New Roman", FontWeight.BOLD, 40));
        btn.setOnAction(e -> primaryStage.setScene(createGameScene(primaryStage)));

        VBox vbox = new VBox(20, myText, btn);
        vbox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(imageView, vbox);
        return new Scene(root, 550, 620);
    }
    private Scene createGameScene(Stage primaryStage) {
        GridPane grid = createGameGrid();
        Pane bottomPanel = createBottomPanel();
        BorderPane root = new BorderPane();
        root.setCenter(grid);
        root.setBottom(bottomPanel);

        Scene scene = new Scene(root, 400, 500);
        handleKeyPress(scene);
        return scene;
    }
    private GridPane createGameGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(5);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                Label label = new Label();
                label.setPrefSize(50, 50);
                label.setFont(Font.font("Arial", 20));
                label.setAlignment(Pos.CENTER);
                label.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                    CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                label.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,
                    CornerRadii.EMPTY, Insets.EMPTY)));
                labels[i][j] = label;
                grid.add(label, j, i);
            }
        }
        return grid;
    }

    private Pane createBottomPanel() {
        HBox bottomPanel = new HBox(10);
        bottomPanel.setPadding(new Insets(15, 12, 15, 12));
        bottomPanel.setAlignment(Pos.CENTER);

        Button resetButton = new Button("Reset");
        resetButton.setFont(new Font("Times New Roman", 14));
        resetButton.setOnAction(e -> restartGame());

        Button instructionButton = new Button("Instructions");
        instructionButton.setFont(new Font("Times New Roman", 14));
        instructionButton.setOnAction(e -> showInstructions());

        statusLabel.setFont(new Font("Times New Roman", 14));

        bottomPanel.getChildren().addAll(instructionButton, statusLabel, resetButton);
        return bottomPanel;
    }

    private void handleKeyPress(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode().isLetterKey() && currentCol < WORD_LENGTH) {
                String letter = event.getText().toUpperCase();
                labels[currentRow][currentCol].setText(letter);
                currentGuess.append(letter);
                if (++currentCol == WORD_LENGTH) {
                    checkGuess();
                }
            } else if (event.getCode() == KeyCode.BACK_SPACE && currentCol > 0) {
                labels[currentRow][--currentCol].setText("");
                currentGuess.deleteCharAt(currentCol);
            } else if (event.getCode() == KeyCode.ENTER) {
                if (currentGuess.length() == WORD_LENGTH) {
                    checkGuess();
                } else {
                    showInputError(); // Show error when not enough letters are entered
                }
            }
        });
    }

    private void checkGuess() {
        try {
            String result = backend.check(currentGuess.toString());
            for (int i = 0; i < WORD_LENGTH; i++) {
                char res = result.charAt(i);
                labels[currentRow][i].setBackground(new Background(new BackgroundFill(res == 'g' ? Color.GREEN
                    : res == 'y' ? Color.YELLOW : Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
            }
            if (result.equals("ggggg")) {
                statusLabel.setText("Congratulations! You've guessed the word!");
                statusLabel.setFont(new Font("Times New Roman", 14));
            } else if (++currentRow == 6) {
                statusLabel.setText("Game over. The word was: " + backend.getTarget());
            } else {
                currentGuess = new StringBuilder();
                currentCol = 0;
            }
        } catch (InvalidGuessException e) {
            statusLabel.setText(e.getMessage());
        }
    }

    private void restartGame() {
        backend.reset();
        currentRow = 0;
        currentCol = 0;
        currentGuess = new StringBuilder();
        statusLabel.setText("Try guessing a word!");
        statusLabel.setFont(new Font("Times New Roman", 14));
        for (Label[] row : labels) {
            for (Label label : row) {
                label.setText("");
                label.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,
                    CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
    }

    private void showInstructions() {
        Stage instructionStage = new Stage();
        instructionStage.setTitle("Instructions");
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        Text instructions = new Text("Guess the 5-letter word in 6 tries.\n"
            + "Each guess must be a valid 5-letter word. Hit the enter button to submit.\n"
            + "After each guess, the color of the tiles will change to show how close your guess was to the word.\n"
            + "Green: The letter is in the word and in the correct spot.\n"
            + "Yellow: The letter is in the word but in the wrong spot.\n"
            + "Gray: The letter is not in the word in any spot.");
        instructions.setFont(Font.font("Arial", 16));
        layout.getChildren().add(instructions);
        Scene scene = new Scene(layout, 750, 400);
        instructionStage.setScene(scene);
        instructionStage.show();
    }
    private void showInputError() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a 5-letter word!");
        alert.getButtonTypes().setAll(new ButtonType("OK"));
        Text contentText = new Text("Please enter a 5-letter word!");
        contentText.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 16));
        VBox content = new VBox(contentText);
        alert.getDialogPane().setContent(content);

        alert.showAndWait();
    }
}