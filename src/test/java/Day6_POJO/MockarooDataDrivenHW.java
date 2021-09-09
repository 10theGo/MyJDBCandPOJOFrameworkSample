package Day6_POJO;
import org.apache.commons.math3.optim.nonlinear.scalar.LineSearch;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class MockarooDataDrivenHW {


    //Homework-1
    //1-Create csv file from mackaroo website, which includes name,gender,phone
    //2-Download excel file
    //3- using testng data provider and apache poi create data driven posting from spartan



    @Test
    public void mockarooTest(){

        ExcelUtils excelUtils = new ExcelUtils("/Users/onur/IdeaProjects/eu5-jdbc-practice/src/test/resources/MOCK_DATA.xlsx", "Sheet 1 - MOCK_DATA");

        List<Map<String, String>> dataList = excelUtils.getDataList();

        System.out.println("dataList.toString() = " + dataList.toString());


    }

}
