package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Анатолий on 11.12.2015.
 */
public class MyConnection {
    public Connection getConnection() throws IOException, ClassNotFoundException, SQLException{
        Properties prop = loadPropertiesFile();

        String driverClass = prop.getProperty("MYSQLJDBC.driver");
        String url = prop.getProperty("MYSQLJDBC.url");
        String username = prop.getProperty("MYSQLJDBC.username");
        String password = prop.getProperty("MYSQLJDBC.password");

        Class.forName(driverClass);
        return DriverManager.getConnection(url, username, password);
    }

    public Properties loadPropertiesFile() throws IOException {
        Properties prop = new Properties();

        prop.load(this.getClass().getResourceAsStream("../database.properties"));

        return prop;
    }



}
