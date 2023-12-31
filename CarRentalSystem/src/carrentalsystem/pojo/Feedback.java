package carrentalsystem.pojo;

public class Feedback {
    private int id;
    private int rating;
    private int carCondition;
    private String feedback;
    private String staffReply;

    public Feedback() {
    }

    public Feedback(int rating, int carCondition, String feedback) {
        this.rating = rating;
        this.carCondition = carCondition;
        this.feedback = feedback;
    }

    public Feedback(int id, int rating, int carCondition, String feedback) {
        this.id = id;
        this.rating = rating;
        this.carCondition = carCondition;
        this.feedback = feedback;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getCarCondition() {
        return carCondition;
    }

    public void setCarCondition(int carCondition) {
        this.carCondition = carCondition;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getStaffReply() {
        return staffReply;
    }

    public void setStaffReply(String staffReply) {
        this.staffReply = staffReply;
    }

    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + ", rating=" + rating + ", carCondition=" + carCondition + ", feedback=" + feedback + ", staffReply=" + staffReply + '}';
    }

    
    
    
    
}
