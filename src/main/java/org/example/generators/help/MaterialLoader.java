package org.example.generators.help;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
            FileInputStream fileInputStream = new FileInputStream(new File(fileName));
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet xssfSheet = xssfWorkbook.getSheet("Materials");

            boolean hasStart = false;
            ArrayList<Material> materialArrayList = new ArrayList<>();
            Iterator<Row> rowIterator = xssfSheet.rowIterator();


            String currentRarity = null;
            rowIteratorLabel:
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                String currentCell = cellIterator.next().getStringCellValue();

                if (currentCell.equals("Finish")) {
                    break rowIteratorLabel;
                } else if (currentCell.equals("Start")) {
                    hasStart = true;
                } else if (hasStart) {

                    Material currentMaterial;

                    String rarity = currentCell;
                    String name = cellIterator.next().getStringCellValue();

                    Cell ownCell = cellIterator.next();
                    String features [] = ownCell.getStringCellValue().split("/");
                    String weaponFeatures = null;
                    String armorFeatures = null;

                    if (features.length>1){
                        armorFeatures = features[0];
                        weaponFeatures = features[1];
                    } else {
                        armorFeatures = ownCell.getStringCellValue();
                    }



                    String physicalDefence;
                    String magicDefence;
                    String healthPoints;

                    String physicalDamage;
                    String magicDamage;

                    if (!rarity.equals(currentRarity)) {
                        if (currentRarity != null) {
                            materialsByRarity.add(materialArrayList);
                            materialArrayList = new ArrayList<>();
                        }
                        currentRarity = rarity;

                    }


                    physicalDefence = makeStringValue(cellIterator.next());
                    magicDefence = makeStringValue(cellIterator.next());
                    healthPoints = makeStringValue(cellIterator.next());

                    physicalDamage = makeStringValue(cellIterator.next());
                    magicDamage = makeStringValue(cellIterator.next());


                    currentMaterial = new Material(rarity, name, armorFeatures, weaponFeatures, physicalDefence, magicDefence, healthPoints, physicalDamage, magicDamage);
                    materialArrayList.add(currentMaterial);

                }
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void printMaterialsByRarity() {

        for (ArrayList list :
                materialsByRarity) {
            for (int i = 0; i < list.size(); i++) {
                Material oneMaterial = (Material) list.get(i);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Название: ");
                stringBuilder.append(oneMaterial.getName());

                stringBuilder.append("\nСвойства: ");
                stringBuilder.append(oneMaterial.getArmorFeatures())
                        .append(" / ").append(oneMaterial.getWeaponFeatures());

                stringBuilder.append("\nПоказатели брони: (");
                stringBuilder.append(oneMaterial.getPhysicalDefence()).append(",")
                        .append(oneMaterial.getMagicDefence()).append(",")
                        .append(oneMaterial.getHealthPoints()).append(")");

                stringBuilder.append("\nПоказатели урона: (");
                stringBuilder.append(oneMaterial.getPhysicalDamage()).append(",")
                        .append(oneMaterial.getMagicDamage()).append(")").append("\n");

                System.out.println(oneMaterial.getRarity() + ": ");
                System.out.println(stringBuilder.toString());
            }

        }
    }

    private String makeStringValue(Cell cell) {
        String result;
        if (cell.getCellType() == CellType.NUMERIC) {
            result = String.valueOf((int) cell.getNumericCellValue());
        } else {
            String nonResult = cell.getStringCellValue();
            try {
                float i = Float.parseFloat(nonResult);
                result = String.valueOf((int) i);
            } catch (NumberFormatException e) {
                result = nonResult;
            }
        }
        return result;
    }

    public Material[] formQueryWithoutWeapons(Rarity rarityFrom, Rarity rarityTo, int ... amount) {
        int startRarity = 0;
        int finishRarity = 0;

        startRarity = switch (rarityFrom){
            case Common -> 0;
            case Rare -> 1;
            case VeryRare -> 2;
            case Epic ->  3;
            case Master -> 4;
            case Legendary -> 5;
        };

        finishRarity = switch (rarityTo){
            case Common -> 0;
            case Rare -> 1;
            case VeryRare -> 2;
            case Epic ->  3;
            case Master -> 4;
            case Legendary -> 5;
        };
        int raritySize = finishRarity-startRarity+1;
        Random random = new Random();
        if (amount.length!=raritySize){
            throw new IllegalArgumentException();
        }
        ArrayList<Material> arrayList = new ArrayList<>();

        int t = 0;
        for (int i = startRarity; i <finishRarity+1; i++) {
            ArrayList<Material> arrayListArrayList = materialsByRarity.get(i);
            for (int j = 0; j < amount[t]; j++) {
                arrayList.add(arrayListArrayList.get(random.nextInt(0, arrayListArrayList.size())));
            }
            t++;
        }
        return arrayList.toArray(new Material[arrayList.size()]);
    }
}
