module org.example.reto {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.reto to javafx.fxml;
    exports org.example.reto;
}