package org.example.generators.help;

import org.apache.poi.ss.usermodel.Cell;

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
            number = -1;
        }
        return number;
    }
    private float determineDistanceByMaterial(String distance) {
        float number;
        try {
            number = Float.parseFloat(distance.replace(',', '.'));
        } catch (NumberFormatException e){
            number = -1;
        }
        return number;
    }
    private String determineBaseDistance(String name) {
        return name;
    }
}
