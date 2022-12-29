// The package in which the current Java compilation unit is to be found.
package upc.coop.dactylo;
// Imports from existing Java libraries, classes and interfaces.
import javafx.application.Application;
import javafx.stage.Stage;
// Import from custom libraries, classes and interfaces.
import upc.coop.dactylo.scenes.game.GameScene;
import upc.coop.dactylo.settings.Settings;

/**
 * <p>
 *     This here compilation unit contains the class structure Runner which extends javafx Application type.
 * </p>
 * <p>
 *     The class models the main entry point of the application.
 * </p>
 * @see Application
 * @author Andrei-Paul Ionescu
 */
public class Runner extends Application implements Settings {

    // Static values/constants of the class.

    // Fields/attributes of the class.

    // Constructor(s) of the class.

    // Getters of the class.

    // Setters of the class.

    // Public non-static methods of the unit.
    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     */
    @Override
    public void start(Stage primaryStage) {
        // To be replaced with a state machine.
        var scene = new GameScene();
        primaryStage.setWidth(DEFAULT_WINDOW_WIDTH);
        primaryStage.setHeight(DEFAULT_WINDOW_HEIGHT);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle(APPLICATION_TITLE);
        primaryStage.show();
    }

    // Public static methods of the unit.
    public static void main(String[] args) {launch();}

    // Private methods of the unit.

    // Nested class(es)/membered type(s).
}
