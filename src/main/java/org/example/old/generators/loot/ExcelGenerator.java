package org.example.old.generators.loot;

import java.util.Arrays;

public class ExcelGenerator {
    public static void main(String[] args) {
        try {
            MaterialRaritySheet materialRaritySheet = new MaterialRaritySheet("Материалы");
            materialRaritySheet.listToSting();

            WeaponTierSheet weaponTierSheet = new WeaponTierSheet("Оружие");
            weaponTierSheet.listToSting();

            MaterialGenerator materialGenerator = new MaterialGenerator(materialRaritySheet, weaponTierSheet);
//            String [] s = materialGenerator.getOneRarityRandomList(Rarity.VERY_RARE, 6);
//            Arrays.stream(s).forEach(System.out::println);
//            String [] p = materialGenerator.getOneWeaponRandomList(Tier.SECOND, 6);
//            Arrays.stream(p).forEach(System.out::println);
            String [] d = materialGenerator.getOneWeaponAndRarityList(Tier.FIRST, Rarity.RARE, 20);
            Arrays.stream(d).forEach(System.out::println);



        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
