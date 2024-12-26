package DAO;

import Model.Address;
import java.sql.SQLException;

public interface AddressDAOInterface {
    Address getAddressByUserId(int userId) throws SQLException;
    
    void updateStreet(int addressId, String street) throws SQLException;
    
    void updateCity(int addressId, String city) throws SQLException;
    
    void updateProvince(int addressId, String province) throws SQLException;
    
    void updateCountry(int addressId, String country) throws SQLException;
    void updateZip(int addressId, String zip) throws SQLException;
    
    void updatePhone(int addressId, String phone) throws SQLException;
    
    void deleteAddress(int addressId) throws SQLException;
    
    void addAddress(Address address) throws SQLException;
}
