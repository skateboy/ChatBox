package sample;

import javafx.fxml.FXML;

import java.awt.event.ActionListener;
import java.lang.String;
import java.net.URL;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import javax.annotation.Resources;

public class Controller {
    private String user;
    private String bot = "Huang";
    private String message;
    @FXML
    private TextField txtUser, txtInput;

    @FXML
    private TextArea txtDisplay;


    @FXML
    private Button btnUser, btnSend;



    @FXML
    protected void initialize() {

    }

    @FXML
    private void sendMessage(ActionEvent event){

        if(txtInput.getText().isEmpty()){

        } else {
            message = txtInput.getText();
            txtDisplay.appendText(user + ": " + message + "\n\n");

            txtInput.setText("");
        }
    }

    @FXML
    private void setUserName(ActionEvent event) {
        user = txtUser.getText();
        txtUser.setVisible(false);
        btnUser.setVisible(false);
        txtInput.setDisable(false);
        btnSend.setDisable(false);
        txtDisplay.setText(bot + ": " + "How may I help you today, " + user +"?\n\n");
    }
}
