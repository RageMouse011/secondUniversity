package database;

import database.util.ConnectionPool;
import entity.Salary;

import java.sql.*;

import static database.util.Resources.*;

public class SalarySQL {
    ConnectionPool connectionPool = new ConnectionPool(dbUrl, dbUser, dbPass, 5);
    Connection connection = null;

    public boolean registerNewSalary(Salary salary) {
        String createSalary = "insert into salary (salary) values (?)";
        boolean result = false;

        try {
            connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(createSalary);
            ps.setDouble(1, salary.getSalary());
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

    public int getSalaryId(Double mansSalary) {
        String getSalary = "select id from salary where salary = ?";
        int salaryId = 0;

        try {
            connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(getSalary);
            ps.setDouble(1, mansSalary);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                salaryId = rs.getInt("id");
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
        return salaryId;
    }
}
