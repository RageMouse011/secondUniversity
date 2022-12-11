package database;

import database.util.ConnectionPool;
import entity.Address;
import entity.Person;


import java.sql.*;

import static database.util.Resources.*;

public class PersonSQL {
    ConnectionPool connectionPool = new ConnectionPool(dbUrl, dbUser, dbPass, 5);
    Connection connection = null;

    public int registerNewPerson(Person person, int addressId, Address address) {
        String createPerson = "insert into person (first_name, last_name, address_id) values (?, ?, ?)";
        int personId = 0;
        AddressSQL addressSQL = new AddressSQL();
        try {
            if(getPersonId(person, addressId) == 0) {
                addressSQL.create(address);
                connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(createPerson, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, person.getFirstName());
                ps.setString(2, person.getLastName());
                ps.setInt(3, addressSQL.getIdOfAddress(address));
                ps.execute();

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    personId = rs.getInt(1);
                }

                ps.close();
                rs.close();
            } else {
                System.out.println("Человек есть в системе.");
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
        return personId;
    }

    public int getPersonId(Person person, int addressId) {
        String getPersonId = "select id from person where first_name = ? and last_name = ? and address_id = ?";
        int personId = 0;

        try {
            connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(getPersonId);

            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getLastName());
            ps.setInt(3, addressId);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                personId = rs.getInt("id");
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
