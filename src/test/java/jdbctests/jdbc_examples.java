package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class jdbc_examples {
    String dbUrl="jdbc:oracle:thin:@44.197.133.197:1521:xe";
    String dbUsername="hr";
    String dbPassword="hr";

    @Test
    public void test1() throws SQLException {
        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement
        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get results

        ResultSet resultSet = statement.executeQuery("Select * from departments");
        resultSet.last();
        System.out.println("resultSet.getRow() = " + resultSet.getRow());
        //we should go to before first row to write our data
        resultSet.beforeFirst();

        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        resultSet = statement.executeQuery("Select * from regions");
        System.out.println("=========================");

        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }



        resultSet.close();
        statement.close();
        connection.close();



    }

    @Test
    public void metaExamle() throws SQLException {
            Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
            //create statement
            Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            //run query and get results

             ResultSet resultSet = statement.executeQuery("Select * from employees");

           //get the databse related inside

        DatabaseMetaData dbMetaData= connection.getMetaData();
        System.out.println("User "+ dbMetaData.getUserName());
        System.out.println("dbMetaData.getDatabaseProductName() = " + dbMetaData.getDatabaseProductName());
        System.out.println("dbMetaData.getDatabaseProductVersion() = " + dbMetaData.getDatabaseProductVersion());
        System.out.println("dbMetaData.getDriverName() = " + dbMetaData.getDriverName());
        System.out.println("dbMetaData.getDriverVersion() = " + dbMetaData.getDriverVersion());

        System.out.println("===================");
      //get the resultSet object
       ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
       //how many columns we have

        System.out.println("resultSetMetaData.getColumnCount() = " + resultSetMetaData.getColumnCount());
         //columns name
        System.out.println("resultSetMetaData.getColumnName(1) = " + resultSetMetaData.getColumnName(1));

       // print all the columns name dynamicly
        for (int i = 1; i <=resultSetMetaData.getColumnCount() ; i++) {
            System.out.println(resultSetMetaData.getColumnName(i));
        }




        resultSet.close();
        statement.close();
        connection.close();

    }
}
