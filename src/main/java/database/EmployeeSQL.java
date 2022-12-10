package database;

import database.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static database.util.Resources.*;

public class EmployeeSQL {
    ConnectionPool connectionPool = new ConnectionPool(dbUrl, dbUser, dbPass, 5);
    Connection connection = null;

    public boolean registerNewEmployee(int experience, int personId, int careerId, double salaryId) {
        String registerNewEmployee = "insert into employee (experience, person_id, career_id, salary_id) " +
                "values (?, ?, ?, ?)";
        boolean result = false;

        try {
            connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(registerNewEmployee);
            ps.setDouble(1, experience);
            ps.setInt(2, personId);
            ps.setInt(3, careerId);
            ps.setDouble(4, salaryId);

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
