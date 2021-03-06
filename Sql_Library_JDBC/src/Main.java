import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws Exception {
        Ecran ecran = new Ecran();
        ecran.setResizable(false);
        ecran.setVisible(true);
    }
}
