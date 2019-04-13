package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Utils.kek;
import sample.controllers.LoginController;
import sample.controllers.PositionController;
import sample.dao.PersonDataAccessor;
import sample.types.Position;
import sample.types.Viddil;

public class Main extends Application {
    public static PersonDataAccessor dataAccessor;
    private static Stage primaryStage1;

    @Override
    public void start(Stage primaryStage) throws Exception{
        kek.init(primaryStage).setLogin();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
