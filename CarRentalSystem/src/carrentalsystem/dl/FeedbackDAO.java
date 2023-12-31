package carrentalsystem.dl;

import carrentalsystem.connection.DatabaseConnection;
import carrentalsystem.exception.DAOException;
import carrentalsystem.pojo.Feedback;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class FeedbackDAO {
    public static void addFeedback(Feedback feedback) throws DAOException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("Insert into feedback(overall_rating, car_condition, feedback) values(?,?,?)");
            ps.setInt(1, feedback.getRating());
            ps.setInt(2, feedback.getCarCondition());
            ps.setString(3, feedback.getFeedback());
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }
    
    public static void addStaffReply(Feedback feedback) throws DAOException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("update feedback set Staff_reply=? where id=?");
            ps.setString(1, feedback.getStaffReply());
            ps.setInt(2, feedback.getId());
            System.out.println(ps.toString());
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public static void editFeedback(Feedback feedback) throws DAOException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("update feedback set overall_rating=?, car_condition=?, feedback=? where id=?");
            ps.setInt(1, feedback.getRating());
            ps.setInt(2, feedback.getCarCondition());
            ps.setString(3, feedback.getFeedback());
            ps.setInt(4, feedback.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public static void deleteFeedback(int id) throws DAOException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("delete from feedback where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public static ResultSet getAllFeedback() throws DAOException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from feedback");
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public static ResultSet getFeedbackByCustomerId(int customerId) throws DAOException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from feedback");
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }
    
}
