package database;

import database.util.ConnectionPool;
import entity.Address;
import entity.Faculty;
import entity.Person;
import entity.Student;

import java.sql.*;

import static database.util.Resources.*;

public class StudentSQL {
    ConnectionPool connectionPool = new ConnectionPool(dbUrl, dbUser, dbPass, 5);
    Connection connection = null;

    public boolean create(int personId, int facultyId) {
        String createStudent = "insert into student (person_id, faculty_id) values (?, ?)";
        boolean result = false;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(createStudent);
            ps.setInt(1, personId);
            ps.setInt(2, facultyId);
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
}
