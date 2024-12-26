package DAO;



import Model.CreditCard;
import java.sql.*;

import javax.servlet.ServletContext;

public class CreditCardDAO implements CreditCardDAOInterface{
	
	static ServletContext context;

    public CreditCardDAO(ServletContext cont) {
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
    
    public CreditCard getCreditCardByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM CreditCard WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new CreditCard(
                    rs.getInt("id"),
                    rs.getInt("userID"),
                    rs.getString("cardNumber"),
                    rs.getString("cardName"),
                    rs.getString("cardExpDate"),
                    rs.getString("cardSecCode")
                );
            }
        }
        return null; // No CreditCard found
    }

    public void addCreditCard(CreditCard card) throws SQLException {
        String sql = "INSERT INTO CreditCard (id, UserId, cardNumber, cardName, cardExpDate, cardSecCode) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, card.getId());
            ps.setInt(2, card.getUserId());
            ps.setString(3, card.getCardNumber());
            ps.setString(4, card.getCardName());
            ps.setString(5, card.getCardExpDate());
            ps.setString(6, card.getCardSecCode());
            ps.executeUpdate();
        }
    }

   

    public void updateCardNumber(int cardId, String cardNumber) throws SQLException {
        String sql = "UPDATE CreditCard SET cardNumber = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, cardNumber);
            ps.setInt(2, cardId);
            ps.executeUpdate();
        }
    }

    public void updateCardName(int cardId, String cardName) throws SQLException {
        String sql = "UPDATE CreditCard SET cardName = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, cardName);
            ps.setInt(2, cardId);
            ps.executeUpdate();
        }
    }

    public void updateCardExpDate(int cardId, String cardExpDate) throws SQLException {
        String sql = "UPDATE CreditCard SET cardExpDate = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, cardExpDate);
            ps.setInt(2, cardId);
            ps.executeUpdate();
        }
    }

    public void updateCardSecCode(int cardId, String cardSecCode) throws SQLException {
        String sql = "UPDATE CreditCard SET cardSecCode = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, cardSecCode);
            ps.setInt(2, cardId);
            ps.executeUpdate();
        }
    }


    public void deleteCreditCard(int cardId) throws SQLException {
        String sql = "DELETE FROM CreditCard WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, cardId);
            ps.executeUpdate();
        }
    }
}
