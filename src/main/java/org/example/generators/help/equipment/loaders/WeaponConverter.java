package org.example.generators.help.equipment.loaders;

import org.apache.poi.ss.usermodel.Cell;
import org.example.generators.help.equipment.types.Weapon;

public class WeaponConverter implements MyConverter{

    public Weapon makeWeapon(Cell[] cells) {

        String[] rawWeapon = makeStringsValueFromCells(cells);

        String name = determineName(rawWeapon[0]);
        float damageByMaterial = determineDamageByMaterial(rawWeapon[1]);
        float distanceByMaterial = determineDistanceByMaterial(rawWeapon[2]);
        String baseDistance = determineBaseDistance(rawWeapon[3]);


        return new Weapon(name,damageByMaterial,distanceByMaterial,baseDistance);
    }

    private String determineName(String name) {
        return name;
    }

    private float determineDamageByMaterial(String damage) {
        float number;
        try {
            number = Float.parseFloat(damage.replace(',', '.'));
        } catch (NumberFormatException e){
            number = 0;
        }
        return number;
    }
    private float determineDistanceByMaterial(String distance) {
        float number;
        try {
            number = Float.parseFloat(distance.replace(',', '.'));
        } catch (NumberFormatException e){
            number = 0;
        }
        return number;
    }
    private String determineBaseDistance(String baseDistance) {
        int number;
        try {
            number = Integer.parseInt(baseDistance);
            return null;
        } catch (NumberFormatException e){
            return baseDistance;
        }
    }
}
