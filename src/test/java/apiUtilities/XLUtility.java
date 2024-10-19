package apiUtilities;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtility {

    private String path;

    public XLUtility(String path) {
        this.path = path;
    }

    public int getRowCount(String xlSheet) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook wb = new XSSFWorkbook(fi)) {
            XSSFSheet ws = wb.getSheet(xlSheet);
            return ws.getLastRowNum();
        }
    }

    public int getCellCount(String xlSheet, int rowNum) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook wb = new XSSFWorkbook(fi)) {
            XSSFSheet ws = wb.getSheet(xlSheet);
            XSSFRow row = ws.getRow(rowNum);
            return row.getLastCellNum();
        }
    }

    public String getCellData(String xlSheet, int rowNum, int columnNum) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook wb = new XSSFWorkbook(fi)) {
            XSSFSheet ws = wb.getSheet(xlSheet);
            XSSFRow row = ws.getRow(rowNum);
            if (row == null) {
                return "";
            }
            XSSFCell cell = row.getCell(columnNum);
            DataFormatter formatter = new DataFormatter();
            return cell != null ? formatter.formatCellValue(cell) : "";
        }
    }

    public void setCellData(String xlSheet, int rowNum, int columnNum, String data) throws IOException {
        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook wb = new XSSFWorkbook(fi)) {
            XSSFSheet ws = wb.getSheet(xlSheet);
            XSSFRow row = ws.getRow(rowNum);
            if (row == null) {
                row = ws.createRow(rowNum); // Create the row if it doesn't exist
            }
            XSSFCell cell = row.createCell(columnNum);
            cell.setCellValue(data);
            try (FileOutputStream fo = new FileOutputStream(path)) {
                wb.write(fo);
            }
        }
    }
}
