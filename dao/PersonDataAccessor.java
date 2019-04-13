package sample.dao;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import sample.types.Person;
import sample.types.Position;
import sample.types.Viddil;

import javax.naming.Name;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import static java.sql.DriverManager.*;

public class PersonDataAccessor {

    private Connection connection ;

    public PersonDataAccessor(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        //Class.forName(driverClassName);
        connection = getConnection(dbURL, user, password);
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public ObservableList<Position> getPersonList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet res = stmnt.executeQuery("select * from positions");
        ){
            ObservableList<Position> positionList = FXCollections.observableArrayList();

            while (res.next()) {
                String id = res.getString("idPos");
                String salary = res.getString("salary");
                String idDep = res.getString("idDep");
                String name= res.getString("Name");
                Position position = new Position(id, salary, idDep, name);
                positionList.add(position);
            }

            return positionList;
        }
    }

    public ObservableList<Viddil> getViddilList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet res = stmnt.executeQuery("select * from departments");
        ){
            ObservableList<Viddil> viddilList = FXCollections.observableArrayList();

            while (res.next()) {
                String id = res.getString("idDep");
                String name= res.getString("nameDep");
                Viddil viddil = new Viddil(id, name);
                viddilList.add(viddil);
            }

            for(Viddil viddil: viddilList) {
                System.out.println(viddil);
            }

            return viddilList;
        }
    }

    public void deletePerson(String name) throws SQLException {
        Statement stmnt = connection.createStatement();
        int res = stmnt.executeUpdate("DELETE  from positions where positions.idPos = " + name);
    }

    public void addPerson(Position position) throws SQLException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = format.parse(position.startDate().get());
        Date myDate2 = format.parse(position.endDate().get());

        PreparedStatement stmnt = connection.prepareStatement("insert  into positions (salary, Name,idDep,startDate,endDate) values (?,?,?,?,?)");

        stmnt.setInt(1, Integer.parseInt(position.getSalary()));
        stmnt.setString(2,position.getName());
        stmnt.setInt(3,Integer.parseInt(position.getidDep()));
        stmnt.setDate(4, new java.sql.Date(myDate.getTime()));
        stmnt.setDate(5, new java.sql.Date(myDate2.getTime()));
        stmnt.execute();

    }


}