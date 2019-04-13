package sample.controllers;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import sample.Utils.kek;
import sample.helpers.PersonDataHelper;
import sample.types.Position;
import sample.types.Viddil;

import java.awt.*;

public class LoginController {
    @FXML
    private TextField db;

    @FXML
    private TextField user;

    @FXML
    private TextField password;

    @FXML
    private TextField url;

    @FXML
    private javafx.scene.control.Button addButton;

    @FXML
    protected void handleAddButtonAction(ActionEvent event) throws Exception {
        String dbVal = db.getText();
        String userVal = user.getText();
        String passwordVal = password.getText();
        String urlVal  = url.getText();

        Window owner = addButton.getScene().getWindow();
        kek.getInstance().setConfig(dbVal,urlVal,userVal,passwordVal);
    }


}
