package org.example.generators.help;

public class Weapon implements Equipment{
    private String name;
    private String materialName;
    private String weaponFeatures;
    private String damage;
    private String distance;

    public Weapon(String name, String materialName, String weaponFeatures, String damage, String distance) {
        this.name = name;
        this.materialName = materialName;
        this.weaponFeatures = weaponFeatures;
        this.damage = damage;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public String getEquipment(){
        String result = name + " из " + materialName + ". Урон: " + damage + ", " + weaponFeatures;
        if (!distance.equals("0")){
            result += " Дистанция: " + distance;
        }
        return result;
    }
}
