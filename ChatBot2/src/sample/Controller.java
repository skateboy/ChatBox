package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class Controller {
    private String user;
    private String message;
    @FXML
    private TextField txtUser, txtInput;

    @FXML
    private TextArea txtDisplay;


    @FXML
    private Button btnUser, btnSend;





    @FXML
    private void sendMessage(ActionEvent event){
        
        message = txtInput.getText();
        txtDisplay.appendText(user + ": " + message + "\n\n");

        txtInput.setText("");
    }

    @FXML
    private void setUserName(ActionEvent event) {
        user = txtUser.getText();
        txtUser.setVisible(false);
        btnUser.setVisible(false);
        txtInput.setDisable(false);
        btnSend.setDisable(false);
    }
}
