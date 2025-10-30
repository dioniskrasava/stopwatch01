module org.majo.stopwatch01 {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens org.majo.stopwatch01 to javafx.fxml;
    exports org.majo.stopwatch01;
}