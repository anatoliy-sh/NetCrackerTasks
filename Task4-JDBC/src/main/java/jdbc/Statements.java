package jdbc;

import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Created by Анатолий on 11.12.2015.
 */
public class Statements {
    private static final Logger log = Logger.getLogger(Statements.class);

    public void viewSubjects(Connection con) throws SQLException {
        String query = "SELECT * FROM subjects";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            log.info("SimpleStatement");
            if (rs != null)
                while (rs.next()) {
                    log.info("id: " + rs.getInt(1) + ", name: " + rs.getString(2));
                }
        } catch (SQLException e) {
            log.error(e.getErrorCode() + e.getMessage(), e);
        }
    }

    //смписок предметов группы в конкретный день
    public void prepareStatement(Connection con, int group, int day) throws SQLException {
        String query = "SELECT l.ID, s.name  FROM list l JOIN subjects s ON (l.subjID = s.ID) " +
                "WHERE l.groupID = ? and l.day = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, group);
            pstmt.setInt(2, day);
            try (ResultSet rs = pstmt.executeQuery()) {
                log.info("PrepareStatement");
                if (rs != null)
                    while (rs.next()) {
                        log.info("id: " + rs.getInt(1) + ", name: " + rs.getString(2));
                    }
            } catch (SQLException e) {
                log.error(e.getErrorCode() + e.getMessage(), e);
            }
        } catch (SQLException e) {
            log.error(e.getErrorCode() + e.getMessage(), e);
        }
    }

    //количество пар
    public void callableStatement(Connection con, int group, int day) throws SQLException {
        try (CallableStatement cstm = con.prepareCall("{CALL para_len2(? , ? , ?) }")) {
            cstm.setInt(1, group);
            cstm.setInt(2, day);
            cstm.registerOutParameter("MY_COUNT", Types.INTEGER);
            cstm.execute();
            log.info("CallableStatement");
            log.info("Number of subjects (day - " + day + " group - " + group + ")- " + cstm.getInt("MY_COUNT"));
        } catch (SQLException e) {
            log.error(e.getErrorCode() + e.getMessage(), e);
        }
    }
}
