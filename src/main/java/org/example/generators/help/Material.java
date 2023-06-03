package org.example.generators.help;

public class Material {

    private final Rarity rarity;
    private final String name;
    private boolean canBeWeapon = true;
    private boolean canBeArmor = true;
    private final String armorFeatures;
    private final String weaponFeatures;
    private final int physicalDefence;
    private final int magicDefence;
    private final int healthPoints;
    private final int physicalDamage;
    private final int magicDamage;


    //Проверить, можно ли быть доспехом или армором.
    public Material(Rarity rarity, String name, String armorFeatures, String weaponFeatures, int physicalDefence, int magicDefence, int healthPoints, int physicalDamage, int magicDamage) {
        this.rarity = rarity;
        this.name = name;
        this.armorFeatures = armorFeatures;
        this.weaponFeatures = weaponFeatures;
        this.physicalDefence = physicalDefence;
        this.magicDefence = magicDefence;
        this.healthPoints = healthPoints;
        this.physicalDamage = physicalDamage;
        this.magicDamage = magicDamage;

        if (physicalDefence == -1 || magicDefence == -1 || healthPoints == -1) {
            canBeArmor = false;
        }

        if (physicalDamage == -1 || magicDamage == -1) {
            canBeWeapon = false;
        }
    }

    public Rarity getRarity() {
        return rarity;
    }

    public String getName() {
        return name;
    }

    public boolean isCanBeWeapon() {
        return canBeWeapon;
    }

    public boolean isCanBeArmor() {
        return canBeArmor;
    }

    public String getArmorFeatures() {
        return armorFeatures;
    }

    public String getWeaponFeatures() {
        return weaponFeatures;
    }

    public int getPhysicalDefence() {
        return physicalDefence;
    }

    public int getMagicDefence() {
        return magicDefence;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getPhysicalDamage() {
        return physicalDamage;
    }

    public int getMagicDamage() {
        return magicDamage;
    }

    @Override
    public String toString() {
        return rarity + " " + name + " " + armorFeatures + " / " + weaponFeatures + " PDef: " + physicalDefence + ", MDef: " + magicDefence + ", Hp: " + healthPoints
                + ", PDam: " + physicalDamage + ", MDam: " + magicDamage;
    }
}
