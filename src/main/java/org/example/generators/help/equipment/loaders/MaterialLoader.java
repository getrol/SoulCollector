package org.example.generators.help.equipment.loaders;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.generators.help.equipment.types.Rarity;
import org.example.generators.help.equipment.types.Material;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class MaterialLoader {

    String fileName = "G:\\Копия Диска\\Ролевки\\Накопитель душ\\Механики и лут\\Сферы школ\\Сферы ремесла\\MaterialsAndWeapons.xlsx";
    HashMap<Rarity, Material[]> materialsByRarity = new HashMap<>();

    public MaterialLoader() {
        connectToFile();
    }

    public HashMap<Rarity, Material[]> getMaterialsByRarity() {
        return materialsByRarity;
    }

    public void connectToFile() {
        try {
            //Подключаемся к файлу
            FileInputStream fileInputStream = new FileInputStream(fileName);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet xssfSheet = xssfWorkbook.getSheet("Materials");

            //Создаем итератор и подсписок материалов, относящийся к нулевой редкости.
            boolean hasStart = false;
            ArrayList<Material> materialArrayList = new ArrayList<>();
            Iterator<Row> rowIterator = xssfSheet.rowIterator();
            MaterialConverter materialConverter = new MaterialConverter();


            Rarity currentRarity = null;
            //Цикл для итерирования строк
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                Cell currentCell = cellIterator.next();
                String currentStringCell = currentCell.getStringCellValue();

                if (currentStringCell.equals("Finish")) { //Определяет не закончился ли файл.
                    materialsByRarity.put(currentRarity, materialArrayList.toArray(new Material[0]));
                    break;
                } else if (currentStringCell.equals("Start")) { //Определяет, когда начнутся материалы, а не вставки и ставит метку
                    hasStart = true;
                } else if (hasStart) {

                    Material currentMaterial;
                    Cell[] cells = new Cell[9]; //Определяет диапазон ячеек, вручную потому что вряд ли будет меняться.
                    cells[0] = currentCell;

                    for (int i = 1; i < cells.length; i++) {
                        cells[i] = cellIterator.next();
                    }

                    currentMaterial = materialConverter.makeMaterial(cells); //Создает матриал на основе ячейки


                    //Проверка на то, сменилась ли редкость. Если да, то в карту кладется массив элементов. Если нет, то продолжает наполняться, пока не сменится.
                    if (currentRarity != currentMaterial.getRarity()) {
                        if (currentRarity != null) {
                            materialsByRarity.put(currentRarity, materialArrayList.toArray(new Material[0]));
                            materialArrayList = new ArrayList<>();
                        }
                        currentRarity = currentMaterial.getRarity();

                    }
                    materialArrayList.add(currentMaterial);

                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
