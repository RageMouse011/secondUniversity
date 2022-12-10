package database.util;

import java.util.ResourceBundle;

public class Resources {
    public static ResourceBundle resource = ResourceBundle.getBundle("database");
    public static String dbUrl = resource.getString("dbUrl");
    public static String dbUser = resource.getString("dbUser");
    public static String dbPass = resource.getString("dbPass");
}
