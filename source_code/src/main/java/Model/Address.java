package Model;

public class Address {
    private int id;
    private int userId;
    private String street;
    private String city;
    private String province;
    private String country;
    private String zip;
    private String phone;

    public Address(int id, int userId, String street, String city, String province, String country, String zip, String phone) {
        this.id = id;
        this.userId = userId;
        this.street = street;
        this.city = city;
        this.province = province;
        this.country = country;
        this.zip = zip;
        this.phone = phone;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getProvince() { return province; }
    public String getCountry() { return country; }
    public String getZip() { return zip; }
    public String getPhone() { return phone; }
    public void setStreet(String street) { this.street = street; }
    public void setCity(String city) { this.city = city; }
    public void setProvince(String province) { this.province = province; }
    public void setCountry(String country) { this.country = country; }
    public void setZip(String zip) { this.zip = zip; }
    public void setPhone(String phone) { this.phone = phone; }

}
