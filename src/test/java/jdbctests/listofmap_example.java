package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.*;

public class listofmap_example {
    String dbUrl = "jdbc:oracle:thin:@54.197.12.112:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void MetaDataExample() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from regions");

        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //list for keeping all rows a map

        List<Map<String, Object>> queryData = new ArrayList<>();



            //columnname, value can be any dataType so object can store any data.
        Map<String, Object> row1 = new HashMap<>();
        row1.put("first_name", "Steven");
        row1.put("last_name","King");
        row1.put("salary", 24000);
        row1.put("job_id","AD_PRES");
        System.out.println("row1.toString() = " + row1.toString());

        Map<String,Object> row2 = new HashMap<>();
        row2.put("first_name", "Neena");
        row2.put("last_name","Kochnar");
        row2.put("salary", 17000);
        row2.put("job_id","AD_VP");
        System.out.println("row2.toString() = " + row2.toString());

        System.out.println("row2.get(\"salary\") = " + row2.get("salary"));

        //adding rows to my list
        queryData.add(row1);
        queryData.add(row2);

        //get the steven last name directly from the list
        // queryData.get(0)==point first map.(row1) to access value of map we need to use get("key Object") method
        System.out.println("queryData.get(0).get(\"last_name\") = " + queryData.get(0).get("last_name"));


        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void MetaDataExample2() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select first_name,last_name,salary, job_id from " +
                "employees where rownum<6");

        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //list for keeping all rows a map

        List<Map<String, Object>> queryData = new ArrayList<>();



        //columnname, value can be any dataType so object can store any data.
        Map<String, Object> row1 = new HashMap<>();
        //move to first row
        resultSet.next();
        row1.put(rsMetadata.getColumnName(1), resultSet.getString(1));
        row1.put(rsMetadata.getColumnName(2),resultSet.getString(2));
        row1.put(rsMetadata.getColumnName(3),resultSet.getString(3));
        row1.put(rsMetadata.getColumnName(4),resultSet.getString(4));

        System.out.println("row1.toString() = " + row1.toString());


        Map<String,Object> row2 = new HashMap<>();
        //move to second row
        resultSet.next();
        row2.put(rsMetadata.getColumnName(1), resultSet.getString(1));
        row2.put(rsMetadata.getColumnName(2),resultSet.getString(2));
        row2.put(rsMetadata.getColumnName(3), resultSet.getString(3));
        row2.put(rsMetadata.getColumnName(4),resultSet.getString(4));
        System.out.println("row2.toString() = " + row2.toString());

        System.out.println("row2.get(\"salary\") = " + row2.get(rsMetadata.getColumnName(3)));

        //adding rows to my list
        queryData.add(row1);
        queryData.add(row2);

        //get the steven last name directly from the list
        // queryData.get(0)==point first map.(row1) to access value of map we need to use get("key Object") method
        System.out.println("queryData.get(0).get(\"last_name\") = " + queryData.get(0).get(rsMetadata.getColumnName(2)));


        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }
}
