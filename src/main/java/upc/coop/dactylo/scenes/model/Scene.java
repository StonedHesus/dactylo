// The package in which the current Java compilation unit is to be found.
package upc.coop.dactylo.scenes.model;
// Imports from existing Java libraries, classes and interfaces.

// Import from custom libraries, classes and interfaces.

import javafx.scene.Parent;
import javafx.scene.paint.Paint;

/**
 * @author Andrei-Paul Ionescu
 */
public abstract class Scene extends javafx.scene.Scene {

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
    protected Scene(Parent root, double width, double height, Paint fill) {
        super(root, width, height, fill);
    }

    // Getters of the class.

    // Setters of the class.

    // Public non-static methods of the unit.

    // Public static methods of the unit.

    // Private methods of the unit.

    // Nested class(es)/membered type(s).
}
