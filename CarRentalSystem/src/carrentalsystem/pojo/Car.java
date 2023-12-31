package carrentalsystem.pojo;

public class Car {
    private int id;
    private String brand;
    private String model;
    private int sittingCapacity;
    private String color;
    private float mileage;
    private float price;

    public Car() {
    }

    public Car(String brand, String model, int sittingCapacity, String color, float mileage, float price) {
        this.brand = brand;
        this.model = model;
        this.sittingCapacity = sittingCapacity;
        this.color = color;
        this.mileage = mileage;
        this.price = price;
    }

    public Car(int id, String brand, String model, int sittingCapacity, String color, float mileage, float price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.sittingCapacity = sittingCapacity;
        this.color = color;
        this.mileage = mileage;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSittingCapacity() {
        return sittingCapacity;
    }

    public void setSittingCapacity(int sittingCapacity) {
        this.sittingCapacity = sittingCapacity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    
    
}
