package DAO;

import Model.Address;

import javax.servlet.ServletContext;
import java.sql.*;

public class AddressDAO implements AddressDAOInterface {
    private static ServletContext context;

    public AddressDAO(ServletContext cont) {
        context = cont;
    }

    // Load SQLite JDBC driver
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    // Establish a database connection
    private Connection getConnection() throws SQLException {
        String absP = context.getRealPath("/WEB-INF/dbFile/4413.db");
        return DriverManager.getConnection("jdbc:sqlite:" + absP);
    }

    // Retrieve address by user ID
    public Address getAddressByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM Address WHERE userID = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Address(
                    rs.getInt("id"),
                    rs.getInt("userID"),
                    rs.getString("street"),
                    rs.getString("city"),
                    rs.getString("province"),
                    rs.getString("country"),
                    rs.getString("zip"),
                    rs.getString("phone")
                );
            }
        }
        return null; // No address found
    }

    public void updateStreet(int addressId, String street) throws SQLException {
        String sql = "UPDATE Address SET street = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, street);
            ps.setInt(2, addressId);
            ps.executeUpdate();
        }
    }

    public void updateCity(int addressId, String city) throws SQLException {
        String sql = "UPDATE Address SET city = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, city);
            ps.setInt(2, addressId);
            ps.executeUpdate();
        }
    }

    public void updateProvince(int addressId, String province) throws SQLException {
        String sql = "UPDATE Address SET province = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, province);
            ps.setInt(2, addressId);
            ps.executeUpdate();
        }
    }

    public void updateCountry(int addressId, String country) throws SQLException {
        String sql = "UPDATE Address SET country = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, country);
            ps.setInt(2, addressId);
            ps.executeUpdate();
        }
    }

    public void updateZip(int addressId, String zip) throws SQLException {
        String sql = "UPDATE Address SET zip = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, zip);
            ps.setInt(2, addressId);
            ps.executeUpdate();
        }
    }

    public void updatePhone(int addressId, String phone) throws SQLException {
        String sql = "UPDATE Address SET phone = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, phone);
            ps.setInt(2, addressId);
            ps.executeUpdate();
        }
    }


    public void deleteAddress(int addressId) throws SQLException {
        String sql = "DELETE FROM Address WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, addressId);
            ps.executeUpdate();
        }
    }
    
    public void addAddress(Address address) throws SQLException {
        String sql = "INSERT INTO Address (id, userID, street, city, province, country, zip, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, address.getId());
            ps.setInt(2, address.getUserId());
            ps.setString(3, address.getStreet());
            ps.setString(4, address.getCity());
            ps.setString(5, address.getProvince());
            ps.setString(6, address.getCountry());
            ps.setString(7, address.getZip());
            ps.setString(8, address.getPhone());
            ps.executeUpdate();
        }
    }
}
