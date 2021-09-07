package jdbctests;

import org.testng.asserts.SoftAssert;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        String dbUrl="jdbc:oracle:thin:@44.197.133.197:1521:xe";
  String dbUsername="hr";
  String dbPassword="hr";

  //create connection
        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
  //create statement
        Statement statement=connection.createStatement();
  //run query and get results

        ResultSet resultSet1 = statement.executeQuery("Select * from regions");
        ResultSet resultSet2 = statement.executeQuery("Select first_name,last_name,salary from employees");
     /*   // move to first row
        resultSet.next();
        System.out.println(resultSet.getInt(1)+"-" +resultSet.getString("region_name"));
        System.out.println(resultSet.getInt(1)+"-" +resultSet.getString(2));
        resultSet.next();
        System.out.println(resultSet.getInt(1)+"-" +resultSet.getString("region_name"));
        System.out.println(resultSet.getInt(1)+"-" +resultSet.getString(2));

        */

       /* while (resultSet1.next()){
            System.out.println(resultSet1.getInt(1)+"-" +resultSet1.getString("region_name"));
        }
        */
        while (resultSet2.next()){
            System.out.println(resultSet2.getString(1)+"-" +resultSet2.getString(2)+"-"+resultSet2.getString(3));
        }
        //close all connections
        resultSet1.close();
        resultSet2.close();
        statement.close();
        connection.close();



    }
}
