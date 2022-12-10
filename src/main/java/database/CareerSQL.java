package database;

import database.util.ConnectionPool;
import entity.Career;


import java.sql.*;

import static database.util.Resources.*;

public class CareerSQL {
    ConnectionPool connectionPool = new ConnectionPool(dbUrl, dbUser, dbPass, 5);
    Connection connection = null;
    int careerId = 0;

    public boolean registerNewCareer(Career career) {
        String addCareer = "insert into career (name) values (?)";
        boolean result = false;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(addCareer);
            ps.setString(1, career.getName());
            result = ps.execute();


            ps.close();
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
        return result;
    }
    public int getCareerId(String name) {
        String getCareerId = "select id from career where name = ?";
        int careerId = 0;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(getCareerId);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                careerId = rs.getInt(1);
            } else {
                throw new SQLException("Такой должности не существует.");
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
        return careerId;
    }
}
