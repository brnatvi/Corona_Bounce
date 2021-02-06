module org.CoronaBounce {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.CoronaBounce to javafx.fxml;
    exports org.CoronaBounce;
}