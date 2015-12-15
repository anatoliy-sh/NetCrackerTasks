package jdbc;
import org.apache.log4j.Logger;
import java.sql.*;

/**
 * Created by Анатолий on 11.12.2015.
 */
public  class Statements {
    private static final Logger log = Logger.getLogger(Statements.class);
    public  void viewSubjects (Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        String query = "SELECT * FROM subjects";
        ResultSet rs = stmt.executeQuery(query);
        log.info("SimpleStatement");
        if(rs!=null)
            while (rs.next()){
                log.info("id: "+rs.getInt(1)+", name: " + rs.getString(2));
            }
        stmt.close();
        rs.close();
    }
    //смписок предметов группы в конкретный день
    public void prepareStatement (Connection con, int group, int day) throws SQLException{
        PreparedStatement pstmt = con.prepareStatement("SELECT l.ID, s.name  FROM list l JOIN subjects s ON (l.subjID = s.ID) " +
                "WHERE l.groupID = ? and l.day = ?");
        pstmt.setInt(1,group);
        pstmt.setInt(2,day);
        ResultSet rs = pstmt.executeQuery();
        log.info("PrepareStatement");
        if(rs!=null)
            while (rs.next()){
                log.info("id: "+rs.getInt(1)+", name: " + rs.getString(2));
            }
        pstmt.close();
        rs.close();
    }
    //количество пар
    public void callableStatement (Connection con, int group, int day) throws SQLException{
        CallableStatement cstm = con.prepareCall("{CALL para_len2(? , ? , ?) }");
        //callableStatement.registerOutParameter(1, Types.INTEGER);
        //cstm.setString(1,"para_len()");
        cstm.setInt(1,group);
        cstm.setInt(2,day);
        cstm.registerOutParameter("MY_COUNT",Types.INTEGER);
        cstm.execute();
        log.info("CallableStatement");
        log.info("Number of subjects (day - "+day+" group - "+group+")- "+cstm.getInt("MY_COUNT"));

        cstm.close();
    }
}
