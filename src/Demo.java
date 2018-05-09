import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Demo extends HttpServlet {
    //define data source
    @Resource(name="jdbc/project")
    private DataSource dataSource;
    public Demo(){

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //step 1: initialize connection object
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        PrintWriter out = response.getWriter();

        try {

            conn = dataSource.getConnection();
            //step 2: create a SQL statement strings
            String query = " select * from users";
            stmt = conn.createStatement();

            //step 3 :  execute query
            rs = stmt.executeQuery(query);

            //step 4: process the result set
            while (rs.next()){
                String email = rs.getString("email");
                out.print("<br/>" + email);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
