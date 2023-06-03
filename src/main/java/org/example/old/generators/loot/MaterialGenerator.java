package org.example.old.generators.loot;

import java.util.*;


public class MaterialGenerator {
    private MaterialRaritySheet materialRaritySheet;
    private WeaponTierSheet weaponTierGenerator;

    public MaterialGenerator(MaterialRaritySheet materialRaritySheet, WeaponTierSheet weaponTierGenerator) {
        this.materialRaritySheet = materialRaritySheet;
        this.weaponTierGenerator = weaponTierGenerator;
    }

    public String [] getOneRarityRandomList (Rarity rarity, int count){
        String [] lootOneRarity = materialRaritySheet.getOneParameterList(EnumAdapter.rarityToString(rarity));
        String [] necessaryAmountOfLoot = new String[count];
        Random random = new Random();
        for (int i = 0; i < necessaryAmountOfLoot.length; i++) {
            necessaryAmountOfLoot[i] = lootOneRarity[random.nextInt(count)];
        }
        return necessaryAmountOfLoot;
    }
    public String [] getOneWeaponRandomList (Tier tier, int count){
        String [] lootOneRarity = weaponTierGenerator.getOneParameterList(EnumAdapter.tierToString(tier));
        String [] necessaryAmountOfLoot = new String[count];
        Random random = new Random();
        for (int i = 0; i < necessaryAmountOfLoot.length; i++) {
            necessaryAmountOfLoot[i] = lootOneRarity[random.nextInt(lootOneRarity.length)];
        }
        return necessaryAmountOfLoot;
    }

    public String [] getLotWeaponRandomList (Tier [] tiers, int [] counts){
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < tiers.length; i++) {
            arrayList.addAll(List.of(getOneWeaponRandomList(tiers[i], counts[i])));
        }
        return arrayList.toArray(new String[arrayList.size()]);
    }

    public String [] getOneWeaponAndRarityList (Tier tier, Rarity rarity, int count){
        String [] weapons = getOneWeaponRandomList(tier, count);
        MaterialRaritySheet.WeaponMaterial [] weaponMaterials = materialRaritySheet.getOneRarityWeaponMaterialsWithCount(rarity, count);
        String [] weaponAndRarity = new String[count];
        StringBuilder stringBuilder;
        for (int i = 0; i < weaponAndRarity.length; i++) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(weapons[i]);
            stringBuilder.append(" из ");
            stringBuilder.append(weaponMaterials[i].weaponName);
            stringBuilder.append(" с уроном: ");
            stringBuilder.append(weaponMaterials[i].weaponDamage);
            weaponAndRarity[i] = stringBuilder.toString();
        }
        return weaponAndRarity;
    }
}
