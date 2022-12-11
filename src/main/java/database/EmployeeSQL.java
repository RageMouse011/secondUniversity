package database;

import database.util.ConnectionPool;

import java.sql.*;

import static database.util.Resources.*;

public class EmployeeSQL {
    ConnectionPool connectionPool = new ConnectionPool(dbUrl, dbUser, dbPass, 5);
    Connection connection = null;

    public boolean registerNewEmployee(int personId, int careerId, double salaryId) {
        String registerNewEmployee = "insert into employee (person_id, career_id, salary_id) " +
                "values (?, ?, ?)";
        boolean result = false;

        try {
            connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(registerNewEmployee);
            ps.setInt(1, personId);
            ps.setInt(2, careerId);
            ps.setDouble(3, salaryId);

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

    public int isEmployeeExists(int personId, int careerId, int salaryId) {
        String checkIfExists = "select id from employee where person_id = ? and career_id = ?" +
                " and salary_id = ?";
        int employeeId = 0;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(checkIfExists, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, personId);
            ps.setInt(2, careerId);
            ps.setInt(3, salaryId);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                employeeId = rs.getInt("id");
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
        return employeeId;
    }

}
