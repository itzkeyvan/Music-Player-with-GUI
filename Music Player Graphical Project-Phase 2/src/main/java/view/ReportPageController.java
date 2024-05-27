package view;

import graphic.musicplayergraphicalprojectphase2.Main;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class ReportPageController
{
    @FXML
    private Label lblBtn_Report;

    @FXML
    private Label lblBtn_cancel;

    @FXML
    private TextArea txtArea_Description;

    @FXML
    void ReportBtnInReportPage_clicked(MouseEvent event)
    {
        Main.getListenerController().reportArtist(ArtistInfoPageController.getArtist().getUserName(),txtArea_Description.getText());
    }

    @FXML
    void cancelBtn_clicked(MouseEvent event)
    {
        Main.getStage().setScene(new Scene(Main.getCenterNodesHistory().get(Main.getCenterNodesHistory().size()-2)));
    }

}
