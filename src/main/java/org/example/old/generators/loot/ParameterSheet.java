package org.example.old.generators.loot;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import workWithFiles.ExcelHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ParameterSheet {
    protected String filePath = "src/main/java/workWithFiles/files/Материалы Кузнецов.xlsx";
    protected XSSFSheet sheet;
    protected int columnId = 0;
    protected int countOfActiveRows;
    protected Map<String, RowsRange> rowRangesWithParameter = null;

    public ParameterSheet(String filePath, int columnId) {
        this.filePath = filePath;
        this.columnId = columnId;
    }

    public ParameterSheet(String sheetName) throws IOException {
        File file = new File(filePath);
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(file));
        this.sheet = myExcelBook.getSheet(sheetName);
    }

    public ParameterSheet(XSSFSheet sheet) {
        this.sheet = sheet;
    }

    protected void calculateRangeWithParameter() {
        if (countOfActiveRows == 0) {
            countOfActiveRows = ExcelHelper.getLastActiveRowNumber(sheet)+1;
        }
        if (rowRangesWithParameter == null) {
            Map<String, RowsRange> localRowRangesWithParameter = new HashMap<>();
            Row row = sheet.getRow(1);
            Cell cell = row.getCell(columnId);
            String parameterName = cell.getStringCellValue();

            int startRowIndex = 1;
            for (int i = 1; i < countOfActiveRows; i++) {
                row = sheet.getRow(i);
                cell = row.getCell(columnId);
                if (!cell.getStringCellValue().equals(parameterName)) {
                    localRowRangesWithParameter.put(parameterName, new RowsRange(startRowIndex, i - 1));
                    startRowIndex = i;
                    parameterName = cell.getStringCellValue();
                }

            }
            rowRangesWithParameter = localRowRangesWithParameter;
        }

    }

    public void listToSting() {
        if (rowRangesWithParameter == null) {
            calculateRangeWithParameter();
        }
        for (Map.Entry entry : rowRangesWithParameter.entrySet()) {
            String key = (String) entry.getKey();
            RowsRange value = (RowsRange) entry.getValue();
            String text = String.format("Редкость: %s, начальная строка: %d, конечная строка: %d", key, value.getRowIndexLow(), value.getRowIndexUp());
            System.out.println(text);
        }

    }

    public int [] getParameterRange (String stringParameter){
        if (rowRangesWithParameter == null){
            calculateRangeWithParameter();
        }
        int [] range = new int[2];
        range[0] = rowRangesWithParameter.get(stringParameter).getRowIndexLow();
        range[1] = rowRangesWithParameter.get(stringParameter).getRowIndexUp();
        return range;
    }


    public String [] getOneParameterList (String stringsParameter){
        if (rowRangesWithParameter == null){
            calculateRangeWithParameter();
        }
        RowsRange rowsRange = rowRangesWithParameter.get(stringsParameter);
        int rowIndexLow = rowsRange.getRowIndexLow();
        int rowIndexUp = rowsRange.getRowIndexUp();
        int count = rowIndexUp-rowIndexLow;
        String [] oneRarityList = new String[count];

        for (int i = 0; i < count; i++) {
            oneRarityList[i] = sheet.getRow(rowIndexLow+i).getCell(1).getStringCellValue();
        }
        return oneRarityList;
    }
    public class RowsRange {
        protected int rowIndexLow;
        protected int rowIndexUp;

        public RowsRange(int rowIndexLow, int rowIndexUp) {
            this.rowIndexLow = rowIndexLow;
            this.rowIndexUp = rowIndexUp;
        }

        protected int getRowIndexLow() {
            return rowIndexLow;
        }

        protected int getRowIndexUp() {
            return rowIndexUp;
        }


    }
}
