package carrentalsystem.ui;

import carrentalsystem.dl.CustomerDAO;
import carrentalsystem.dl.DirectorDAO;
import carrentalsystem.exception.DAOException;
import carrentalsystem.pojo.Customer;
import carrentalsystem.pojo.Director;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RegisterWindowController implements Initializable {

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
    private TextField txtCPwd;
    @FXML
    public TextField txtSecretKey;
    @FXML
    private Button btnRegister;
    @FXML
    private Label lblError;
    private FXMLLoader fxmlLoader=new FXMLLoader();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }    

    @FXML
    private void redirectToHomeWindow(MouseEvent event) {
        fxmlLoader.setLocation(getClass().getResource("RegisterHomeWindow.fxml"));
        Utility.redirectToWindow(event, fxmlLoader, "Register Home Window", DAOConstants.WINDOW_WIDTH, DAOConstants.WINDOW_HEIGHT);
    }

    @FXML
    private void btnRegisterClicked(ActionEvent event) {
        try
        {
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        String title=stage.getTitle();
        
        String firstName=txtFN.getText();
        String lastName=txtLN.getText();
        String contactNo=txtCN.getText();
        String emailId=txtEmail.getText();
        String street=txtStreet.getText();
        String city=txtCity.getText();
        String province=txtProvince.getText();
        String zipcode=txtZC.getText();
        String pwd=txtPwd.getText();
        String cPwd=txtCPwd.getText();
        String secretKey=txtSecretKey.getText();
        if(firstName.length()==0 || lastName.length()==0 || contactNo.length()==0 || emailId.length()==0 || street.length()==0 || city.length()==0 || province.length()==0 || zipcode.length()==0 || pwd.length()==0 || cPwd.length()==0) throw new DAOException("Invalid Input, Please fill all the fields");
        
        if(!pwd.equals(cPwd)) throw new DAOException("Password and confrim Password did not match");
        
        if(title.contains("Customer"))
        {
            Customer c=new Customer(firstName,lastName,contactNo,emailId, street,city, province, zipcode, pwd);
            CustomerDAO.addCustomer(c);
            fxmlLoader.setLocation(getClass().getResource("LoginWindow.fxml"));
            Utility.redirectToWindow(event, fxmlLoader, DAOConstants.CUSTOMER_LOGIN_WINDOW, DAOConstants.WINDOW_WIDTH, DAOConstants.WINDOW_HEIGHT);

        }else if(title.contains("Director"))
        {
            if(secretKey.length()==0) throw new DAOException("Secret Key cannot be empty");
            if(!secretKey.equals(DAOConstants.SECRET_KEY)) throw new DAOException("Secret key did not matched");

            Director d=new Director(firstName,lastName,contactNo,emailId, street,city, province, zipcode, pwd);
            DirectorDAO.addDirector(d);
            fxmlLoader.setLocation(getClass().getResource("LoginWindow.fxml"));
            Utility.redirectToWindow(event, fxmlLoader, DAOConstants.DIRECTOR_LOGIN_WINDOW, DAOConstants.WINDOW_WIDTH, DAOConstants.WINDOW_HEIGHT);
        }

        }catch(Exception ex)
        {
            System.out.println("RegisterWindowController.java | btnRegisterClicked | "+ex.getMessage());
            lblError.setText(ex.getMessage());
        }
    }
    
}
