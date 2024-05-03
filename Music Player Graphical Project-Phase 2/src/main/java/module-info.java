module graphic.musicplayergraphicalprojectphase2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens graphic.musicplayergraphicalprojectphase2 to javafx.fxml;
    exports graphic.musicplayergraphicalprojectphase2;
}