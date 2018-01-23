package queststore;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;


public class JDBConnection{
    
    private String filename;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet result;

    public JDBConnection(String filename){
        this.filename = filename;
    }

    Connection connectToDatabase() {
        
        try {
            // Register JDBC driver.
            Class.forName("org.sqlite.JDBC");
            // Open a connection to database.
            connection = DriverManager.getConnection(filename);
        }catch ( Exception exception ) {
            System.err.println( exception.getClass().getName() + ": " + exception.getMessage() );
        }
        System.out.println("Database has opened successfully");
        return connection;
    }