package view;

import controller.ListenerController;
import graphic.musicplayergraphicalprojectphase2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FavouriteGenresPageController implements Initializable
{

    @FXML
    private Label lblBtn_doneGenresSelection;

    @FXML
    private ToggleButton tglBtn_Country;

    @FXML
    private ToggleButton tglBtn_Hiphop;

    @FXML
    private ToggleButton tglBtn_History;

    @FXML
    private ToggleButton tglBtn_Interview;

    @FXML
    private ToggleButton tglBtn_Jazz;

    @FXML
    private ToggleButton tglBtn_Pop;

    @FXML
    private ToggleButton tglBtn_Rock;

    @FXML
    private ToggleButton tglBtn_Society;

    @FXML
    private ToggleButton tglBtn_TrueCrime;

    private int selectedCount = 0;
    private final int maxSelections = 4;

    @FXML
    void doneGenresSelectionBtn_clicked(MouseEvent event) {
        String[] selectedGenres = getSelectedGenres();

        if (selectedGenres.length==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Select at least 1 and at most 4 genres!");
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Error.png"));
            alert.showAndWait();
        }
        else
        {
            String result=ListenerController.getListenerController().setFavouriteGenres(selectedGenres);
            if(result.equals("Genres added to favourites successfully."))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Genres added to favourites successfully.");
                Main.setLoggedIn(true);
                Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Tick.png"));
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        try {
                            Main.setCurrentCenterNode(FXMLLoader.load(HomePageController.class.getResource("homePage.fxml")));
                            MainTemplateController.getBorderPane_mainTemplate().setCenter(Main.getCurrentCenterNode());
                            Scene scene = new Scene(FXMLLoader.load(MainTemplateController.class.getResource("mainTemplate.fxml")));
                            Main.getStage().setScene(scene);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });
            }
        }
    }
    private String[] getSelectedGenres() {
        List<String> selectedGenres = new ArrayList<>();
        if (tglBtn_Country.isSelected()) selectedGenres.add("Country");
        if (tglBtn_Hiphop.isSelected()) selectedGenres.add("Hiphop");
        if (tglBtn_History.isSelected()) selectedGenres.add("History");
        if (tglBtn_Interview.isSelected()) selectedGenres.add("Interview");
        if (tglBtn_Jazz.isSelected()) selectedGenres.add("Jazz");
        if (tglBtn_Pop.isSelected()) selectedGenres.add("Pop");
        if (tglBtn_Rock.isSelected()) selectedGenres.add("Rock");
        if (tglBtn_Society.isSelected()) selectedGenres.add("Society");
        if (tglBtn_TrueCrime.isSelected()) selectedGenres.add("TrueCrime");
        return selectedGenres.toArray(new String[0]);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleButton[] toggleButtons = {
                tglBtn_Country, tglBtn_Hiphop, tglBtn_History,
                tglBtn_Interview, tglBtn_Jazz, tglBtn_Pop,
                tglBtn_Rock, tglBtn_Society, tglBtn_TrueCrime
        };
        for (ToggleButton toggleButton : toggleButtons) {
            toggleButton.setOnAction(event -> {
                if (toggleButton.isSelected()) {
                    if (selectedCount >= maxSelections) {
                        toggleButton.setSelected(false);
                    } else {
                        selectedCount++;
                    }
                } else {
                    selectedCount--;
                }
            });
        }
    }
}
