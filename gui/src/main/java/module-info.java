module osmosismahmoud.gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens osmosismahmoud.gui to javafx.fxml;
    exports osmosismahmoud.gui;
}