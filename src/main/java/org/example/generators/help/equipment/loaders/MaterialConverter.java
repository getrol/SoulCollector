package org.example.generators.help.equipment.loaders;

import org.apache.poi.ss.usermodel.Cell;
import org.example.generators.help.equipment.types.Rarity;
import org.example.generators.help.equipment.types.Material;

public class MaterialConverter implements MyConverter{

    public Material makeMaterial(Cell[] cells) {

        String[] rawMaterial = makeStringsValueFromCells(cells);

        Rarity rarity = determineRarity(rawMaterial[0]);
        String name = determineName(rawMaterial[1]);
        String armorFeatures = determineArmorFeatures(rawMaterial[2]);
        String weaponFeatures = determineWeaponFeatures(rawMaterial[3]);
        int physicalDefence = determinePhysicalDefence(rawMaterial[4]);
        int magicDefence = determineMagicDefence(rawMaterial[5]);
        int healthPoints = determineHealthPoints(rawMaterial[6]);
        int physicalDamage = determinePhysicalDamage(rawMaterial[7]);
        int magicDamage = determineMagicDamage(rawMaterial[8]);

        return new Material(rarity, name, armorFeatures, weaponFeatures
                , physicalDefence, magicDefence, healthPoints, physicalDamage, magicDamage);
    }

    private Rarity determineRarity(String rawRarity) {

        return switch (rawRarity.toLowerCase()) {
            case "обычное" -> Rarity.Common;
            case "редкое" -> Rarity.Rare;
            case "очень редкое" -> Rarity.VeryRare;
            case "эпическое" -> Rarity.Epic;
            case "мастерское" -> Rarity.Master;
            case "легендарное" -> Rarity.Legendary;
            default -> throw new IllegalArgumentException();
        };
    }

    private String determineName(String name) {
        return name;
    }

    private String determineArmorFeatures(String cell) {
        if (cell.equals("")){
            return "Свойств нет";
        } else {
            return cell;
        }
    }

    private String determineWeaponFeatures(String cell) {
        if (cell.equals("")){
            return "Свойств нет";
        } else {
            return cell;
        }
    }

    private int determinePhysicalDefence(String cell) {
        int number;
        try {
            number = Integer.parseInt(cell);
        } catch (NumberFormatException e){
            number = -1;
        }
        return number;
    }

    private int determineMagicDefence(String cell) {
        int number;
        try {
            number = Integer.parseInt(cell);
        } catch (NumberFormatException e){
            number = -1;
        }
        return number;
    }

    private int determineHealthPoints(String cell) {
        int number;
        try {
            number = Integer.parseInt(cell);
        } catch (NumberFormatException e){
            number = -1;
        }
        return number;
    }

    private int determinePhysicalDamage(String cell) {
        int number;
        try {
            number = Integer.parseInt(cell);
        } catch (NumberFormatException e){
            number = -1;
        }
        return number;
    }

    private int determineMagicDamage(String cell) {
        int number;
        try {
            number = Integer.parseInt(cell);
        } catch (NumberFormatException e){
            number = -1;
        }
        return number;
    }

}
