package carrentalsystem.pojo;

import java.util.Date;

public class CarBooking {
    private int id;
    private int customerId;
    private int carId;
    private String isPaymentReceived;
    private Date bookingDate;
    private String fromTime;
    private String toTime;

    public CarBooking() {
    }

    public CarBooking(int customerId, int carId, String isPaymentReceived, Date bookingDate, String fromTime, String toTime) {
        this.customerId = customerId;
        this.carId = carId;
        this.isPaymentReceived = isPaymentReceived;
        this.bookingDate = bookingDate;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public CarBooking(int id, int customerId, int carId, String isPaymentReceived, Date bookingDate, String fromTime, String toTime) {
        this.id = id;
        this.customerId = customerId;
        this.carId = carId;
        this.isPaymentReceived = isPaymentReceived;
        this.bookingDate = bookingDate;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getIsPaymentReceived() {
        return isPaymentReceived;
    }

    public void setIsPaymentReceived(String isPaymentReceived) {
        this.isPaymentReceived = isPaymentReceived;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    @Override
    public String toString() {
        return "CarBooking{" + "id=" + id + ", customerId=" + customerId + ", carId=" + carId + ", isPaymentReceived=" + isPaymentReceived + ", bookingDate=" + bookingDate + ", fromTime=" + fromTime + ", toTime=" + toTime + '}';
    }
    
    
}
