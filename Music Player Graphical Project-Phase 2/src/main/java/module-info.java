module graphic.musicplayergraphicalprojectphase2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;
    requires mp3agic;


    opens graphic.musicplayergraphicalprojectphase2 to javafx.fxml;
    exports graphic.musicplayergraphicalprojectphase2;
}