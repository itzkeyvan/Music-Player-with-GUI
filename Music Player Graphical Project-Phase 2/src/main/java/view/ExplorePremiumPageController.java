package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

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
        if (selectedLabel != null) {
            System.out.println("Selected Plan: " + selectedLabel.getText());
        } else {
            System.out.println("No plan selected");
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
