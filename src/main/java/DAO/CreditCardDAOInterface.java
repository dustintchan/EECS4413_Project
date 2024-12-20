package DAO;

import Model.CreditCard;
import java.sql.SQLException;

public interface CreditCardDAOInterface {
    CreditCard getCreditCardByUserId(int userId) throws SQLException;
    
    void addCreditCard(CreditCard card) throws SQLException;
    
    void updateCardNumber(int cardId, String cardNumber) throws SQLException;
    
    void updateCardName(int cardId, String cardName) throws SQLException;
    
    void updateCardExpDate(int cardId, String cardExpDate) throws SQLException;
    
    void updateCardSecCode(int cardId, String cardSecCode) throws SQLException;
    
    void deleteCreditCard(int cardId) throws SQLException;
}