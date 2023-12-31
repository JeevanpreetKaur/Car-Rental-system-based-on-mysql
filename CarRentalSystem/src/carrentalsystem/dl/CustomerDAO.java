package carrentalsystem.dl;

import carrentalsystem.connection.DatabaseConnection;
import carrentalsystem.exception.DAOException;
import carrentalsystem.pojo.Customer;
import carrentalsystem.utility.DAOConstants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDAO {

    public static void addCustomer(Customer customer) throws DAOException {
        try {
            if (isEmailExists(customer.getEmailId())) {
                throw new DAOException(DAOConstants.ERROR_EMAIL_EXISTS);
            }

            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("Insert into customer(first_name, last_name, contact_number, email_id, street, city, province, zip_code, password) values(?,?,?,?,?,?,?,?,?)");
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getContactNumber());
            ps.setString(4, customer.getEmailId());
            ps.setString(5, customer.getStreet());
            ps.setString(6, customer.getCity());
            ps.setString(7, customer.getProvince());
            ps.setString(8, customer.getZipCode());
            ps.setString(9, customer.getPassword());
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public static void editCustomerDetails(Customer customer) throws DAOException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("update customer set first_name=?, last_name=?, contact_number=?, email_id=?, street=?, city=?, province=?, zip_code=?, password=? where id=?");
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getContactNumber());
            ps.setString(4, customer.getEmailId());
            ps.setString(5, customer.getStreet());
            ps.setString(6, customer.getCity());
            ps.setString(7, customer.getProvince());
            ps.setString(8, customer.getZipCode());
            ps.setString(9, customer.getPassword());
            ps.setInt(10, customer.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public static void deleteCustomer(int id) throws DAOException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("delete from customer where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public static boolean loginCustomer(Customer customer) throws DAOException {
        try {
            if (customer.getEmailId() == null || customer.getPassword() == null) {
                throw new DAOException("Invalid email or password");
            }

            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from customer where email_id=? and password=?");
            ps.setString(1, customer.getEmailId());
            ps.setString(2, customer.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public static ResultSet getAllCustomer() throws DAOException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from customer");
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public static boolean isEmailExists(String emailId) throws DAOException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from customer where email_id=?");
            ps.setString(1, emailId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }
    
    public static int getIdByEmail(String emailId) throws DAOException{
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from customer where email_id=?");
            ps.setString(1, emailId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("id");
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }        
    }
    
    public static Customer getCustomerById(int id) throws DAOException
    {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from customer where id=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            rs.next();
            Customer c=new Customer(rs.getString("first_name"), rs.getString("last_name"), rs.getString("contact_number"), rs.getString("email_id"), rs.getString("street"), rs.getString("city"), rs.getString("province"), rs.getString("zip_code"), rs.getString("password"));
            return c;
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }        
    }
}
