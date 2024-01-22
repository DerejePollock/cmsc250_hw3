module edu.lawrence.tabbedhello {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.lawrence.tabbedhello to javafx.fxml;
    exports edu.lawrence.tabbedhello;
}
