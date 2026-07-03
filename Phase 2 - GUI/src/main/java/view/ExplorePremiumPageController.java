package view;

import controller.ListenerController;
import exceptions.LackOfCreditException;
import graphic.musicplayergraphicalprojectphase2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.PremiumPlans;

import java.io.IOException;

public class ExplorePremiumPageController {

    @FXML
    private Button btn_purchase;

    @FXML
    private Label lblBtn_bronzePlan;

    @FXML
    private Label lblBtn_goldPlan;

    @FXML
    private Label lblBtn_silverPlan;

    private Label selectedLabel;

    @FXML
    void initialize() {
        selectedLabel = null;
    }

    @FXML
    void bronzePlanLblBtn_Clicked(MouseEvent event) {
        selectLabel(lblBtn_bronzePlan);
    }

    @FXML
    void goldPlanLblBtn_Clicked(MouseEvent event) {
        selectLabel(lblBtn_goldPlan);
    }

    @FXML
    void silverPlanLblBtn_Clicked(MouseEvent event) {
        selectLabel(lblBtn_silverPlan);
    }

    @FXML
    void purchaseBtn_Clicked(MouseEvent event) {
        if (selectedLabel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a premium plan first.");
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Error.png"));
            alert.showAndWait();
            return;
        }

        PremiumPlans selectedPlan = null;
        if (selectedLabel.equals(lblBtn_bronzePlan)) {
            selectedPlan = PremiumPlans.ONEMONTH;
        } else if (selectedLabel.equals(lblBtn_silverPlan)) {
            selectedPlan = PremiumPlans.TWOMONTHS;
        } else if (selectedLabel.equals(lblBtn_goldPlan)) {
            selectedPlan = PremiumPlans.SIXMONTHS;
        }

        try {
            String result = ListenerController.getListenerController().buyOrRenewSubscription(selectedPlan);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText(result);
            Main.setLoggedIn(true);
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Tick.png"));
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Navigate back to listener panel
                    MainTemplateController.centerPath.set("listenerPanel");
                }
            });
        } catch (LackOfCreditException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Error.png"));
            alert.showAndWait();
        }
    }

    private void selectLabel(Label label) {
        if (selectedLabel != null) {
            selectedLabel.getStyleClass().remove("selected");
        }
        selectedLabel = label;
        selectedLabel.getStyleClass().add("selected");
    }
}