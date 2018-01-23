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

    public void executeQueryAgainstDatabase(String query){
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

            System.out.println("\n\nResult: \n\n");
            while(result.next()){
                String row = "";
            
                for (int i = 0; i < columnNames.size(); i++){
                    row += columnNames.get(i) + ": "+ result.getString(columnNames.get(i));
                    row += "  ";
                }
                System.out.println(row);
            }
            System.out.println("\n\n\nQuery performed successfully");
            

        }catch(Exception exception){

            System.err.println(exception.getClass().getName() + ": " + exception.getMessage() );
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

            System.out.println("\n\nResult: \n\n");
            while(result.next()){
                
                ArrayList<String> rowResult = new ArrayList<String>();
            
                for (int i = 0; i < columnNames.size(); i++){
                        
                        rowResult.add(result.getString(columnNames.get(i)));
                    }
                arrayResult.add(rowResult);
                }
                System.out.println("\n\n\nOperation performed successfully");
                
            }catch(Exception exception){
    
                System.err.println(exception.getClass().getName() + ": " + exception.getMessage() );
                System.out.println("\n\n\nOperation was NOT performed successfully");
    
            }
        System.out.println(arrayResult);
        return arrayResult;
    }

    public void closeDatabase(){
        try{
            result.close();
            statement.close();
            connection.close();

        }catch(Exception exception){
            System.err.println(exception.getClass().getName() + ": " + exception.getMessage() );
            System.exit(0);
        }
    }
}