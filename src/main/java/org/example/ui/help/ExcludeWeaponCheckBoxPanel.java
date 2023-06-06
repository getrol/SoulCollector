package org.example.ui.help;

import org.example.generators.help.equipment.Weapon;

import javax.swing.*;
import java.awt.*;

public class ExcludeWeaponCheckBoxPanel extends ExcludeCheckBoxPanel{
    Weapon [] weapons;
    EquipmentCheckBox[] equipmentCheckBoxes;

    public ExcludeWeaponCheckBoxPanel(Weapon[] weapons) {
        this.weapons = weapons;

        setBackground(Color.gray);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Label weaponLabel = new Label("Исключить оружие: ");
        add(weaponLabel);

        equipmentCheckBoxes = new EquipmentCheckBox[weapons.length];
        for (int i = 0; i < weapons.length; i++) {
            EquipmentCheckBox equipmentCheckBox = new EquipmentCheckBox(weapons[i].getName(), weapons[i]);
            equipmentCheckBoxes[i] = equipmentCheckBox;
            add(equipmentCheckBox);
        }



    }




}