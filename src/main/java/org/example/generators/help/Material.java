package org.example.generators.help;

public class Material {

    private String rarity;
    private String name;
    private String armorFeatures;
    private String weaponFeatures;
    private String physicalDefence;
    private String magicDefence;
    private String healthPoints;
    private String physicalDamage;
    private String magicDamage;


    public Material() {
    }

    public Material(String rarity, String name, String armorFeatures, String weaponFeatures, String physicalDefence, String magicDefence, String healthPoints, String physicalDamage, String magicDamage) {
        this.rarity = rarity;
        this.name = name;
        this.armorFeatures = armorFeatures;
        this.weaponFeatures = weaponFeatures;
        this.physicalDefence = physicalDefence;
        this.magicDefence = magicDefence;
        this.healthPoints = healthPoints;
        this.physicalDamage = physicalDamage;
        this.magicDamage = magicDamage;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArmorFeatures() {
        return armorFeatures;
    }

    public void setArmorFeatures(String armorFeatures) {
        this.armorFeatures = armorFeatures;
    }

    public String getWeaponFeatures() {
        return weaponFeatures;
    }

    public void setWeaponFeatures(String weaponFeatures) {
        this.weaponFeatures = weaponFeatures;
    }

    public String getPhysicalDefence() {
        return physicalDefence;
    }

    public void setPhysicalDefence(String physicalDefence) {
        this.physicalDefence = physicalDefence;
    }

    public String getMagicDefence() {
        return magicDefence;
    }

    public void setMagicDefence(String magicDefence) {
        this.magicDefence = magicDefence;
    }

    public String getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(String healthPoints) {
        this.healthPoints = healthPoints;
    }

    public String getPhysicalDamage() {
        return physicalDamage;
    }

    public void setPhysicalDamage(String physicalDamage) {
        this.physicalDamage = physicalDamage;
    }

    public String getMagicDamage() {
        return magicDamage;
    }

    public void setMagicDamage(String magicDamage) {
        this.magicDamage = magicDamage;
    }

    public String printMaterial() {
        return rarity + " " + name + " " + armorFeatures + " / " + weaponFeatures +  " PDef: " + physicalDefence + ", MDef: " + magicDefence + ", Hp: " + healthPoints
                + ", PDam: " + physicalDamage + ", MDam: " + magicDamage;
    }
}
