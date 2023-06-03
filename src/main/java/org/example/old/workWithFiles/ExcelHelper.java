package org.example.old.workWithFiles;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelHelper {
    public static int getLastActiveRowNumber(Sheet sheet) {
        int rowIndex = 0;
        Row row = sheet.getRow(rowIndex);
        Cell c = null;
        try {
            c = row.getCell(0);
        } catch (Exception e) {
            return rowIndex;
        }
        while (!c.getStringCellValue().equals("Конечная ячейка") && rowIndex != 1000) {
            rowIndex++;
            row = sheet.getRow(rowIndex);
            if (row != null) {
                c = row.getCell(0);
            } else {
                break;
            }
        }
        return rowIndex;

    }
}
