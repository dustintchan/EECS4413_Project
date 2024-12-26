package DAO;

import Model.User;

import javax.servlet.ServletContext;
import java.sql.*;

public class UserDAO implements UserDAOInterface{
    private static ServletContext context;

    public UserDAO(ServletContext cont) {
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

    // Establish database connection
    private Connection getConnection() throws SQLException {
        String absP = context.getRealPath("/WEB-INF/dbFile/4413.db");
        return DriverManager.getConnection("jdbc:sqlite:" + absP);
    }
    
    public  User getUserById(int userId) throws SQLException {
        String sql = "SELECT * FROM User WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("isAdmin")
                );
            }
        }
        return null; // User not found
    }

    
    // Validate user credentials
    public User validateUser(String email, String password) throws SQLException {
        String sql = "SELECT * FROM User WHERE email = ? AND password = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("isAdmin")
                );
            }
        }
        return null; // User not found
    }

    // Add a new user
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO User (id, firstName, lastName, email, password, isAdmin) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
        	
        	ps.setInt(1, user.getId());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getisAdmin());
            ps.executeUpdate();
        }
    }
    
    public void updateFirstName(int userId, String firstName) throws SQLException {
        String sql = "UPDATE User SET firstName = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, firstName);
            ps.setInt(2, userId);
            ps.executeUpdate();
        }
    }

    public void updateLastName(int userId, String lastName) throws SQLException {
        String sql = "UPDATE User SET lastName = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, lastName);
            ps.setInt(2, userId);
            ps.executeUpdate();
        }
    }

    public void updateEmail(int userId, String email) throws SQLException {
        String sql = "UPDATE User SET email = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setInt(2, userId);
            ps.executeUpdate();
        }
    }

    public void updatePassword(int userId, String password) throws SQLException {
        String sql = "UPDATE User SET password = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, password);
            ps.setInt(2, userId);
            ps.executeUpdate();
        }
    }

}
