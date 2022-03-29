import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class TEST01 {
    private final static int dateColumn = 6;
    public static void main(String[] args) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

        String filename = "C:\\Users\\Andrey\\Documents\\java\\TelegrammOtpravkaUrokov\\book_01.xlsx";
        XSSFWorkbook workbook = new XSSFWorkbook(filename);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();

        Date date = null;
        String completed = "";

        while (true) {

            Date currentDate = new Date();

            if (!completed.equals(dateFormat.format(currentDate))) {

                System.out.println("Текущая дата:  " + dateFormat.format(currentDate));
                System.out.println();

                while (rowIterator.hasNext()) {
                    Row row =rowIterator.next();
                    System.out.print (row.getRowNum() + 1 + "    ");

                    try {

                        date = row.getCell(dateColumn - 1).getDateCellValue();

                        if (dateFormat.format(currentDate).equalsIgnoreCase(dateFormat.format(date))) {
                            /*System.out.println("***Приветствие***\n\n"); */ System.out.println(row.getCell(2).getStringCellValue() + "\n");
                            System.out.println("      Сегодня мы пройдём " + row.getCell(0).getStringCellValue());
                            System.out.println("      Название урока: " + row.getCell(1).getStringCellValue());
                            System.out.println("      Дополнительные материалы к уроку вы можете найти тут: " + row.getCell(3).getStringCellValue());
                        }

                    } catch (Exception e) {
                        System.out.println();
                    }

                }

                completed = dateFormat.format(currentDate);

            }

        }




    }
}
