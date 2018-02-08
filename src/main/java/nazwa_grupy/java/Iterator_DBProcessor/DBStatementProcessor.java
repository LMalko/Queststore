package nazwa_grupy.java.Iterator_DBProcessor;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;


public class DBStatementProcessor{
    
    private String filename;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet result;

    public DBStatementProcessor(String filename){
        this.filename = filename;
    }

    public void connectToDatabase() {
        
        try {
            // Register JDBC driver.
            Class.forName("org.sqlite.JDBC");
            // Open a connection to database.
            connection = DriverManager.getConnection(filename);
        }catch ( Exception exception ) {
            System.err.println( exception.getClass().getName() + ": " + exception.getMessage() );
        }
    }

    public void executeQueryAgainstDatabase(String query){
        try{

            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            printQueryResult(result);

        }catch(Exception exception){

            System.err.println(exception.getClass().getName() + ": " + exception.getMessage() );
            System.out.println("\n\n\nQuery was NOT performed successfully");

        }
    }

    private void printQueryResult(ResultSet result){
        try {
            // Group all column names from query result
            ResultSetMetaData metaData = result.getMetaData();
            int columnCount = metaData.getColumnCount();
            List<String> columnNames = new ArrayList<String>();


            for (int row = 1; row <= columnCount; row++) {
                String columnName = metaData.getColumnName(row).toString();
                columnNames.add(columnName);
            }

            while (result.next()) {
                String row = "";

                for (int i = 0; i < columnNames.size(); i++) {
                    row += columnNames.get(i) + ": " + result.getString(columnNames.get(i));
                    row += "  ";
                }
                System.out.println(row);
                }
        }catch(Exception exception){
                System.err.println(exception.getClass().getName() + ": " + exception.getMessage());
                System.out.println("\n\n\nQuery was NOT performed successfully");
            }
        }


    public void executeUpdateAgainstDatabase(String update){
        try{
            statement = connection.createStatement();
            statement.executeUpdate(update);
        }catch (Exception exception){
            System.err.println(exception.getClass().getName() + ": " + exception.getMessage() );
            System.out.println("\n\n\nUpdate was NOT performed successfully");
        }
        
    }

    public String getStringDataFromQuery(String query, String dataToGet){
        String receivedData = "";
        try{
            result = statement.executeQuery(query);
            while (result.next()) {
                receivedData = result.getString(dataToGet);
            }

        } catch (SQLException exception) {
            System.err.println(exception.getClass().getName() + ": " + exception.getMessage() );
            System.out.println("\n\n\nOperation was NOT performed successfully");
        }
        return receivedData;
    }

    public int getIntegerDataFromQuery(String query, String columnToGet){
        int receivedData = 0;
        try{
            result = statement.executeQuery(query);
            while (result.next()) {
                receivedData = result.getInt(columnToGet);
            }

        } catch (SQLException exception) {
            System.err.println(exception.getClass().getName() + ": " + exception.getMessage() );
            System.out.println("\n\n\nOperation was NOT performed successfully");
        }
        return receivedData;
    }



    public ArrayList getArrayListFromQuery(String query){
        
        ArrayList<ArrayList> arrayResult = new ArrayList<ArrayList>();

        try{
            
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            // Group all column names from query result
            ResultSetMetaData metaData = result.getMetaData(); 
            int columnCount = metaData.getColumnCount(); 
            List<String> columnNames = new ArrayList<String>();


            for (int row = 1; row <= columnCount; row++){  
                String columnName = metaData.getColumnName(row).toString();  
                columnNames.add(columnName);
            }

            while(result.next()){
                
                ArrayList<String> rowResult = new ArrayList<String>();
            
                for (int i = 0; i < columnNames.size(); i++){
                        
                        rowResult.add(result.getString(columnNames.get(i)));
                    }
                arrayResult.add(rowResult);
                }
                
            }catch(Exception exception){
    
                System.err.println(exception.getClass().getName() + ": " + exception.getMessage() );
                System.out.println("\n\n\nOperation was NOT performed successfully");
    
            }
        return arrayResult;
    }

    public void closeDatabase(){
        try{
            if (result != null){
                result.close();
            }
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }


        }catch(Exception exception){
            System.err.println(exception.getClass().getName() + ": " + exception.getMessage() );
            System.exit(0);
        }
    }
}
