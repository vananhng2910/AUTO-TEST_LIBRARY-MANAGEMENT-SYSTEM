package setupbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelHelpers {
    private FileInputStream fis;
    private Workbook wb;
    private Sheet sh;
    private Cell cell;
    private Row row;
    private String excelFilePath;
    private Map<String, Integer> columns = new HashMap<>();
    //setup file Excel
    public void setExcelFile(String ExcelPath, String SheetName) throws Exception {
        try {
            File f = new File(ExcelPath);
            if (!f.exists()) {
                f.createNewFile();
                System.out.println("File doesn't exist, so created!");
            }
            fis = new FileInputStream(ExcelPath);
            //tạo workbook phù hợp với định dạng file input
            wb = WorkbookFactory.create(fis);
            sh = wb.getSheet(SheetName);
            if (sh == null) {
                sh = wb.createSheet(SheetName);
            }
            this.excelFilePath = ExcelPath;
            //thêm tên các cột và thứ tự cột vào map columns = cách lấy dữ liệu các ô ở dòng đầu tiên của các cột
            sh.getRow(0).forEach(cell ->{
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });
        } catch (Exception e) {
            System.out.println("Khong tim thay file!");
        }
    }
    //Đọc dữ liệu theo hàng, cột
    public String getCellData1(int rownum, int colnum) throws Exception{
        try{
            cell = sh.getRow(rownum).getCell(colnum);
            String CellData = null;
            switch (cell.getCellType()){
                case STRING:
                    CellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)){
                    	//nếu là dữ liệu ngày tháng thì trả về định dạng ngày tháng
                        CellData = String.valueOf(cell.getDateCellValue());
                    }
                    else{
                    	//trả kết quả ô dữ liệu về 1 số
                        CellData = String.valueOf((long)cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    CellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    CellData = "";
                    break;
            }
            return CellData;
        }catch (Exception e){
            return "fail";
        }
    }
    //Đọc dữ liệu từ trang tính theo tên cột và số hàng
    public String getCellData(String columnName, int rownum) throws Exception {
        return getCellData1(rownum, columns.get(columnName));
    }
    //Lấy số lượng bản ghi dữ liệu trong trang tính
    public int rownum() {
    	return sh.getLastRowNum();
    	
    }
}