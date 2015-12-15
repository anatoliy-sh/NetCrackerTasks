package jdbc;

/**
 * Created by �������� on 07.12.2015.
 */

import org.apache.log4j.Logger;

import java.sql.*;

public class Test {
    private static final Logger log = Logger.getLogger(Test.class);
    public static void main(String[] args)  {
        Connection con= null;
        MyConnection myCon = new MyConnection();
        Statements st = new Statements();
        try {
            con = myCon.getConnection();
            if (con != null){
                st.viewSubjects(con);
                st.prepareStatement(con,1,2);
                st.callableStatement(con,1,2);
            }
            else
                System.out.println(" unable to create connection");
        }catch (SQLException e) {
            log.error(e+e.getMessage()+e.getErrorCode(),e);
        }
        catch (Exception e) {
            log.error(e+e.getMessage(),e);

        }
        finally {
            if(con!=null) try {
                con.close();
            } catch (SQLException e) {
                log.error(e+ e.getMessage()+e.getErrorCode(),e);
            }
        }
    }
}