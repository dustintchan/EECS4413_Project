package DAO;

import Model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

public class ItemDAO implements ItemDAOInterface {
    static ServletContext context;

    public ItemDAO(ServletContext cont) {
        context = cont;
    }

    // Load SQLite JDBC driver
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load SQLite JDBC driver", e);
        }
    }


    // Establish a database connection
    private Connection getConnection() throws SQLException {
        String absP = context.getRealPath("/WEB-INF/dbFile/4413.db");
        return DriverManager.getConnection("jdbc:sqlite:" + absP);
    }



    // Retrieve all items
    public List<Item> getAllItems() throws SQLException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM Item";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                items.add(createItemFromResultSet(rs));
            }
        }
        return items;
    }
    
    public List<String> getDistinctBrands() throws SQLException {
        List<String> brands = new ArrayList<>();
        String sql = "SELECT DISTINCT brand FROM Item";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                brands.add(rs.getString("brand"));
            }
        }
        return brands;
    }

    public List<Item> filterItemsByCategory(String category) throws SQLException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM Item WHERE category = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, category);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(createItemFromResultSet(rs));
                }
            }
        }
        return items;
    }

    public List<Item> filterItemsByBrand(String brand) throws SQLException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM Item WHERE brand = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, brand);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(createItemFromResultSet(rs));
                }
            }
        }
        return items;
    }


    // Search items by keyword
    public List<Item> searchItems(String keyword) throws SQLException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM Item WHERE LOWER(name) LIKE ? OR LOWER(description) LIKE ? OR LOWER(type) LIKE ?";


        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            String searchKey = "%" + keyword.toLowerCase() + "%";
            ps.setString(1, searchKey);
            ps.setString(2, searchKey);
            ps.setString(3, searchKey);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(createItemFromResultSet(rs));
                }
            }
        }
        return items;
    }

    
    public List<Item> filterItemsByTypeAndCategory(String type, String category) throws SQLException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM Item WHERE 1=1";

        if (type != null && !type.isEmpty()) {
            sql += " AND type = ?";
        }
        if (category != null && !category.equalsIgnoreCase("allClothing") && !category.isEmpty()) {
            sql += " AND category = ?";
        }

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            int paramIndex = 1;

            if (type != null && !type.isEmpty()) {
                ps.setString(paramIndex++, type);
            }
            if (category != null && !category.equalsIgnoreCase("allClothing") && !category.isEmpty()) {
                ps.setString(paramIndex++, category);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(createItemFromResultSet(rs));
                }
            }
        }
        return items;
    }
    
    


    
    public void updateItemQuantity(int itemId, int newQuantity) throws SQLException {
        String sql = "UPDATE Item SET quantity = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, newQuantity); // Bind the new quantity
            ps.setInt(2, itemId);      // Bind the item ID

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No item found with ID: " + itemId);
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to update item quantity: " + e.getMessage());
        }
    }
    
    public List<Item> SortAndBrand(String sortBy, String sortOrder, String brand) throws SQLException {
        List<Item> items = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Item WHERE 1=1");

        if (brand != null && !brand.equalsIgnoreCase("allBrands") && !brand.equalsIgnoreCase("None")) {
            sql.append(" AND brand = ?");
        }

        if (sortBy != null && !sortBy.isEmpty()) {
            sql.append(" ORDER BY ").append(sortBy);
            if (sortOrder != null && sortOrder.equalsIgnoreCase("desc")) {
                sql.append(" DESC");
            } else {
                sql.append(" ASC");
            }
        }

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            if (brand != null && !brand.equalsIgnoreCase("allBrands") && !brand.equalsIgnoreCase("None")) {
                ps.setString(paramIndex++, brand);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(createItemFromResultSet(rs));
                }
            }
        }
        return items;
    }


   


    private Item createItemFromResultSet(ResultSet rs) throws SQLException {
        return new Item(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getString("category"),
            rs.getString("brand"),
            rs.getString("size"),
            rs.getString("color"),
            rs.getString("type"),
            rs.getDouble("price"),
            rs.getInt("quantity"),
            rs.getString("imgPath")
        );
    }

}
