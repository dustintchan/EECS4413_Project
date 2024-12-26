package DAO;

import Model.Item;
import java.sql.SQLException;
import java.util.List;

public interface ItemDAOInterface {
    List<Item> getAllItems() throws SQLException;
    
    List<String> getDistinctBrands() throws SQLException;
    
    List<Item> filterItemsByCategory(String category) throws SQLException;
    
    List<Item> filterItemsByBrand(String brand) throws SQLException;
    
    List<Item> searchItems(String keyword) throws SQLException;
    
    List<Item> filterItemsByTypeAndCategory(String type, String category) throws SQLException;
    
    void updateItemQuantity(int itemId, int newQuantity) throws SQLException;
    
    List<Item> SortAndBrand(String sortBy, String sortOrder, String brand) throws SQLException;
}