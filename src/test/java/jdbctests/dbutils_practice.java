package jdbctests;

import org.testng.annotations.Test;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class dbutils_practice {

    @Test
    public void test1(){
        //create connection
        DBUtils.createConnection();

        //using method to get result as a list of maps
        List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap("select * from employees");

        //printing the result
        for (Map<String,Object> map: queryResultMap) {
            System.out.println(map.toString());

        }

        //close connection
        DBUtils.destroy();

    }

    @Test
    public void test2(){
        //create connection
        DBUtils.createConnection();

        //get only one row result
        Map<String, Object> rowMap = DBUtils.getRowMap("select first_name,last_name,salary,job_id from employees\n" +
                "where employee_id = 100");



        System.out.println(rowMap.toString());
        //close connection
        DBUtils.destroy();

    }

    @Test
    public void test3() throws Exception {

        DBUtils.createConnection();

        List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap("select * from departments");

        for(Map<String,Object> map : queryResultMap){
            System.out.println(map.toString());
        }

        int rowCount = DBUtils.getRowCount();
        System.out.println(rowCount);

        List<String> columnNames = DBUtils.getColumnNames("select * from departments");
        System.out.println(columnNames.toString());

        System.out.println("***************************+");
        Map<String, Object> rowMap = DBUtils.getRowMap("select * from departments where department_id = 100");

        System.out.println(rowMap.toString());
        System.out.println("*******************************");


        int rowCount2 = DBUtils.getRowCount();
        System.out.println(rowCount);


    }
}
