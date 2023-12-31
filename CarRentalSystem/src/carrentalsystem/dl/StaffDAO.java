package carrentalsystem.dl;

import carrentalsystem.connection.DatabaseConnection;
import carrentalsystem.exception.DAOException;
import carrentalsystem.pojo.Staff;
import carrentalsystem.utility.DAOConstants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class StaffDAO {

    public static void addStaff(Staff staff) throws DAOException {
        try {
            if (isEmailExists(staff.getEmailId())) {
                throw new DAOException(DAOConstants.ERROR_EMAIL_EXISTS);
            }

            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("Insert into staff(first_name, last_name, contact_number, email_id, street, city, province, zip_code, password) values(?,?,?,?,?,?,?,?,?)");
            ps.setString(1, staff.getFirstName());
            ps.setString(2, staff.getLastName());
            ps.setString(3, staff.getContactNumber());
            ps.setString(4, staff.getEmailId());
            ps.setString(5, staff.getStreet());
            ps.setString(6, staff.getCity());
            ps.setString(7, staff.getProvince());
            ps.setString(8, staff.getZipCode());
            ps.setString(9, staff.getPassword());
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public static void editStaffDetails(Staff staff) throws DAOException {
        try {

            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("update staff set first_name=?, last_name=?, contact_number=?, email_id=?, street=?, city=?, province=?, zip_code=?, password=? where id=?");
            ps.setString(1, staff.getFirstName());
            ps.setString(2, staff.getLastName());
            ps.setString(3, staff.getContactNumber());
            ps.setString(4, staff.getEmailId());
            ps.setString(5, staff.getStreet());
            ps.setString(6, staff.getCity());
            ps.setString(7, staff.getProvince());
            ps.setString(8, staff.getZipCode());
            ps.setString(9, staff.getPassword());
            ps.setInt(10, staff.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public static void deleteStaff(int id) throws DAOException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("delete from staff where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public static boolean loginStaff(Staff staff) throws DAOException {
        try {
            if (staff.getEmailId() == null || staff.getPassword() == null) {
                throw new DAOException("Invalid email or password");
            }

            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from staff where email_id=? and password=?");
            ps.setString(1, staff.getEmailId());
            ps.setString(2, staff.getPassword());
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

    public static ResultSet getAllStaff() throws DAOException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from staff");
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
            ps = conn.prepareStatement("select * from staff where email_id=?");
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

    public static ResultSet getDailyRentalReport(Date bookingDate) throws DAOException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("select cu.email_id, c.brand, c.model, c.price, cb.booking_date  from car_booking cb inner join customer cu inner join car c where cb.customer_id=cu.id and cb.car_id=c.id and booking_date=?;");
            ps.setDate(1, new java.sql.Date(bookingDate.getTime()));
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
            ps = conn.prepareStatement("select * from staff where email_id=?");
            ps.setString(1, emailId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("id");
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public static Staff getStaffById(int id) throws DAOException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from staff where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Staff c = new Staff(rs.getString("first_name"), rs.getString("last_name"), rs.getString("contact_number"), rs.getString("email_id"), rs.getString("street"), rs.getString("city"), rs.getString("province"), rs.getString("zip_code"), rs.getString("password"));
            return c;
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

}
