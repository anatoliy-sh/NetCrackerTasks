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

    public List<Para> getTimeTable(Connection con, int group, int day) throws SQLException {
        String query = "SELECT l.para, s.name, p.time  FROM list l JOIN subjects s ON (l.subjID = s.ID) JOIN para p ON (p.number = l.para) " +
                "WHERE l.groupID = ? and l.day = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            List<Para> paras = new ArrayList<>();
            pstmt.setInt(1, group);
            pstmt.setInt(2, day);
            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs != null)
                    while (rs.next()) {
                        Para para = new Para(rs.getInt(1),rs.getString(2),rs.getString(3));
                        paras.add(para);
                    }
                return paras;
            } catch (SQLException e) {
                System.out.println(e.getErrorCode() + e.getMessage());
                //log.error(e.getErrorCode() + e.getMessage(), e);
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
           // log.error(e.getErrorCode() + e.getMessage(), e);
            return null;
        }
    }

}
