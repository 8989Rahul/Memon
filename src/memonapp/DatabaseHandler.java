package memonapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler {

    static DatabaseHandler handler = null;
    Connection c2;
    Connection c1;

    private DatabaseHandler() throws SQLException {
        createConnection();
        createTables();
    }

    public static DatabaseHandler getInstance() throws Exception {
        if (handler == null) {
            handler = new DatabaseHandler();
            // this if statement run only once..
        }
        return handler;
    }

    void createConnection() {
        PreparedStatement ps = null;
        try {
            Class.forName("org.postgresql.Driver");
            c1 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            String query = "create database memondemo5";
            ps = c1.prepareStatement(query);
            int rs = ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e){}
        try {
            c2 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/memondemo5", "postgres", "root");

        } catch (Exception e) {}

    }

    public Connection getConnection() throws Exception {
        return c2;
    }

    void createTables() throws SQLException {
        try {
            String query1 = "create table login(username text primary key NOT NULL,password text)";
            PreparedStatement ps1 = c2.prepareStatement(query1);
            int rs1 = ps1.executeUpdate();

            String query2 = "create table memories(title text,memory text,username text references login(username))";
            PreparedStatement ps2 = c2.prepareStatement(query2);
            int rs2 = ps2.executeUpdate();
            System.out.println("table created successfully");
        } catch (Exception e) {}
    }

}
