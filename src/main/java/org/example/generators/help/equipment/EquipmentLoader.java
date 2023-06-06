package org.example.generators.help.equipment;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class EquipmentLoader {
    public Armor[] rawArmorList = {new Armor("Шлем"), new Armor("Доспех"), new Armor("Обувь"), new Armor("Щит")};
    public Weapon[] rawWeaponList;


    public EquipmentLoader() {
        try {
            //Подключаемся к файлу
            String fileName = "G:\\Копия Диска\\Ролевки\\Накопитель душ\\Механики и лут\\Сферы школ\\Сферы ремесла\\MaterialsAndWeapons.xlsx";
            FileInputStream fileInputStream = new FileInputStream(fileName);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet xssfSheet = xssfWorkbook.getSheet("Weapons");

            //Создаем итератор и подсписок материалов, относящийся к нулевой редкости.
            boolean hasStart = false;
            Iterator<Row> rowIterator = xssfSheet.rowIterator();
            WeaponConverter weaponConverter = new WeaponConverter();
            ArrayList<Weapon> arrayList = new ArrayList<>();


            //Цикл для итерирования строк
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                Cell currentCell = cellIterator.next();
                String currentStringCell = currentCell.getStringCellValue();

                if (currentStringCell.equals("Доспехи")) { //Определяет не закончился ли файл.
                    break;
                } else if (currentStringCell.equals("Оружие")) { //Определяет, когда начнутся оружия, а не заголовки и ставит метку
                    hasStart = true;
                } else if (hasStart) {

                    Weapon currentWeapon;
                    Cell[] cells = new Cell[4]; //Определяет диапазон ячеек, вручную потому что вряд ли будет меняться.
                    cells[0] = currentCell;

                    for (int i = 1; i < cells.length; i++) {
                        cells[i] = cellIterator.next();
                    }


                    currentWeapon = weaponConverter.makeWeapon(cells);
                    arrayList.add(currentWeapon);

                }
            }
            rawWeaponList = arrayList.toArray(new Weapon[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
