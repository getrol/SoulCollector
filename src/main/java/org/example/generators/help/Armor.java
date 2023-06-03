package org.example.generators.help;

public class Armor implements Equipment{
    private String name;
    private String materialName;
    private String armorFeatures;
    private String defence;

    public Armor(String name, String materialName, String armorFeatures, String defence) {
        this.name = name;
        this.materialName = materialName;
        this.armorFeatures = armorFeatures;
        this.defence = defence;
    }

    public String getEquipment(){
        return name + " из " + materialName + ". Броня: " + defence + ", " + armorFeatures;
    }
}
