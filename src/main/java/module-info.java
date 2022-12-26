module upc.coop.dactylo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens upc.coop.dactylo to javafx.fxml;
    exports upc.coop.dactylo;
}