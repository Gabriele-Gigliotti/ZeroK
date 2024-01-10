module vfinf.zerok.zerok {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    opens vfinf.zerok.zerok to javafx.fxml;
    exports vfinf.zerok.zerok;

    exports vfinf.zerok.zerok.networkTest.classes;
    opens vfinf.zerok.zerok.networkTest.classes to com.fasterxml.jackson.databind;
    exports vfinf.zerok.zerok.networkTest.classes.graphics;
    opens vfinf.zerok.zerok.networkTest.classes.graphics to com.fasterxml.jackson.databind;
}