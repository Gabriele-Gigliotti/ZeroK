module vfinf.zerok.zerok {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    opens vfinf.zerok.zerok to javafx.fxml;
    exports vfinf.zerok.zerok;

    exports vfinf.zerok.zerok.classes;
    opens vfinf.zerok.zerok.classes to com.fasterxml.jackson.databind;
    exports vfinf.zerok.zerok.classes.graphics;
    opens vfinf.zerok.zerok.classes.graphics to com.fasterxml.jackson.databind;
    exports vfinf.zerok.zerok.classes.serializers;
    opens vfinf.zerok.zerok.classes.serializers to com.fasterxml.jackson.databind;
    exports vfinf.zerok.zerok.classes.elements;
    opens vfinf.zerok.zerok.classes.elements to com.fasterxml.jackson.databind;
}