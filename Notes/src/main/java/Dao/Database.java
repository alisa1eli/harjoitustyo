package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

    public void init() {
        List<String> lauseet = sqliteLauseet();

        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            for (String lause : lauseet) {
                System.out.println("Running command >> " + lause);
                st.executeUpdate(lause);
            }

        } catch (Throwable t) {
            // database already exists
            System.out.println("Error >> " + t.getMessage());
        }
    }

    private List<String> sqliteLauseet() {
        ArrayList<String> lista = new ArrayList<>();

        // to create a database
        lista.add("CREATE TABLE User (id integer PRIMARY KEY, email varchar(255), "
                + "password varchar(255), name varchar(255), userType integer);");
        lista.add("CREATE TABLE File(id integer PRIMARY KEY, userId integer, "
                + "dateCreated datetime, dateLastChanged datetime"
                + "FOREIGN KEY(userId) REFERENCES User(id));");
        
//        lista.add("INSERT INTO User() VALUES(1,'Manhattan');");
//        lista.add("INSERT INTO RaakaAine(id,nimi) VALUES(1,'ruisviski');");
//        lista.add("INSERT INTO RaakaAine(id,nimi) VALUES(2,'makea vermutti');");
//        lista.add("INSERT INTO RaakaAine(id,nimi) VALUES(3,'angostura');");
        
        return lista;
    }
}
