module org.CoronaBounce {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens org.coronabounce to javafx.fxml;
    exports org.coronabounce;
}
