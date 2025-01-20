module com.example.drone {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.net.http;
    requires java.desktop;


    opens com.example.drone.Frontend to javafx.fxml;
    exports com.example.drone.Frontend;
    exports com.example.drone.Backend;
    opens com.example.drone.Backend to javafx.fxml;



}