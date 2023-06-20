package org.example.generators.help.equipment;

public class Weapon extends Equipment{
    final String name;
    final float damageByMaterial;
    final float distanceByMaterial;
    final String baseDistance;
    boolean isDistance = true;

    public Weapon(String name, float damageByMaterial, float distanceByMaterial, String baseDistance) {
        this.name = name;
        this.damageByMaterial = damageByMaterial;
        this.distanceByMaterial = distanceByMaterial;
        this.baseDistance = baseDistance;

        if (distanceByMaterial == 0 && baseDistance == null){
            isDistance = false;
        }
    }

    public float getDamageByMaterial() {
        return damageByMaterial;
    }

    public float getDistanceByMaterial() {
        return distanceByMaterial;
    }

    public String getBaseDistance() {
        return baseDistance;
    }

    public boolean isDistance() {
        return isDistance;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString(){
        String result = name + " . Урон от материала: " + damageByMaterial;
        if (isDistance){
            result += ", Дальность: " + distanceByMaterial+"+"+baseDistance;
        }
        return result;
    }
}
