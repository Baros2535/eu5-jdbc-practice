package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.*;

public class ListOfMapExample {
    String dbUrl="jdbc:oracle:thin:@44.197.133.197:1521:xe";
    String dbUsername="hr";
    String dbPassword="hr";

    @Test
    public void metaExamle() throws SQLException {
        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement
        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get results

        ResultSet resultSet = statement.executeQuery("Select * from employees");
//get the resultsetObject
        ResultSetMetaData resultSetMetaData= resultSet.getMetaData();

        Map<String,Object> row1=new HashMap<>();
        row1.put("first_name","Barış");
        row1.put("last_name","Altun");
        row1.put("salary",24000);
        row1.put("job_id","AD_PRES");
        System.out.println("row1.toString() = " + row1.toString());
        Map<String,Object> row2=new HashMap<>();
        row2.put("first_name","Hascı");
        row2.put("last_name","Arpacı");
        row2.put("salary",20000);
        row2.put("job_id","AD_VP");
        System.out.println("row2.toString() = " + row2.toString());
        System.out.println("row2.get(\"salary\") = " + row2.get("salary"));

        List<Map<String,Object >> queryData=new ArrayList<>();
        queryData.add(row1);
        queryData.add(row2);

        //get the barış last naem
        System.out.println("queryData.get(0).get(\"last_name\") = " + queryData.get(0).get("last_name"));

        resultSet.close();
        statement.close();
        connection.close();

    }

    @Test
    public void metaExamle1() throws SQLException {
        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement
        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get results

        ResultSet resultSet = statement.executeQuery("Select first_name,last_name,salary,job_id from employees");
//get the resultsetObject
        ResultSetMetaData resultSetMetaData= resultSet.getMetaData();
    resultSet.next();
        Map<String,Object> row1=new LinkedHashMap<>();
        row1.put(resultSetMetaData.getColumnName(1),resultSet.getString(1));
        row1.put(resultSetMetaData.getColumnName(2),resultSet.getString(2));
        row1.put(resultSetMetaData.getColumnName(3),resultSet.getString(3));
        row1.put(resultSetMetaData.getColumnName(4),resultSet.getString(4));

        System.out.println("row1.toString() = " + row1.toString());

        resultSet.next();
        Map<String,Object> row2=new LinkedHashMap<>();
        row2.put(resultSetMetaData.getColumnName(1),resultSet.getString(1));
        row2.put(resultSetMetaData.getColumnName(2),resultSet.getString(2));
        row2.put(resultSetMetaData.getColumnName(3),resultSet.getString(3));
        row2.put(resultSetMetaData.getColumnName(4),resultSet.getString(4));

        System.out.println("row2.toString() = " + row2.toString());

        List<Map<String,Object >> queryData=new ArrayList<>();
        queryData.add(row1);
        queryData.add(row2);

        //get the barış last naem
        System.out.println("queryData.get(0).get(\"last_name\") = " + queryData.get(0).get("last_name"));

        resultSet.close();
        statement.close();
        connection.close();

    }

}
