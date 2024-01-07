module vfinf.zerok.zerok {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens vfinf.zerok.zerok to javafx.fxml;
    exports vfinf.zerok.zerok;
}