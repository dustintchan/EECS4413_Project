package Model;


public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String isAdmin;
    private Address address; 
    private CreditCard creditCard;

    public User(int id, String firstName, String lastName, String email, String password, String isAdmin2) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin2;
    }

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Address getAddress() { return address; }
    public String getisAdmin() { return isAdmin; }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

}
