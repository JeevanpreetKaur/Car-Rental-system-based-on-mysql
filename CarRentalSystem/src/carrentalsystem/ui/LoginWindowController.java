package carrentalsystem.ui;

import carrentalsystem.dl.CustomerDAO;
import carrentalsystem.dl.DirectorDAO;
import carrentalsystem.dl.StaffDAO;
import carrentalsystem.exception.DAOException;
import carrentalsystem.pojo.Customer;
import carrentalsystem.pojo.Director;
import carrentalsystem.pojo.Staff;
import carrentalsystem.utility.DAOConstants;
import carrentalsystem.utility.Utility;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginWindowController implements Initializable {

    @FXML
    private Button btnRegister;
    @FXML
    private Label lblError;
    @FXML
    private Label lblUserName;

    private FXMLLoader fxmlLoader = new FXMLLoader();
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPwd2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void redirectToLoginHomeWindow(MouseEvent event) {
        fxmlLoader.setLocation(getClass().getResource("LoginHomeWindow.fxml"));
        Utility.redirectToWindow(event, fxmlLoader, "Login Home Window", DAOConstants.WINDOW_WIDTH, DAOConstants.WINDOW_HEIGHT);
    }

    @FXML
    private void btnLoginClicked(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            String title = stage.getTitle();

            String emailId = txtEmail.getText();
            String pwd = txtPwd2.getText();
            if (emailId.length() == 0 || pwd.length() == 0) {
                throw new DAOException(DAOConstants.ERROR_MISSING_FIELDS);
            }

            if (title.contains("Customer")) {
                Customer c = new Customer(emailId, pwd);
                if (CustomerDAO.loginCustomer(c)) {
                    fxmlLoader.setLocation(getClass().getResource(DAOConstants.CUSTOMER_HOME_WINDOW_FXML));

                    Utility.redirectToWindow(event, fxmlLoader, DAOConstants.CUSTOMER_HOME_WINDOW, DAOConstants.WINDOW_WIDTH, DAOConstants.HOME_WINDOW_HEIGHT);
                    CustomerHomeWindowController chwc = fxmlLoader.getController();
                    chwc.lblCustomerId.setText(CustomerDAO.getIdByEmail(emailId) + "");

                    return;
                }

            } else if (title.contains("Staff")) {
                Staff s = new Staff(emailId, pwd);
                if (StaffDAO.loginStaff(s)) {
                    fxmlLoader.setLocation(getClass().getResource(DAOConstants.STAFF_HOME_WINDOW_FXML));
                    Utility.redirectToWindow(event, fxmlLoader, DAOConstants.STAFF_HOME_WINDOW, DAOConstants.WINDOW_WIDTH, DAOConstants.HOME_WINDOW_HEIGHT);
                    StaffHomeWindowController chwc = fxmlLoader.getController();
                    chwc.lblStaffId.setText(StaffDAO.getIdByEmail(emailId) + "");

                    return;
                }

            } else if (title.contains("Director")) {
                Director d = new Director(emailId, pwd);
                if (DirectorDAO.loginDirector(d)) {
                    fxmlLoader.setLocation(getClass().getResource(DAOConstants.DIRECTOR_HOME_WINDOW_FXML));
                    Utility.redirectToWindow(event, fxmlLoader, DAOConstants.DIRECTOR_HOME_WINDOW, DAOConstants.WINDOW_WIDTH, DAOConstants.HOME_WINDOW_HEIGHT);
                    DirectorHomeWindowController chwc = fxmlLoader.getController();
                    chwc.lblDirectorId.setText(DirectorDAO.getIdByEmail(emailId) + "");

                    return;
                }
            }
            throw new DAOException(DAOConstants.ERROR_INVALID_CREDENTIALS);
        } catch (Exception ex) {
            System.out.println("LoginWindowController | btnLoginClicked | " + ex.getMessage());
            lblError.setText(ex.getMessage());
        }
    }

}
