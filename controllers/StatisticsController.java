package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import sample.Utils.kek;
import sample.helpers.PersonDataHelper;
import sample.types.Head;
import sample.types.Person;
import sample.types.Salary;
import sample.types.Worker;

public class StatisticsController {
    @FXML
    ComboBox<Worker> worker;

    @FXML
    ComboBox<Head> head;

    @FXML
    DatePicker dateStart;

    @FXML
    DatePicker dateEnd;

    @FXML
    Button show;

    @FXML
    TableView<Salary> table;

    @FXML
    Label sum;


    public void setWorkers(ObservableList<Worker> data) {
        worker.setItems(data);
    }

    public void setHead(ObservableList<Head> data) {
        head.setItems(data);
    }

    @FXML
    protected void getData(ActionEvent event) throws Exception {
        kek abc = kek.getInstance();

        Window owner = show.getScene().getWindow();
        if(worker.getValue() != null) {
            ObservableList<Salary> data1 = abc.employeeDataAcessor.getSalary(worker.getValue(),dateStart.getValue().toString(),dateEnd.getValue().toString());
            Double sumvalue = 0.0;
            for(Salary salary: data1){
                sumvalue += Double.parseDouble(salary.getAmount());
            }
            sum.setText("Сума : " + sumvalue);
            TableColumn firstNameCol = new TableColumn("Дата");
            firstNameCol.setCellValueFactory(new PropertyValueFactory<>("month"));
            firstNameCol.setPrefWidth(96.0);
            TableColumn lastNameCol = new TableColumn("Дохід");
            lastNameCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
            lastNameCol.setPrefWidth(103.0);
            table.getColumns().setAll(firstNameCol, lastNameCol);
            table.setItems(data1);
        }

    }

    @FXML
    protected void handleStatisticsButtonAction(ActionEvent event) throws Exception {
        kek.getInstance().setPosad();
    }

    @FXML
    protected void handlePaymentsButtonAction(ActionEvent event) throws Exception {
        kek.getInstance().setPay();
    }

    @FXML
    protected void getDataHead(ActionEvent event) throws Exception {
        kek abc = kek.getInstance();

        Window owner = show.getScene().getWindow();
        if(head.getValue() != null) {
            ObservableList<Salary> data1 = abc.employeeDataAcessor.getSalaryHead(head.getValue(),dateStart.getValue().toString(),dateEnd.getValue().toString());
            Double sumvalue = 0.0;
            for(Salary salary: data1){
                sumvalue += Double.parseDouble(salary.getAmount());
            }
            sum.setText("Сума : " + sumvalue);
            TableColumn firstNameCol = new TableColumn("Дата");
            firstNameCol.setCellValueFactory(new PropertyValueFactory<>("month"));
            firstNameCol.setPrefWidth(96.0);
            TableColumn lastNameCol = new TableColumn("Дохід");
            lastNameCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
            lastNameCol.setPrefWidth(103.0);
            table.getColumns().setAll(firstNameCol, lastNameCol);
            table.setItems(data1);
        }

    }
}
