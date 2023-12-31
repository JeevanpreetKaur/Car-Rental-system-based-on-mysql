package carrentalsystem.pojo;

public class Staff {
    private int id;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String emailId;
    private String street;
    private String city;
    private String province;
    private String zipCode;
    private String password;

    public Staff() {
    }

    public Staff(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
    }

    public Staff(int id, String firstName, String lastName, String contactNumber, String emailId, String street, String city, String province, String zipCode, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.emailId = emailId;
        this.street = street;
        this.city = city;
        this.province = province;
        this.zipCode = zipCode;
        this.password = password;
    }

    public Staff(String firstName, String lastName, String contactNumber, String emailId, String street, String city, String province, String zipCode, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.emailId = emailId;
        this.street = street;
        this.city = city;
        this.province = province;
        this.zipCode = zipCode;
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Staff{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", contactNumber=" + contactNumber + ", emailId=" + emailId + ", street=" + street + ", city=" + city + ", province=" + province + ", zipCode=" + zipCode + ", password=" + password + '}';
    }

    
}
