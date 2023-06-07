package org.example.generators.help.equipment;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public interface MyConverter {
    default String [] makeStringsValueFromCells(Cell[] cells) {
        String [] resultStrings = new String[cells.length];

        for (int i = 0; i < cells.length; i++) {
            Cell cell = cells[i];
            String result;
            if (cell.getCellType() == CellType.NUMERIC) {
                result = String.valueOf((int) cell.getNumericCellValue());
            } else {
                String nonResult = cell.getStringCellValue();
                try {
                    float j = Float.parseFloat(nonResult);
                    result = String.valueOf((int) j);
                } catch (NumberFormatException e) {
                    result = nonResult;
                }
            }
            resultStrings[i] = result;
        }

        return resultStrings;
    }
}
