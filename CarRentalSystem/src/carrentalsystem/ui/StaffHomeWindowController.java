package carrentalsystem.ui;

import carrentalsystem.dl.CarDAO;
import carrentalsystem.dl.FeedbackDAO;
import carrentalsystem.dl.StaffDAO;
import carrentalsystem.exception.DAOException;
import carrentalsystem.pojo.Car;
import carrentalsystem.pojo.Feedback;
import carrentalsystem.pojo.Staff;
import carrentalsystem.utility.DAOConstants;
import carrentalsystem.utility.Utility;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class StaffHomeWindowController implements Initializable {

    @FXML
    private Label lblUserName;
    @FXML
    private TextField txtBrand;
    @FXML
    private TableView<ObservableList> tblCars;
    @FXML
    private TextField txtModel;
    @FXML
    private TextField txtSC;
    @FXML
    private TextField txtColor;
    @FXML
    private TextField txtMileage;
    @FXML
    private TextField txtPrice;
    @FXML
    private Label lblError;
    @FXML
    private TableView<ObservableList> tblFeedback;
    @FXML
    private TextField txtFeedbackId;
    @FXML
    private TextField txtFeedback;
    @FXML
    private Label lblFeedbackError;
    private DatePicker datePickerReport;
    @FXML
    private TableView<ObservableList> tblReport;
    @FXML
    private DatePicker txtBookingDate;
    @FXML
    private Label lblReportError;
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
    @FXML
    public Label lblStaffId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            addingChangeListenerInDatePicker();
            setTodayDateInDatePicker();
            Utility.AddTableData(tblCars, CarDAO.getAllCars());
            Utility.AddTableData(tblFeedback, FeedbackDAO.getAllFeedback());
        } catch (Exception ex) {
            System.out.println("DirectorHomeWindowController | initialize |" + ex.getMessage());
            lblError.setText(ex.getMessage());
        }
    }

    @FXML
    private void tblMouseClicked(MouseEvent event) {
        try {
            lblError.setText("");
            int selectedIndex = tblCars.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                ObservableList<String> selectedItem = tblCars.getSelectionModel().getSelectedItem();
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
    private void btnAddClicked(ActionEvent event) {
        try {
            lblError.setText("");
            String brand = txtBrand.getText();
            String model = txtModel.getText();
            String sc = txtSC.getText();
            String color = txtColor.getText();
            String mileage = txtMileage.getText();
            String price = txtPrice.getText();

            if (brand.length() == 0 || model.length() == 0 || sc.length() == 0 || color.length() == 0 || mileage.length() == 0 || price.length() == 0) {
                throw new DAOException(DAOConstants.ERROR_INVALID_INPUT);
            }

            if (!Utility.isInt(sc) || !Utility.isFloat(mileage) || !Utility.isFloat(price)) {
                throw new DAOException(DAOConstants.ERROR_NUMBER_FIELDS);
            }

            Car car = new Car(brand, model, Integer.parseInt(sc), color, Float.parseFloat(mileage), Float.parseFloat(price));
            CarDAO.addCar(car);
            Utility.AddTableData(tblCars, CarDAO.getAllCars());
            lblError.setText("Car Added Successfully");
        } catch (Exception ex) {
            System.out.println("Error StaffHomeWindowController | btnAddClicked | " + ex.getMessage());
            lblError.setText(ex.getMessage());
        }
    }

    @FXML
    private void btnUpdateClicked(ActionEvent event) {
        try {
            lblError.setText("");
            int selectedIndex = tblCars.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                String brand = txtBrand.getText();
                String model = txtModel.getText();
                String sc = txtSC.getText();
                String color = txtColor.getText();
                String mileage = txtMileage.getText();
                String price = txtPrice.getText();

                if (brand.length() == 0 || model.length() == 0 || sc.length() == 0 || color.length() == 0 || mileage.length() == 0 || price.length() == 0) {
                    throw new DAOException(DAOConstants.ERROR_INVALID_INPUT);
                }

                if (!Utility.isInt(sc) || !Utility.isFloat(mileage) || !Utility.isFloat(price)) {
                    throw new DAOException(DAOConstants.ERROR_NUMBER_FIELDS);
                }
                ObservableList<String> selectedItem = tblCars.getSelectionModel().getSelectedItem();

                Car c = new Car(Integer.parseInt(selectedItem.get(0)), brand, model, Integer.parseInt(sc), color, Float.parseFloat(mileage), Float.parseFloat(price));

                CarDAO.editCarDetails(c);
                Utility.AddTableData(tblCars, CarDAO.getAllCars());
                clearSelection();
                lblError.setText("Car Details Updated Successfully");
            }
        } catch (Exception ex) {
            System.out.println("Error StaffHomeWindowController | btnUpdateClicked | " + ex.getMessage());
            lblError.setText(ex.getMessage());
        }
    }

    @FXML
    private void btnDeleteClicked(ActionEvent event) {
        try {
            lblError.setText("");
            int selectedIndex = tblCars.getSelectionModel().getSelectedIndex();
            ObservableList<String> selectedItem = null;
            if (selectedIndex >= 0) {
                selectedItem = tblCars.getSelectionModel().getSelectedItem();
                CarDAO.deleteCar(Integer.parseInt(selectedItem.get(0)));
                Utility.AddTableData(tblCars, CarDAO.getAllCars());
                clearSelection();
                lblError.setText("Car Deleted Successfully");
            } else {
                throw new DAOException(DAOConstants.ERROR_INVALID_DELETE);
            }
        } catch (Exception ex) {
            System.out.println("Error StaffHomeWindowController | btnDeleteClicked | " + ex.getMessage());
            lblError.setText(ex.getMessage());
        }
    }

    @FXML
    private void btnCancelClicked(ActionEvent event) {
        try {
            lblError.setText("");
            clearSelection();
        } catch (Exception ex) {
            System.out.println("Error StaffHomeWindowController | btnUpdateClicked | " + ex.getMessage());
            lblError.setText(ex.getMessage());
        }
    }

    @FXML
    private void btnLogoutClicked(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(DAOConstants.LOGIN_WINDOW_FXML));
        Utility.redirectToWindow(event, fxmlLoader, DAOConstants.STAFF_LOGIN_WINDOW, DAOConstants.WINDOW_WIDTH, DAOConstants.WINDOW_HEIGHT);
    }

    private void fillSelection(ObservableList<String> selectedItem) throws Exception {
        txtBrand.setText(selectedItem.get(1));
        txtModel.setText(selectedItem.get(2));
        txtSC.setText(selectedItem.get(3));
        txtColor.setText(selectedItem.get(4));
        txtMileage.setText(selectedItem.get(5));
        txtPrice.setText(selectedItem.get(6));
    }

    private void clearSelection() throws Exception {
        txtBrand.setText("");
        txtModel.setText("");
        txtSC.setText("");
        txtColor.setText("");
        txtMileage.setText("");
        txtPrice.setText("");
    }

    @FXML
    private void btnSubmitFeedbackReplyOnClick(ActionEvent event) {
        try {
            int selectedIndex = tblFeedback.getSelectionModel().getSelectedIndex();
            if (selectedIndex < 0) {
                throw new DAOException(DAOConstants.ERROR_INVALID_SELECTION);
            }
            if (txtFeedback.getLength() == 0) {
                throw new DAOException(DAOConstants.ERROR_INVALID_INPUT);
            }
            Feedback f = new Feedback();
            f.setId(Integer.parseInt(txtFeedbackId.getText()));
            f.setStaffReply(txtFeedback.getText());
            System.out.println(f);
            FeedbackDAO.addStaffReply(f);
            lblFeedbackError.setText(DAOConstants.SUCCESS_EDIT);
            Utility.AddTableData(tblFeedback, FeedbackDAO.getAllFeedback());
        } catch (Exception ex) {
            System.out.println("Error DirectorHomeWindowController | btnSubmitFeedbackReplyOnClick | " + ex.getMessage());
            lblFeedbackError.setText(ex.getMessage());
        }
    }

    @FXML
    private void tblFeedbackMouseClicked(MouseEvent event) {
        try {
            lblFeedbackError.setText("");
            int selectedIndex = tblFeedback.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                txtFeedbackId.setText(tblFeedback.getSelectionModel().getSelectedItem().get(0).toString());
            }
        } catch (Exception ex) {
            System.out.println("Error DirectorHomeWindowController | tblFeedbackMouseClicked | " + ex.getMessage());
            lblFeedbackError.setText(ex.getMessage());
        }
    }

    private void setTodayDateInDatePicker() {
        txtBookingDate.setValue(LocalDate.now().plusDays(1));
        txtBookingDate.setValue(LocalDate.now(ZoneId.systemDefault()));
    }

    private Date convertLocalDateToDate(LocalDate ld) {
        return new Date(ld.getYear() - 1900, ld.getMonthValue() - 1, ld.getDayOfMonth());
    }

    private void addingChangeListenerInDatePicker() {
        txtBookingDate.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                try {
                    LocalDate ld = txtBookingDate.getValue();
                    ResultSet rs = StaffDAO.getDailyRentalReport(convertLocalDateToDate(ld));
                    Utility.AddTableData(tblReport, rs);
                } catch (Exception ex) {
                    System.out.println("StaffHomeWindowController | addingChangeListenerInDatePicker |" + ex.getMessage());
                    lblReportError.setText(ex.getMessage());
                }

            }
        });
    }

    @FXML
    private void btnEditUpdateRecords(ActionEvent event) {
        try {
            if (txtEditId.getLength() == 0 || txtEditFN.getLength() == 0 || txtEditLN.getLength() == 0 || txtEditCN.getLength() == 0 || txtEditEmail.getLength() == 0 || txtEditStreet.getLength() == 0 || txtEditCity.getLength() == 0 || txtEditProvince.getLength() == 0 || txtEditZip.getLength() == 0 || txtEditPwd.getLength() == 0) {
                throw new DAOException(DAOConstants.ERROR_INVALID_INPUT);
            }

            Staff c = new Staff(Integer.parseInt(txtEditId.getText()), txtEditFN.getText(), txtEditLN.getText(), txtEditCN.getText(), txtEditEmail.getText(), txtEditStreet.getText(), txtEditCity.getText(), txtEditProvince.getText(), txtEditZip.getText(), txtEditPwd.getText());

            StaffDAO.editStaffDetails(c);
            lblEditError.setText(DAOConstants.SUCCESS_EDIT);
        } catch (Exception ex) {
            System.out.println("StaffHomeWindowController | btnEditUpdateRecords |" + ex.getMessage());
            lblEditError.setText(ex.getMessage());
        }
    }

    @FXML
    private void onEdtTabSelectionChanged(Event event) {
        try {
            Staff c = StaffDAO.getStaffById(Integer.parseInt(lblStaffId.getText()));
            txtEditId.setText(lblStaffId.getText());
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
        } catch (Exception ex) {
            System.out.println("CustomerHomeWindowController | onTabSelectionChanged |" + ex.getMessage());
            lblEditError.setText(ex.getMessage());
        }
    }

}
