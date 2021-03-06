import java.sql.*;

public class Connexion {

    static private Connection connexion;

    static  Connection getConnexion() throws Exception {
        if(connexion == null){
            System.out.println("Driver connecter !");
            String ChaineDeConnexion = "jdbc:sqlserver://localhost;database=biblio;user=insertuseruser;password=insertPassword";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connexion = DriverManager.getConnection(ChaineDeConnexion);
            System.out.println("Connexion réussi !");
        }
        return connexion;
    }
}
