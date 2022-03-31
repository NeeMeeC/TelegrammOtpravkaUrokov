import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class TEST02 {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static void main(String[] args) throws IOException {
        Date currentDate = new Date();
        XSSFWorkbook workbook = new XSSFWorkbook(".\\Task01.xlsx");
        XSSFSheet sheet = workbook.getSheetAt(2);

        Row row = sheet.getRow(0);
        Cell cell;
        Iterator<Cell> cellIterator = row.cellIterator();
        int dateColumn = -1;
        while (cellIterator.hasNext()) {
            cell = cellIterator.next();
            if (cell.getCellType().ordinal() == 2 && cell.getStringCellValue().equalsIgnoreCase("дата")) {
                dateColumn = cell.getColumnIndex();
                break;
            }
        }
        if (dateColumn == -1) {
            System.err.println("Не зайден столбес с датами занятий!");
            return;
        }
        Iterator<Row> rowIterator = sheet.iterator();
        int rowIsEmpty = 0;
        String taskDate;
        while (rowIterator.hasNext()) {

            row = rowIterator.next();
            System.out.print("Строка " + (row.getRowNum() + 1) + "    ");
            if (row.getCell(dateColumn).getCellType().ordinal() == 1 || row.getCell(dateColumn).getCellType().ordinal() == 3) {
                rowIsEmpty = 0;
                try {
                    taskDate = dateFormat.format(row.getCell(dateColumn).getDateCellValue());
                    if (taskDate.equalsIgnoreCase(dateFormat.format(currentDate))) {
                        System.out.println(taskDate + " ←");
                    }else System.out.println(taskDate);
                } catch (Exception e) {
                    System.out.println();
                }

            }
            else {
                rowIsEmpty++;
                System.out.println();
                if (rowIsEmpty > 2) break;
            }
        }
    }
}
