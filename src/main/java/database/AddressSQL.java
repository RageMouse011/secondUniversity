package database;

import database.util.ConnectionPool;
import entity.Address;

import java.sql.*;

import static database.util.Resources.*;

public class AddressSQL {
    ConnectionPool connectionPool = new ConnectionPool(dbUrl, dbUser, dbPass, 5);
    Connection connection = null;

    public int create(Address address) {
        String createAddress = "insert into address (country, city, street, house_number) values (?, ?, ?, ?)";
        int addressId = 0;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(createAddress, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, address.getCountry());
            ps.setString(2, address.getCity());
            ps.setString(3, address.getStreet());
            ps.setString(4, address.getHouseAddress());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                addressId = rs.getInt(1);
            }

            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connectionPool.returnConnection(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return addressId;
    }

    public int getIdOfAddress(String country, String city, String street, String houseNumber) {
        String getIdOfAddress = "select id from address where country = ? and city = ? " +
                "and street = ? and house_number = ?";
        int addressId = 0;

        try {
            connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(getIdOfAddress);
            ps.setString(1, country);
            ps.setString(2, city);
            ps.setString(3, street);
            ps.setString(4, houseNumber);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                addressId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connectionPool.returnConnection(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return addressId;
    }
}
