package tugas.com;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;

public class Database {
    private static Connection koneksi;

    public static Connection getDatabase() {
        if (koneksi == null) {
            try {
                String url = "jdbc:mysql://localhost/kontak";
                String username = "root";
                String password = "root";

                DriverManager.registerDriver((Driver) new com.mysql.jdbc.Driver());
                koneksi = DriverManager.getConnection(url, username, password);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        return koneksi;
    }
}
