module org.CoronaBounce {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.coronabounce to javafx.fxml;
    exports org.coronabounce;
}