package carrentalsystem.utility;

import carrentalsystem.dl.StaffDAO;
import carrentalsystem.exception.DAOException;
import java.io.IOException;
import java.sql.ResultSet;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Utility {

    public static boolean isNumeric(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    
    public static boolean isInt(String s){
        try
        {
            int a=Integer.parseInt(s);
            return true;
        }catch(Exception ex)
        {
            return false;
        }
    }
    
    public static boolean isFloat(String s){
        try
        {
            float a=Float.parseFloat(s);
            return true;
        }catch(Exception ex)
        {
            return false;
        }
    }
    
    public static void redirectToWindow(ActionEvent event, FXMLLoader fxmlLoader, String title, int width, int height) {
        Parent root;
        try {
            root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, width, height));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void redirectToWindow(MouseEvent event, FXMLLoader fxmlLoader, String title, int width, int height) {
        Parent root;
        try {
            root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, width, height));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void AddTableData(TableView<ObservableList> tblView, ResultSet rs) throws DAOException {
        try {
            tblView.getItems().clear();
            tblView.getColumns().clear();
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return (param.getValue().get(j)==null)?null:new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tblView.getColumns().addAll(col);
            }

            ObservableList<ObservableList> data = FXCollections.observableArrayList();
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            tblView.setItems(data);
        } catch (Exception ex) {
            throw new DAOException("Utility | "+ex.getMessage());
        }

    }

}
