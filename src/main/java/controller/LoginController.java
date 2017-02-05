package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField fldLogin;

    @FXML
    private PasswordField fldPassword;

    @FXML
    private Button btnEnter, btnExit;

    public void enterButtonAction() throws IOException {
        if (fldLogin.getText().equals("admin") && fldPassword.getText().equals("admin")){
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/admin_panel.fxml"));
            stage.setTitle("Administration");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
            Stage old = (Stage) btnEnter.getScene().getWindow();
            old.close();
        } else {
            fldLogin.setText("");
            fldPassword.setText("");
            fldPassword.setPromptText("PASSWORD IS WRONG!");
        }
    }

    public void exitButtonAction() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }


}
