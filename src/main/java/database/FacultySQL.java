package database;

import database.util.ConnectionPool;
import entity.Faculty;

import java.sql.*;

import static database.util.Resources.*;

public class FacultySQL {
    ConnectionPool connectionPool = new ConnectionPool(dbUrl, dbUser, dbPass, 5);
    Connection connection = null;

    public int registerNewFaculty(Faculty faculty) {
        String createFaculty = "insert into faculty (name) values (?)";
        int facultyId = 0;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(createFaculty, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, faculty.getName());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                facultyId = rs.getInt(1);
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
        return facultyId;
    }

    public int getIdOfFaculty(String name) {
        String getIdOfFaculty = "select id from faculty where name = ?";
        int facultyId = 0;

        try {
            connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(getIdOfFaculty);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                facultyId = rs.getInt(1);
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
        return facultyId;
    }
}
