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