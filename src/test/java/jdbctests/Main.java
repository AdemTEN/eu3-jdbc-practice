package jdbctests;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String dbUrl = "jdbc:oracle:thin:@54.197.12.112:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        //creating connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword );
        //create a statement object
        Statement statement = connection.createStatement();
        //run query and get result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");



        //move pointer to first row. it point always defult first row as column's names
       // resultSet.next();//go to next row after the column name row
        //getting information with column name
       // System.out.println(resultSet.getString("region_name"));
        //getting information with column index (starts from 1)
       // System.out.println(resultSet.getString(2));

        //"1 - Europa"
       // System.out.println(resultSet.getInt(1)+" - " + resultSet.getString("region_name"));

        //move pointer to second row
        //resultSet.next();
        //getting information with column name
       // System.out.println(resultSet.getString("region_name"));
        //getting information with column index (starts from 1)
        //System.out.println(resultSet.getString(2));

        //"2 - Americas"
        //System.out.println(resultSet.getInt(1)+ " - "+ resultSet.getString("region_name"));

        //move pointer to third row
        //resultSet.next();

       // System.out.println(resultSet.getInt(1)+" - "+ resultSet.getString(2));

        while(resultSet.next()){
            System.out.println(resultSet.getString(1)+ " - "+ resultSet.getString(2)
            + " - " + resultSet.getString(3) + " - "+ resultSet.getString(4));
        }

        //close all conection
        resultSet.close();
        statement.close();
        connection.close();
















    }
}
