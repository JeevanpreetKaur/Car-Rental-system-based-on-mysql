package carrentalsystem.ui;

import carrentalsystem.dl.CarDAO;
import carrentalsystem.dl.DirectorDAO;
import carrentalsystem.dl.StaffDAO;
import carrentalsystem.exception.DAOException;
import carrentalsystem.pojo.Director;
import carrentalsystem.pojo.Staff;
import carrentalsystem.utility.DAOConstants;
import carrentalsystem.utility.Utility;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class DirectorHomeWindowController implements Initializable {

    @FXML
    private Label lblUserName;
    @FXML
    private TextField txtFN;
    @FXML
    private TextField txtLN;
    @FXML
    private TextField txtCN;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtStreet;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtProvince;
    @FXML
    private TextField txtZC;
    @FXML
    private TextField txtPwd;
    @FXML
    private TableView<ObservableList> tblStaff;
    @FXML
    private Label lblError;
    @FXML
    private ChoiceBox<Integer> cbMonth;
    @FXML
    private TableView<ObservableList> tblMonthlyReport;
    @FXML
    public Label lblDirectorId;
    @FXML
    private TextField txtEditId;
    @FXML
    private TextField txtEditFN;
    @FXML
    private TextField txtEditLN;
    @FXML
    private TextField txtEditCN;
    @FXML
    private TextField txtEditEmail;
    @FXML
    private TextField txtEditStreet;
    @FXML
    private TextField txtEditCity;
    @FXML
    private TextField txtEditProvince;
    @FXML
    private TextField txtEditZip;
    @FXML
    private TextField txtEditPwd;
    @FXML
    private Label lblEditError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ResultSet rs = StaffDAO.getAllStaff();
            Utility.AddTableData(tblStaff, rs);
            populateChoiceBox();
            addingChangeListenerInDatePicker();
        } catch (Exception ex) {
            System.out.println("DirectorHomeWindowController | initialize |" + ex.getMessage());
            lblError.setText(ex.getMessage());
        }
    }

    private void addingChangeListenerInDatePicker() {
        cbMonth.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                try {
                    Integer month = cbMonth.getSelectionModel().getSelectedItem();
                    ResultSet rs = DirectorDAO.getMonthlyReport(month);
                    Utility.AddTableData(tblMonthlyReport, rs);
                } catch (Exception ex) {
                    System.out.println("CustomerHomeWindowController | datePickerTxtChanged |" + ex.getMessage());
                    lblError.setText(ex.getMessage());
                }

            }
        });
    }

    private void populateChoiceBox() {
        for (int i = 1; i <= 12; i++) {
            cbMonth.getItems().add(i);
        }
    }

    @FXML
    private void btnAddClicked(ActionEvent event) {
        try {
            lblError.setText("");
            String firstName = txtFN.getText();
            String lastName = txtLN.getText();
            String contactNo = txtCN.getText();
            String emailId = txtEmail.getText();
            String street = txtStreet.getText();
            String city = txtCity.getText();
            String province = txtProvince.getText();
            String zipcode = txtZC.getText();
            String pwd = txtPwd.getText();

            if (firstName.length() == 0 || lastName.length() == 0 || contactNo.length() == 0 || emailId.length() == 0 || street.length() == 0 || city.length() == 0 || province.length() == 0 || zipcode.length() == 0 || pwd.length() == 0) {
                throw new DAOException(DAOConstants.ERROR_INVALID_INPUT);
            }

            Staff s = new Staff(firstName, lastName, contactNo, emailId, city, street, province, zipcode, pwd);
            StaffDAO.addStaff(s);
            ResultSet rs = StaffDAO.getAllStaff();
            Utility.AddTableData(tblStaff, rs);
            lblError.setText("Staff Added Successfully");
        } catch (Exception ex) {
            System.out.println("Error DirectorHomeWindowController | btnAddClicked | " + ex.getMessage());
            lblError.setText(ex.getMessage());
        }
    }

    @FXML
    private void btnUpdateClicked(ActionEvent event) {
        try {
            lblError.setText("");
            int selectedIndex = tblStaff.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                ObservableList<String> selectedItem = tblStaff.getSelectionModel().getSelectedItem();
                Staff s = new Staff(Integer.parseInt(selectedItem.get(0)), txtFN.getText(), txtLN.getText(), txtCN.getText(), txtEmail.getText(), txtStreet.getText(), txtCity.getText(), txtProvince.getText(), txtZC.getText(), txtPwd.getText());
//                System.out.println(selectedItem);
//                System.out.println(s);
                StaffDAO.editStaffDetails(s);
                Utility.AddTableData(tblStaff, StaffDAO.getAllStaff());
                clearSelection();
                lblError.setText("Staff Details Updated Successfully");
            }
        } catch (Exception ex) {
            System.out.println("Error DirectorHomeWindowController | btnUpdateClicked | " + ex.getMessage());
            lblError.setText(ex.getMessage());
        }
    }

    @FXML
    private void btnDeleteClicked(ActionEvent event) {
        try {
            lblError.setText("");
            int selectedIndex = tblStaff.getSelectionModel().getSelectedIndex();
            ObservableList<String> selectedItem = null;
            if (selectedIndex >= 0) {
                selectedItem = tblStaff.getSelectionModel().getSelectedItem();
                StaffDAO.deleteStaff(Integer.parseInt(selectedItem.get(0)));
                Utility.AddTableData(tblStaff, StaffDAO.getAllStaff());
                clearSelection();
                lblError.setText("Staff Deleted Successfully");
            } else {
                throw new DAOException(DAOConstants.ERROR_INVALID_DELETE);
            }
        } catch (Exception ex) {
            System.out.println("Error DirectorHomeWindowController | btnDeleteClicked | " + ex.getMessage());
            lblError.setText(ex.getMessage());
        }
    }

    @FXML
    private void btnCancelClicked(ActionEvent event) {
        try {
            lblError.setText("");
            clearSelection();
        } catch (Exception ex) {
            System.out.println("Error DirectorHomeWindowController | btnUpdateClicked | " + ex.getMessage());
            lblError.setText(ex.getMessage());
        }
    }

    @FXML
    private void btnLogoutClicked(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(DAOConstants.LOGIN_WINDOW_FXML));
        Utility.redirectToWindow(event, fxmlLoader, DAOConstants.DIRECTOR_LOGIN_WINDOW, DAOConstants.WINDOW_WIDTH, DAOConstants.WINDOW_HEIGHT);

    }

    private void fillSelection(ObservableList<String> selectedItem) throws Exception {
        txtFN.setText(selectedItem.get(1));
        txtLN.setText(selectedItem.get(2));
        txtCN.setText(selectedItem.get(3));
        txtEmail.setText(selectedItem.get(4));
        txtStreet.setText(selectedItem.get(5));
        txtCity.setText(selectedItem.get(6));
        txtProvince.setText(selectedItem.get(7));
        txtZC.setText(selectedItem.get(8));
        txtPwd.setText(selectedItem.get(9));
    }

    private void clearSelection() throws Exception {
        txtFN.setText("");
        txtLN.setText("");
        txtCN.setText("");
        txtEmail.setText("");
        txtStreet.setText("");
        txtCity.setText("");
        txtProvince.setText("");
        txtZC.setText("");
        txtPwd.setText("");
    }

    @FXML
    private void tblMouseClicked(MouseEvent event) {
        try {
            lblError.setText("");
            int selectedIndex = tblStaff.getSelectionModel().getSelectedIndex();
            ObservableList<String> selectedItem = null;
            if (selectedIndex >= 0) {
                selectedItem = tblStaff.getSelectionModel().getSelectedItem();
//                System.out.println(selectedItem.toString());
//                System.out.println(selectedItem.get(0));
//                // Fill all the fields with selected Data
                fillSelection(selectedItem);
            } else {
                clearSelection();
            }
        } catch (Exception ex) {
            System.out.println("Error DirectorHomeWindowController | btnUpdateClicked | " + ex.getMessage());
            lblError.setText(ex.getMessage());
        }

    }

    @FXML
    private void btnEditUpdateRecords(ActionEvent event) {
        try {
            if (txtEditId.getLength() == 0 || txtEditFN.getLength() == 0 || txtEditLN.getLength() == 0 || txtEditCN.getLength() == 0 || txtEditEmail.getLength() == 0 || txtEditStreet.getLength() == 0 || txtEditCity.getLength() == 0 || txtEditProvince.getLength() == 0 || txtEditZip.getLength() == 0 || txtEditPwd.getLength() == 0) {
                throw new DAOException(DAOConstants.ERROR_INVALID_INPUT);
            }

            Director d = new Director(Integer.parseInt(txtEditId.getText()), txtEditFN.getText(), txtEditLN.getText(), txtEditCN.getText(), txtEditEmail.getText(), txtEditStreet.getText(), txtEditCity.getText(), txtEditProvince.getText(), txtEditZip.getText(), txtEditPwd.getText());

            DirectorDAO.editDirectorDetails(d);
            lblEditError.setText(DAOConstants.SUCCESS_EDIT);
        } catch (Exception ex) {
            System.out.println("CustomerHomeWindowController | btnEditUpdateRecords |" + ex.getMessage());
            lblEditError.setText(ex.getMessage());
        }
    }

    @FXML
    private void editProfileTabSelectionChanged(Event event) {
                try
        {
            Director c=DirectorDAO.getDirectorById(Integer.parseInt(lblDirectorId.getText()));
            txtEditId.setText(lblDirectorId.getText());
            txtEditFN.setText(c.getFirstName());
            txtEditLN.setText(c.getLastName());
            txtEditCN.setText(c.getContactNumber());
            txtEditEmail.setText(c.getEmailId());
            txtEditStreet.setText(c.getStreet());
            txtEditCity.setText(c.getCity());
            txtEditProvince.setText(c.getProvince());
            txtEditZip.setText(c.getZipCode());
            txtEditPwd.setText(c.getPassword());
            lblEditError.setText("");
        }catch(Exception ex)
        {
            System.out.println("DirectorHomeWindowController | onTabSelectionChanged |" + ex.getMessage());
            lblEditError.setText(ex.getMessage());
        }
    }

}
