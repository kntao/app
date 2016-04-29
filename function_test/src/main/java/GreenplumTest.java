import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by qingtao.kong on 2016/4/21.
 */
public class GreenplumTest {

    public static void main(String[] args) {
        try {
            Class.forName("com.pivotal.jdbc.GreenplumDriver");
            Connection db = DriverManager.getConnection("jdbc:pivotal:greenplum://192.168.5.252:5432;DatabaseName=mining","dev","manying123");
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("select * from test limit 10 offset 0");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            rs.close();
            st.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
