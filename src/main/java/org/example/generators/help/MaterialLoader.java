package org.example.generators.help;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class MaterialLoader {

    String fileName = "G:\\Копия Диска\\Ролевки\\Накопитель душ\\Механики и лут\\Сферы школ\\Сферы ремесла\\MaterialsAndWeapons.xlsx";
    ArrayList<ArrayList<Material>> materialsByRarity = new ArrayList<>();

    public MaterialLoader() {
        connectToFile();
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

                    currentMaterial = materialConverter.makeMaterial(cells);

                    if (currentRarity != currentMaterial.getRarity()) {
                        if (currentRarity != null) {
                            materialsByRarity.add(materialArrayList);
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


    public Material[] formQueryWithoutWeapons(Rarity rarityFrom, Rarity rarityTo, int... amount) {
        int startRarity;
        int finishRarity;

        startRarity = switch (rarityFrom) {
            case Common -> 0;
            case Rare -> 1;
            case VeryRare -> 2;
            case Epic -> 3;
            case Master -> 4;
            case Legendary -> 5;
        };

        finishRarity = switch (rarityTo) {
            case Common -> 0;
            case Rare -> 1;
            case VeryRare -> 2;
            case Epic -> 3;
            case Master -> 4;
            case Legendary -> 5;
        };
        int raritySize = finishRarity - startRarity + 1;
        Random random = new Random();
        if (amount.length != raritySize) {
            throw new IllegalArgumentException();
        }
        ArrayList<Material> arrayList = new ArrayList<>();

        int t = 0;
        for (int i = startRarity; i < finishRarity + 1; i++) {
            ArrayList<Material> arrayListArrayList = materialsByRarity.get(i);
            for (int j = 0; j < amount[t]; j++) {
                arrayList.add(arrayListArrayList.get(random.nextInt(0, arrayListArrayList.size())));
            }
            t++;
        }
        return arrayList.toArray(new Material[0]);
    }
}
