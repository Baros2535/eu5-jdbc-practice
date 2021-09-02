package jdbctests;

import org.testng.annotations.Test;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class dbutils_practice {

    @Test
public void test1(){
    DBUtils.createConnection();
    List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap("Select * from departments");

    for (Map<String, Object> stringObjectMap : queryResultMap) {
        System.out.println(stringObjectMap);
    }


    DBUtils.destroy();
}

    @Test
    public void test2(){
        DBUtils.createConnection();
        List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap("Select first_name,last_name from employees where employee_id=100");
        Map<String, Object> rowMap = DBUtils.getRowMap("Select first_name,last_name from employees where employee_id=100");
        System.out.println(rowMap);
        System.out.println(queryResultMap);
        DBUtils.destroy();
    }

}
