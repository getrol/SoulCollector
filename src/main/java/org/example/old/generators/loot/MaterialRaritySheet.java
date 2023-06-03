package org.example.old.generators.loot;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MaterialRaritySheet extends ParameterSheet {

    public HashMap<String, Integer> rowsAvailableForWeapons = null;
    public int columnDamageId = 6;
    public int columnNameId = 1;

    public MaterialRaritySheet(String sheetName) throws IOException {
        super(sheetName);
    }

//    public HashMap<String, Integer> getRowsAvailableForWeapons() {
//        if (rowsAvailableForWeapons == null) {
//            HashMap<String, Integer> localRowsAvailableForWeapons = new HashMap<>();
//            for (int i = 1; i < countOfActiveRows; i++) {
//                Row row = sheet.getRow(i);
//                Cell cell = row.getCell(columnDamageId);
//                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
//                    localRowsAvailableForWeapons.put(row.getCell(1).getStringCellValue(), (int) cell.getNumericCellValue());
//                }
//            }
//        }
//        return rowsAvailableForWeapons;
//    }

    private WeaponMaterial[] getOneRarityRowsForWeapon(String stringRarity) {
        if (rowRangesWithParameter == null) {
            calculateRangeWithParameter();
        }
        RowsRange rowsRangeWithOneRarity = rowRangesWithParameter.get(stringRarity);
        int rowIndexLow = rowsRangeWithOneRarity.getRowIndexLow();
        int rowIndexUp = rowsRangeWithOneRarity.getRowIndexUp();
        int count = rowIndexUp - rowIndexLow;
        ArrayList<WeaponMaterial> localOnlyWeaponMaterials = new ArrayList<>();
        for (int i = rowIndexLow; i < rowIndexUp; i++) {
            Row row = sheet.getRow(i);
            if (row.getCell(columnDamageId).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                localOnlyWeaponMaterials.add(new WeaponMaterial(i, row.getCell(columnNameId).getStringCellValue(), (int) row.getCell(columnDamageId).getNumericCellValue()));
            }
        }
        return localOnlyWeaponMaterials.toArray(new WeaponMaterial[localOnlyWeaponMaterials.size()]);
    }
    public WeaponMaterial [] getOneRarityWeaponMaterialsWithCount (Rarity rarity, int count){
        WeaponMaterial [] availableMaterial = getOneRarityRowsForWeapon(EnumAdapter.rarityToString(rarity));
        WeaponMaterial [] weaponMaterials = new WeaponMaterial[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            weaponMaterials[i] = availableMaterial[random.nextInt(availableMaterial.length)];
        }
        return weaponMaterials;
    }


    public class WeaponMaterial {
        int rowNumber;
        String weaponName;
        int weaponDamage;

        public WeaponMaterial(int rowNumber, String weaponName, int weaponDamage) {
            this.rowNumber = rowNumber;
            this.weaponName = weaponName;
            this.weaponDamage = weaponDamage;
        }
    }
}
