package carrentalsystem.dl;

import carrentalsystem.connection.DatabaseConnection;
import carrentalsystem.exception.DAOException;
import carrentalsystem.pojo.Customer;
import carrentalsystem.pojo.Director;
import carrentalsystem.utility.DAOConstants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class DirectorDAO {

    public static void addDirector(Director director) throws DAOException {
        try {
            if (isEmailExists(director.getEmailId())) {
                throw new DAOException(DAOConstants.ERROR_EMAIL_EXISTS);
            }

            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("Insert into director(first_name, last_name, contact_number, email_id, street, city, province, zip_code, password) values(?,?,?,?,?,?,?,?,?)");
            ps.setString(1, director.getFirstName());
            ps.setString(2, director.getLastName());
            ps.setString(3, director.getContactNumber());
            ps.setString(4, director.getEmailId());
            ps.setString(5, director.getStreet());
            ps.setString(6, director.getCity());
            ps.setString(7, director.getProvince());
            ps.setString(8, director.getZipCode());
            ps.setString(9, director.getPassword());
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public static void editDirectorDetails(Director director) throws DAOException {
        try {

            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("update director set first_name=?, last_name=?, contact_number=?, email_id=?, street=?, city=?, province=?, zip_code=?, password=? where id=?");
            ps.setString(1, director.getFirstName());
            ps.setString(2, director.getLastName());
            ps.setString(3, director.getContactNumber());
            ps.setString(4, director.getEmailId());
            ps.setString(5, director.getStreet());
            ps.setString(6, director.getCity());
            ps.setString(7, director.getProvince());
            ps.setString(8, director.getZipCode());
            ps.setString(9, director.getPassword());
            ps.setInt(10, director.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public static boolean loginDirector(Director director) throws DAOException {
        try {
            if (director.getEmailId() == null || director.getPassword() == null) {
                throw new DAOException("Invalid email or password");
            }

            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from director where email_id=? and password=?");
            ps.setString(1, director.getEmailId());
            ps.setString(2, director.getPassword());
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

    public static boolean isEmailExists(String emailId) throws DAOException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from director where email_id=?");
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

    public static ResultSet getMonthlyReport(int month) throws DAOException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("select cu.email_id, c.brand, c.model, c.price, cb.booking_date  from car_booking cb inner join customer cu inner join car c where cb.customer_id=cu.id and cb.car_id=c.id and month(booking_date)=?;");
            ps.setInt(1, month);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public static int getIdByEmail(String emailId) throws DAOException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from director where email_id=?");
            ps.setString(1, emailId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("id");
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public static Director getDirectorById(int id) throws DAOException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from director where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Director c = new Director(rs.getString("first_name"), rs.getString("last_name"), rs.getString("contact_number"), rs.getString("email_id"), rs.getString("street"), rs.getString("city"), rs.getString("province"), rs.getString("zip_code"), rs.getString("password"));
            return c;
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

}
