package carrentalsystem.ui;

import carrentalsystem.utility.DAOConstants;
import carrentalsystem.utility.Utility;
import java.io.IOException;
import java.net.URL;
import java.util.ListResourceBundle;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RegisterHomeWindowController implements Initializable {
    private FXMLLoader fxmlLoader = new FXMLLoader();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnRedirectToDirectorRegistrationWindow(ActionEvent event) {
        fxmlLoader.setLocation(getClass().getResource("RegisterWindow.fxml"));
        Utility.redirectToWindow(event, fxmlLoader, "Director Register Window", DAOConstants.WINDOW_WIDTH, DAOConstants.WINDOW_HEIGHT);
    }

    @FXML
    private void btnRedirectToCustomerRegistrationWindow(ActionEvent event) {
        fxmlLoader.setLocation(getClass().getResource("RegisterWindow.fxml"));
        Utility.redirectToWindow(event, fxmlLoader, "Customer Register Window", DAOConstants.WINDOW_WIDTH, DAOConstants.WINDOW_HEIGHT);
        RegisterWindowController r=fxmlLoader.getController();
        r.txtSecretKey.setDisable(true);

        
    }

    @FXML
    private void btnRedirectToLoginHomeWindow(MouseEvent event) {
        fxmlLoader.setLocation(getClass().getResource("LoginHomeWindow.fxml"));
        Utility.redirectToWindow(event, fxmlLoader, "Login Home Window", DAOConstants.WINDOW_WIDTH, DAOConstants.WINDOW_HEIGHT);
    }
    
}

