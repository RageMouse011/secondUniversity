package database;

import database.util.ConnectionPool;
import entity.Person;

import java.sql.*;

import static database.util.Resources.*;

public class PersonSQL {
    ConnectionPool connectionPool = new ConnectionPool(dbUrl, dbUser, dbPass, 5);
    Connection connection = null;

    public int create(Person person, int addressId) {
        String createPerson = "insert into person (first_name, last_name, address_id) values (?, ?, ?)";
        int personId = 0;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(createPerson, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getLastName());
            ps.setInt(3, addressId);
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                personId = rs.getInt(1);
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
        return personId;
    }
}
