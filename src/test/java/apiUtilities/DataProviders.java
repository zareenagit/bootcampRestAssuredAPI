package apiUtilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {
    
    // Path to the Excel file
    private static final String EXCEL_PATH = System.getProperty("user.dir") + "/src/test/resources/testdata/userdata.xlsx";

    // DataProvider for POST requests
    @DataProvider(name = "PostData")
    public Object[][] getAllDataPost() throws IOException {
        return getDataFromExcel(EXCEL_PATH, "POST");
    }
    
    // DataProvider for PUT requests
    @DataProvider(name = "PutData")
    public Object[][] getAllDataPut() throws IOException {
        return getDataFromExcel(EXCEL_PATH, "PUT");
    }

    // DataProvider for GET requests
    @DataProvider(name = "GetData")
    public Object[][] getAllDataGet() throws IOException {
        return getDataFromExcel(EXCEL_PATH, "GET");
    }

    // DataProvider for DELETE requests
    @DataProvider(name = "DeleteData")
    public Object[][] getAllDataDelete() throws IOException {
        return getDataFromExcel(EXCEL_PATH, "DELETE");
    }

    // Helper method to fetch data from the Excel sheet
    private Object[][] getDataFromExcel(String path, String sheetName) throws IOException {
        XLUtility xl = new XLUtility(path);
        
        int rownum = xl.getRowCount(sheetName); // Get row count for the specified sheet
        int colcount = xl.getCellCount(sheetName, 0); // Get column count

        Object[][] apidata = new Object[rownum][colcount];
        
        for (int i = 1; i <= rownum; i++) {
            for (int j = 0; j < colcount; j++) {
                apidata[i - 1][j] = xl.getCellData(sheetName, i, j);
            }
        }
        return apidata;
    }
}
