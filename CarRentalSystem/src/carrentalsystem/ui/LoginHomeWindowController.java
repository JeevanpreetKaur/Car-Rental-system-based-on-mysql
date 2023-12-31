package carrentalsystem.ui;

import carrentalsystem.utility.DAOConstants;
import carrentalsystem.utility.Utility;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public class LoginHomeWindowController implements Initializable {

    private FXMLLoader fxmlLoader=new FXMLLoader();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void redirectToDirectorLoginWindow(ActionEvent event) {
        fxmlLoader.setLocation(getClass().getResource("LoginWindow.fxml"));
        Utility.redirectToWindow(event, fxmlLoader, "Director Login Window", DAOConstants.WINDOW_WIDTH, DAOConstants.WINDOW_HEIGHT);
    }

    @FXML
    private void redirectToStaffLoginWindow(ActionEvent event) {
        fxmlLoader.setLocation(getClass().getResource("LoginWindow.fxml"));
        Utility.redirectToWindow(event, fxmlLoader, "Staff Login Window", DAOConstants.WINDOW_WIDTH, DAOConstants.WINDOW_HEIGHT);
    }

    @FXML
    private void redirectToCustomerLoginWindow(ActionEvent event) {
        fxmlLoader.setLocation(getClass().getResource("LoginWindow.fxml"));
        Utility.redirectToWindow(event, fxmlLoader, "Customer Login Window", DAOConstants.WINDOW_WIDTH, DAOConstants.WINDOW_HEIGHT);
    }

    @FXML
    private void redirectToRegisterHomeWindow(MouseEvent event) {
        fxmlLoader.setLocation(getClass().getResource("RegisterHomeWindow.fxml"));
        Utility.redirectToWindow(event, fxmlLoader, "Register Home Window", DAOConstants.WINDOW_WIDTH, DAOConstants.WINDOW_HEIGHT);
    }
    
}
