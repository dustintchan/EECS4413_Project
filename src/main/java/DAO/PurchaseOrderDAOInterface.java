package DAO;

import Model.PurchaseOrder;
import java.sql.SQLException;
import java.util.List;

public interface PurchaseOrderDAOInterface {
	
    List<PurchaseOrder> getOrdersByUserId(int userId) throws SQLException;
    
    void addPurchaseOrder(PurchaseOrder order) throws SQLException;
}