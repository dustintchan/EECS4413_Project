package DAO;

import Model.User;
import java.sql.SQLException;

public interface UserDAOInterface {
	
    User getUserById(int userId) throws SQLException;
    
    User validateUser(String email, String password) throws SQLException;
    
    void addUser(User user) throws SQLException;
    
    void updateFirstName(int userId, String firstName) throws SQLException;
    
    void updateLastName(int userId, String lastName) throws SQLException;
    
    void updateEmail(int userId, String email) throws SQLException;
    
    void updatePassword(int userId, String password) throws SQLException;
}
