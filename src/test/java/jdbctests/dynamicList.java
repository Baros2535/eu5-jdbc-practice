package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class dynamicList {
    String dbUrl="jdbc:oracle:thin:@44.197.133.197:1521:xe";
    String dbUsername="hr";
    String dbPassword="hr";

    @Test
    public void metaExamle1() throws SQLException {
        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement
        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get results

        ResultSet resultSet = statement.executeQuery("Select * from countries");
//get the resultsetObject
        ResultSetMetaData resultSetMetaData= resultSet.getMetaData();

        List<Map<String,Object >> queryData=new ArrayList<>();
        int columnCount = resultSetMetaData.getColumnCount();

        while(resultSet.next()){
          Map<String,Object> row=new LinkedHashMap<>();
            for (int i = 1; i <=columnCount ; i++) {
         row.put(resultSetMetaData.getColumnName(i),resultSet.getObject(i));
            }
            queryData.add(row);
        }

      //  System.out.println(queryData);

        for (Map<String, Object> queryDatum : queryData) {
            System.out.println(queryDatum);
        }

        resultSet.close();
        statement.close();
        connection.close();

    }

}
