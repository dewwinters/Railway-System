import java.sql.*;

public class Database {
    private String user = "root";
    private String pass = "daihocquocteIU@22";
    private String url = "jdbc:mysql://localhost:3306/railway_system";
    private Statement statement;

    public Database() throws SQLException {
        Connection con = DriverManager.getConnection(url,user,pass);
        statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    public Statement getStatement() {
        return statement;
    }
}
