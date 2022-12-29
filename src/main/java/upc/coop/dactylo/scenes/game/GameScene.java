// The package in which the current Java compilation unit is to be found.
package upc.coop.dactylo.scenes.game;
// Imports from existing Java libraries, classes and interfaces.
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Objects;

// Import from custom libraries, classes and interfaces.

import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import upc.coop.dactylo.scenes.model.Scene;
import upc.coop.dactylo.settings.Settings;
import org.fxmisc.richtext.StyleClassedTextArea;


/**
 * @author Andrei-Paul Ionescu
 */
public final class GameScene extends Scene implements Settings {
    // Static values/constants of the class.

    // Fields/attributes of the class.
    /**
     * <p>
     *     The two vertical panes, will contain the option menus which enable the user to alter the manner
     *     in which words are displayed, but also modify the amount of time allocated for the round.
     * </p>
     *
     * <p>
     *     This values are created but once, and are cache towards the lifecycle of the software.
     * </p>
     *
     * <p>
     *     This architectural choice was taken due to the fact, that during one instance of the software,
     *     the user might close the menus multiple times, hence creating unwanted overhead if the values are not cached.
     * </p>
     */
    private final HBox wordsOptions;
    private final HBox timeOptions;

    private boolean isWordsOptionsOpen = false;
    private boolean isTimeOptionsOpen = false;

    // Instance side initialisation block.
    {
        // Create the two vertical panes, which will contain the option menus.
        this.wordsOptions = createOptionMenu(List.of("punctuation", "numbers"));
        this.timeOptions = createOptionMenu(List.of("10", "25", "50", "100"));

        this.wordsOptions.setTranslateY(-93.5);
        this.timeOptions.setTranslateY(-93.5);
    }

    // Constructor(s) of the class.
    /**
     * Creates a Scene for a specific root Node with a specific size and fill.
     *
     * @param root   The root node of the scene graph
     * @param width  The width of the scene
     * @param height The height of the scene
     * @param fill   The fill
     * @throws NullPointerException if root is null
     */
    private GameScene(Parent root, double width, double height, Paint fill) {
        super(root, width, height, fill);
    }

    public GameScene(){
        this(new VBox(), DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT, Color.WHITE);
        this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        this.setRoot(init());

        this.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                System.out.println("To implement a state stack so as to be able to pause.");
            } else {
                System.out.println("Todo!");
            }
        });

        this.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {

        });
    }

    // Getters of the class.

    // Setters of the class.

    // Public non-static methods of the unit.

    // Public static methods of the unit.

    // Private methods of the unit.
    private HBox createOptionMenu(List<String> options){
        HBox menu = new HBox();
        menu.setPrefSize(304, 45);
        menu.setMaxSize(304, 45);
        menu.setBackground(Background.fill(Color.valueOf("#8A1538")));
        menu.setAlignment(Pos.CENTER);

        for (int i = 0; i < options.size(); ++i) {
            Text text = new Text(options.get(i));
            text.setTranslateX(33 * i);
            text.setTextAlignment(TextAlignment.CENTER);
            text.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
            text.setFill(Color.WHITE);
            text.addEventHandler(MouseEvent.MOUSE_ENTERED, (event -> text.setOpacity(0.5)));
            text.addEventHandler(MouseEvent.MOUSE_EXITED, (event -> text.setOpacity(1)));

            menu.getChildren().add(text);
        }


        return menu;
    }
    /**
     * <p>
     *     This here method is responsible for initialising the view's Parent component.
     * </p>
     * @return {Parent} the newly constructed parent component.
     * @author Andrei-Paul Ionescu
     */
    private Parent init() {
        var parent = new VBox();

        var optionBar = new Pane();
        optionBar.setTranslateX(466);
        optionBar.setTranslateY(74);
        optionBar.setBackground(Background.fill(Color.valueOf("#8A1538")));
        optionBar.setPrefSize(356, 45);
        optionBar.setMaxWidth(356);
        optionBar.setMaxHeight(45);
        parent.getChildren().addAll(optionBar);

        Text time = new Text("time");
        time.setTranslateX(39);
        time.setTranslateY(29);
        time.setFill(Color.WHITE);
        time.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        time.addEventHandler(MouseEvent.MOUSE_ENTERED, (event -> time.setOpacity(0.5)));
        time.addEventHandler(MouseEvent.MOUSE_EXITED, (event -> time.setOpacity(1)));
        time.setVisible(true);
        time.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {
            if(this.isTimeOptionsOpen) {

                Timeline timeline = new Timeline();
                KeyValue keyValue = new KeyValue(this.timeOptions.translateXProperty(), 300, Interpolator.EASE_BOTH);
                KeyFrame keyFrame = new KeyFrame(Duration.millis(500), keyValue);
                timeline.getKeyFrames().add(keyFrame);
                timeline.play();
                parent.getChildren().remove(this.timeOptions);
            }

            else
            {
                this.timeOptions.setTranslateY(-275.5);
                parent.getChildren().add(this.timeOptions);

                Timeline timeline = new Timeline();
                KeyValue keyValue = new KeyValue(this.timeOptions.translateXProperty(), this.getWidth() - this.timeOptions.getPrefWidth() - 143, Interpolator.EASE_BOTH);
                KeyFrame keyFrame = new KeyFrame(Duration.millis(500), keyValue);
                timeline.getKeyFrames().add(keyFrame);
                timeline.play();
            }


            this.isTimeOptionsOpen = !this.isTimeOptionsOpen;
        }));
        optionBar.getChildren().add(time);

        Text words = new Text("words");
        words.setTranslateX(155);
        words.setTranslateY(29);
        words.setFill(Color.WHITE);
        words.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        words.addEventHandler(MouseEvent.MOUSE_ENTERED, (event -> words.setOpacity(0.5)));
        words.addEventHandler(MouseEvent.MOUSE_EXITED, (event -> words.setOpacity(1)));
        words.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {
            if(this.isWordsOptionsOpen) {

                Timeline timeline = new Timeline();
                KeyValue keyValue = new KeyValue(this.wordsOptions.translateXProperty(), 300, Interpolator.EASE_BOTH);
                KeyFrame keyFrame = new KeyFrame(Duration.millis(500), keyValue);
                timeline.getKeyFrames().add(keyFrame);
                timeline.play();
                parent.getChildren().remove(this.wordsOptions);
            }

            else
            {
                this.wordsOptions.setTranslateY(-275.5);
                parent.getChildren().add(this.wordsOptions);

                Timeline timeline = new Timeline();
                KeyValue keyValue = new KeyValue(this.wordsOptions.translateXProperty(), 162, Interpolator.EASE_BOTH);
                KeyFrame keyFrame = new KeyFrame(Duration.millis(500), keyValue);
                timeline.getKeyFrames().add(keyFrame);
                timeline.play();
            }


            this.isWordsOptionsOpen = !this.isWordsOptionsOpen;
        }));
        optionBar.getChildren().add(words);

        Text wiki = new Text("wiki");
        wiki.setTranslateX(281);
        wiki.setTranslateY(29);
        wiki.setFill(Color.WHITE);
        wiki.setFont(Font.font("Arial", FontWeight.MEDIUM, 16));
        wiki.addEventHandler(MouseEvent.MOUSE_ENTERED, (event -> wiki.setOpacity(0.5)));
        wiki.addEventHandler(MouseEvent.MOUSE_EXITED, (event -> wiki.setOpacity(1)));
        optionBar.getChildren().add(wiki);


        Text language = new Text("English (UK)");
        language.setFill(Color.BLACK);
        language.setTranslateY(63);
        language.setTranslateX(563);
        language.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        language.addEventHandler(MouseEvent.MOUSE_ENTERED, (event -> language.setOpacity(0.5)));
        language.addEventHandler(MouseEvent.MOUSE_EXITED, (event -> language.setOpacity(1)));
        parent.getChildren().add(language);

        StyleClassedTextArea textArea = new StyleClassedTextArea();
        textArea.setMaxSize(980, 282);
        textArea.setPrefSize(980, 282);
        textArea.setTranslateX(150);
        textArea.setTranslateY(229);
        textArea.setBorder(Border.stroke(Color.valueOf("#8A1538")));
        textArea.setStyle("-fx-font-size: 24px;");
        textArea.appendText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n");
        parent.getChildren().add(textArea);

        return parent;
    }

    // Nested class(es)/membered type(s).
}
