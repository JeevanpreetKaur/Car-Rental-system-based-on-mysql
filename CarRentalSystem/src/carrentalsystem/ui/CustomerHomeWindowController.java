package carrentalsystem.ui;

import carrentalsystem.dl.CarBookingDAO;
import carrentalsystem.dl.CarDAO;
import carrentalsystem.dl.CustomerDAO;
import carrentalsystem.dl.FeedbackDAO;
import carrentalsystem.exception.DAOException;
import carrentalsystem.pojo.CarBooking;
import carrentalsystem.pojo.Customer;
import carrentalsystem.pojo.Feedback;
import carrentalsystem.utility.DAOConstants;
import carrentalsystem.utility.Utility;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import java.sql.ResultSet;
import java.time.Month;
import javafx.beans.value.ChangeListener;
import javafx.scene.input.KeyEvent;
import javafx.beans.value.*;
import javafx.event.Event;

public class CustomerHomeWindowController implements Initializable {

    @FXML
    private TableView<ObservableList> tblCars;
    @FXML
    private Label lblError;
    @FXML
    private DatePicker txtBookingDate;
    @FXML
    private TextField txtFrom;
    @FXML
    private TextField txtTo;
    @FXML
    public Label lblCustomerId;
    @FXML
    private TableView<ObservableList> tblBookingHistory;
    @FXML
    private TextField txtOverallRating;
    @FXML
    private TextField txtCarCondition;
    @FXML
    private TextField txtFeedback;
    @FXML
    private Label lblFeedbackError;
    @FXML
    private Label lblHistoryError;
    @FXML
    private TableView<ObservableList> tblFeedback;
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
            addingChangeListenerInDatePicker();
            setTodayDateInDatePicker();
            Utility.AddTableData(tblFeedback, FeedbackDAO.getAllFeedback());
        } catch (Exception ex) {
            System.out.println("CustomerHomeWindowController | initialize |" + ex.getMessage());
            lblError.setText(ex.getMessage());
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
                    if (ld.compareTo(LocalDate.now(ZoneId.systemDefault())) < 0) {
                        setTodayDateInDatePicker();
                        throw new DAOException(DAOConstants.ERROR_INVALID_DATE);
                    }
                    ResultSet rs = CarDAO.getAvailableCarsAccordingToDate(convertLocalDateToDate(ld));
                    Utility.AddTableData(tblCars, rs);
                } catch (Exception ex) {
                    System.out.println("CustomerHomeWindowController | datePickerTxtChanged |" + ex.getMessage());
                    lblError.setText(ex.getMessage());
                }

            }
        });
    }

    @FXML
    private void tblMouseClicked(MouseEvent event) {
    }

    @FXML
    private void btnBookClicked(ActionEvent event) {
        try {
            if (tblCars.getSelectionModel().getSelectedIndex() < 0) {
                throw new DAOException("Booking Error, Please select a car firstly before booking");
            }
            if (txtBookingDate.getValue() == null || txtFrom.getLength() == 0 || txtTo.getLength() == 0) {
                throw new DAOException("Please enter booking_date, from_time and to_time before booking a Car ");
            }

            int carId = Integer.parseInt(tblCars.getSelectionModel().getSelectedItem().get(0).toString());
            int customerId = Integer.parseInt(lblCustomerId.getText());
            CarBooking cb = new CarBooking(customerId, carId, "Y", convertLocalDateToDate(txtBookingDate.getValue()), txtFrom.getText(), txtTo.getText());
            CarBookingDAO.bookCar(cb);
            txtFrom.setText("");
            txtTo.setText("");
            lblError.setText("Booking Confirmed!!!");
            setTodayDateInDatePicker();
        } catch (Exception ex) {
            System.out.println("CustomerHomeWindowController | btnBookClicked |" + ex.getMessage());
            lblError.setText(ex.getMessage());
        }
    }

    @FXML
    private void btnLogoutClicked(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(DAOConstants.LOGIN_WINDOW_FXML));
        Utility.redirectToWindow(event, fxmlLoader, DAOConstants.CUSTOMER_LOGIN_WINDOW, DAOConstants.WINDOW_WIDTH, DAOConstants.WINDOW_HEIGHT);
    }

    private void datePickerKeyTyped(KeyEvent event) {
    }

    @FXML
    private void btnFeedbackClicked(ActionEvent event) {
        try {
            if (txtOverallRating.getLength() == 0 || txtCarCondition.getLength() == 0 || txtFeedback.getLength() == 0) {
                throw new DAOException(DAOConstants.ERROR_INVALID_INPUT);
            }

            int rating = Integer.parseInt(txtOverallRating.getText());
            int carCondition = Integer.parseInt(txtCarCondition.getText());
            Feedback f = new Feedback(rating, carCondition, txtFeedback.getText());
            FeedbackDAO.addFeedback(f);
            txtOverallRating.setText("");
            txtCarCondition.setText("");
            txtFeedback.setText("");
            lblFeedbackError.setText("Feedback sent!!!");
            Utility.AddTableData(tblFeedback, FeedbackDAO.getAllFeedback());

        } catch (Exception ex) {
            System.out.println("CustomerHomeWindowController | btnRefreshButtonClicked |" + ex.getMessage());
            lblFeedbackError.setText(ex.getMessage());
        }
    }

    @FXML
    private void btnRefreshButtonClicked(ActionEvent event) {
        try {
            Utility.AddTableData(tblBookingHistory, CarBookingDAO.getCustomerBookingHistory(Integer.parseInt(lblCustomerId.getText())));
        } catch (Exception ex) {
            System.out.println("CustomerHomeWindowController | btnRefreshButtonClicked |" + ex.getMessage());
            lblHistoryError.setText(ex.getMessage());
        }
    }

    @FXML
    private void btnEditUpdateRecords(ActionEvent event) {
        try
        {
            if(txtEditId.getLength()==0 || txtEditFN.getLength()==0 || txtEditLN.getLength()==0 || txtEditCN.getLength()==0 || txtEditEmail.getLength()==0 || txtEditStreet.getLength()==0 || txtEditCity.getLength()==0 || txtEditProvince.getLength()==0 || txtEditZip.getLength()==0 || txtEditPwd.getLength()==0)
                throw new DAOException(DAOConstants.ERROR_INVALID_INPUT);
            
            Customer c=new Customer(Integer.parseInt(txtEditId.getText()), txtEditFN.getText(), txtEditLN.getText(), txtEditCN.getText(), txtEditEmail.getText(), txtEditStreet.getText(), txtEditCity.getText(), txtEditProvince.getText(), txtEditZip.getText(), txtEditPwd.getText());
            
            CustomerDAO.editCustomerDetails(c);
            lblEditError.setText(DAOConstants.SUCCESS_EDIT);
        }catch(Exception ex)
        {
            System.out.println("CustomerHomeWindowController | btnEditUpdateRecords |" + ex.getMessage());
            lblEditError.setText(ex.getMessage());            
        }
    }

    @FXML
    private void onTabSelectionChanged(Event event) {
        try
        {
            Customer c=CustomerDAO.getCustomerById(Integer.parseInt(lblCustomerId.getText()));
            txtEditId.setText(lblCustomerId.getText());
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
            System.out.println("CustomerHomeWindowController | onTabSelectionChanged |" + ex.getMessage());
            lblEditError.setText(ex.getMessage());
        }
    }

    @FXML
    private void onBookingTabSelectionChanged(Event event) {
        fillBookingHistoryTable();
    }

    private void fillBookingHistoryTable()
    {
        try
        {
        Utility.AddTableData(tblBookingHistory, CarBookingDAO.getCustomerBookingHistory(Integer.parseInt(lblCustomerId.getText())));
        }catch(Exception ex)
        {
            System.out.println("CustomerHomeWindowController | fillBookingHistoryTable |" + ex.getMessage());
            lblHistoryError.setText(ex.getMessage());            
        }
    }
}
