package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class jdbc_example {

    String dbUrl = "jdbc:oracle:thin:@54.197.12.112:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {

        //creating connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword );
        //create a statement object                       //in order to move  up and down ,  //in order to read only data.
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");


        //how to find how many rows we have for the query
        //go to last row
        resultSet.last();
        //get the row count
        int rowCount = resultSet.getRow();

        System.out.println("rowCount = " + rowCount);

        //we need move before first row to get full list since we are at he last row right now.
        resultSet.beforeFirst();
        while(resultSet.next()){
            System.out.println(resultSet.getString(1)+" - "+  resultSet.getString(2));
        }

        resultSet = statement.executeQuery("select * from regions");
        while(resultSet.next()){
            System.out.println(resultSet.getString(1)+" - "+  resultSet.getString(2));
        }


        //close all conection
        resultSet.close();
        statement.close();
        connection.close();

    }

    @Test
    public void CountNavigate() throws SQLException {

        //creating connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword );
        //create a statement object                       //in order to move  up and down ,  //in order to read only data.
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");


        //how to find how many rows we have for the query
        //go to last row
        resultSet.last();
        //get the row count
        int rowCount = resultSet.getRow();

        System.out.println("rowCount = " + rowCount);

        //we need move before first row to get full list since we are at he last row right now.
        resultSet.beforeFirst();
        while(resultSet.next()){
            System.out.println(resultSet.getString(1)+" - "+  resultSet.getString(2));
        }


        //close all conection
        resultSet.close();
        statement.close();
        connection.close();

    }

    @Test
    public void MetaDataExample() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");


        //get the database related data inside the dbMetadata object
        DatabaseMetaData dbMetadata = connection.getMetaData();

        System.out.println("User = " + dbMetadata.getUserName());
        System.out.println("Database Product Name = " + dbMetadata.getDatabaseProductName());
        System.out.println("Database Product Version = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("Driver Name = " + dbMetadata.getDriverName());
        System.out.println("Driver Version = " + dbMetadata.getDriverVersion());



        //get the resultset object metadata==VERY IMPORTANT
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        //how many columns we have ?
        int columnCount = resultSetMetaData.getColumnCount();
        System.out.println("columnCount = " + columnCount);

        //column names
        System.out.println("resultSetMetaData.getColumnName(1) = " + resultSetMetaData.getColumnName(1));
        System.out.println("resultSetMetaData.getColumnName(2) = " + resultSetMetaData.getColumnName(2));

        //print all the column names dynamically
        for (int i = 1; i <= columnCount; i++) {
            System.out.println("resultSetMetaData.getColumnName (" + i + ") = " +  resultSetMetaData.getColumnName(i));

        }

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }






}
