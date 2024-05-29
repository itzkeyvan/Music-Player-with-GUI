module graphic.musicplayergraphicalprojectphase2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;
    requires mp3agic;
    requires java.desktop;
    requires jlayer;


    opens graphic.musicplayergraphicalprojectphase2 to javafx.fxml;
    exports graphic.musicplayergraphicalprojectphase2;
    exports view;
    opens view to javafx.fxml;
}