package DAO;

import Model.PurchaseOrder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

public class PurchaseOrderDAO implements PurchaseOrderDAOInterface{
	
	static ServletContext context;

    public PurchaseOrderDAO(ServletContext cont) {
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
    
    public List<PurchaseOrder> getOrdersByUserId(int userId) throws SQLException {
        List<PurchaseOrder> orders = new ArrayList<>();
        String sql = "SELECT * FROM PurchaseOrder WHERE userID = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                orders.add(new PurchaseOrder(
                    rs.getInt("id"),
                    rs.getInt("userID"),
                    rs.getInt("itemID"),
                    rs.getInt("quantity"),
                    rs.getDouble("totalAmount"),
                    rs.getString("dateOfPurchase")
                ));
            }
        }
        return orders;
    }

    public void addPurchaseOrder(PurchaseOrder order) throws SQLException {
        String sql = "INSERT INTO PurchaseOrder (userID, itemID, quantity, totalAmount, dateOfPurchase) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getItemId());
            ps.setInt(3, order.getQuantity());
            ps.setDouble(4, order.getTotalAmount());
            ps.setString(5, order.getDateOfPurchase());
            ps.executeUpdate();
        }
    }
}
