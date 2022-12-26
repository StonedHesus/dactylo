// The package in which the current Java compilation unit is to be found.
package upc.coop.dactylo.scenes.welcome;
// Imports from existing Java libraries, classes and interfaces.
import javafx.animation.FadeTransition;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Objects;
import java.util.Optional;
// Import from custom libraries, classes and interfaces.
import upc.coop.dactylo.scenes.model.Scene;
import upc.coop.dactylo.settings.Settings;


/**
 * <p>
 *     This here compilation unit contains a Java class construct which models the welcome view of the application.
 * </p>
 * @see Scene
 * @author Andrei-Paul Ionescu
 */
public final class WelcomeScene extends Scene implements Settings {
    // Static values/constants of the class.

    // Fields/attributes of the class.

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
    private WelcomeScene(Parent root, double width, double height, Paint fill) {
        super(root, width, height, fill);
    }

    public WelcomeScene(){
        this(new VBox(), DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT, Color.WHITE);
        this.setRoot(init());

        this.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                this.getRoot().setVisible(false);
            } else {
                System.out.println("Todo!");
            }
        });

        this.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {

        });
        FadeTransition fade = new FadeTransition(Duration.seconds(2), this.getRoot());
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setCycleCount(1);
        fade.play();
    }

    // Getters of the class.

    // Setters of the class.

    // Public non-static methods of the unit.

    // Public static methods of the unit.

    // Private methods of the unit.

    /**
     * <p>
     *     This here method is responsible for initialising the view's Parent component.
     * </p>
     * @return {Parent} the newly constructed parent component.
     * @author Andrei-Paul Ionescu
     */
    private Parent init() {
        var parent = new VBox();
        parent.setPrefSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
        parent.setMinSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
        parent.setVisible(true);

        Text title = new Text( "Welcome !");
        title.setFill(Color.BLACK);
        title.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 64));
        title.setTranslateX(480);
        title.setTranslateY(165);
        parent.getChildren().add(title);

        Text interaction = new Text("To continue please tap enter or any other key!");
        interaction.setFill(Color.BLACK);
        interaction.setFont(Font.font("Arial", FontWeight.LIGHT, 24));
        interaction.setTranslateX(400);
        interaction.setTranslateY(299);
        parent.getChildren().add(interaction);

        var footer = new HBox();
        footer.setMinSize(1280, 221);
        footer.setPrefSize(1280, 221);
        footer.setBackground(Background.fill(Color.valueOf("#8A1538")));
        footer.setTranslateY(499);

        Text copyright = new Text("© Andrei-Paul Ionescu");
        copyright.setFill(Color.WHITE);
        copyright.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        copyright.setTranslateX((int)(DEFAULT_WINDOW_WIDTH / 2) - 100);
        copyright.setTranslateY(135);
        footer.getChildren().add(copyright);

        var image = getImage("logo-upc.png");
        if (image.isPresent()) {
            ImageView upc = new ImageView(image.get());
            upc.setVisible(true);
            upc.setPreserveRatio(true);
            upc.setFitHeight(89);
            upc.setTranslateX(174);
            upc.setTranslateY(22);
            footer.getChildren().add(upc);
        }

        image = getImage("logo-irif.png");
        if (image.isPresent()) {
            ImageView irif = new ImageView(image.get());
            irif.setVisible(true);
            irif.setPreserveRatio(true);
            irif.setFitHeight(89);
            irif.setTranslateX(230);
            irif.setTranslateY(22);
            footer.getChildren().add(irif);
        }

        parent.getChildren().add(footer);

        return parent;
    }

    /**
     * <p>
     *     This here method is a helper routine, whose role is to attempt the creation of a new Image object, in
     *     case of a failure, which will as a consequence of the fact that the image does not appear to exist within
     *     the resources of this class, the routine will return an empty Optional object.
     * </p>
     * @param imageName {String} a string indicating the name of the image which we are trying to load.
     * @return {Optional<Image>} an optional object which contains the image with the specified name it that image exists
     *         within the resources of this class, and it was successfully loaded into memory.
     * @author Andrei-Paul Ionescu
     */
    private Optional<Image> getImage(String imageName) {
        try {
            return Optional.of(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(imageName))));
        } catch (NullPointerException expected) {
            return Optional.empty();
        }
    }

    // Nested class(es)/membered type(s).
}
