package carrentalsystem.dl;

import carrentalsystem.connection.DatabaseConnection;
import carrentalsystem.exception.DAOException;
import carrentalsystem.pojo.CarBooking;
import carrentalsystem.utility.DAOConstants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;

public class CarBookingDAO {

    public static void bookCar(CarBooking carBooking) throws DAOException {
        try {
            if(!checkCarAvailable(carBooking.getCarId(), new Date(carBooking.getBookingDate().getTime())))
                throw new DAOException(DAOConstants.ERROR_CAR_NOT_AVAILABLE);
            
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps= conn.prepareStatement("insert into car_booking(customer_id, car_id, is_payment_received, booking_date, from_time, to_time) values(?,?,?,?,?,?)");
            ps.setInt(1, carBooking.getCustomerId());
            ps.setInt(2, carBooking.getCarId());
            ps.setString(3, carBooking.getIsPaymentReceived());
            ps.setDate(4, new Date(carBooking.getBookingDate().getTime()));
            ps.setString(5, carBooking.getFromTime());
            ps.setString(6, carBooking.getToTime());
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }
    
    public static boolean checkCarAvailable(int carId, Date d) throws DAOException
    {
        try {            
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            // Check if car is available in that day
            ps=conn.prepareStatement("select * from car where id in (select car_id from car_booking where booking_date=? and car_id=?)");
            ps.setDate(1, d);
            ps.setInt(2, carId);
            ResultSet rs=ps.executeQuery();
            if(!rs.next()) return true;
            else return false;
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }
    
    public static ResultSet getCustomerBookingHistory(int customerId) throws DAOException
    {
        try {            
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps=conn.prepareStatement("select c.model, c.sitting_capacity, c.mileage, c.price, cb.booking_date, cb.from_time, cb.to_time  from car c inner join car_booking cb where c.id=cb.car_id and cb.customer_id=?");
            ps.setInt(1, customerId);
            return ps.executeQuery();
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }        
    }
    
    public static ResultSet getAllCustomersBookingHistory() throws DAOException
    {
        try { 
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps=conn.prepareStatement("select c.model, c.sitting_capacity, c.mileage, c.price, cb.booking_date, cb.from_time, cb.to_time  from car c inner join car_booking cb where c.id=cb.car_id");
            return ps.executeQuery();
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }        
    }

}
