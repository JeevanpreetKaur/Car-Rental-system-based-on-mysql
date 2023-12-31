package carrentalsystem.dl;

import carrentalsystem.connection.DatabaseConnection;
import carrentalsystem.exception.DAOException;
import carrentalsystem.pojo.Car;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class CarDAO {

    public static void addCar(Car car) throws DAOException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("Insert into car(brand, model, sitting_capacity, color, mileage, price) values(?,?,?,?,?,?)");
            ps.setString(1, car.getBrand());
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getSittingCapacity());
            ps.setString(4, car.getColor());
            ps.setFloat(5, car.getMileage());
            ps.setFloat(6, car.getPrice());
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public static void editCarDetails(Car car) throws DAOException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("update car set brand=?, model=?, sitting_capacity=?, color=?, mileage=?, price=? where id=?");
            ps.setString(1, car.getBrand());
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getSittingCapacity());
            ps.setString(4, car.getColor());
            ps.setFloat(5, car.getMileage());
            ps.setFloat(6, car.getPrice());
            ps.setInt(7, car.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public static void deleteCar(int id) throws DAOException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("delete from car where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

    public static ResultSet getAllCars() throws DAOException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from car");
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }
    
    public static ResultSet getAvailableCarsAccordingToDate(Date d) throws DAOException
    {
        try {
            Connection conn = DatabaseConnection.getConnection();
            System.out.println(d);
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from car where id not in (select car_id from car_booking where booking_date=?)");
            ps.setDate(1, new java.sql.Date(d.getTime()));
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage());
        }
    }

}
