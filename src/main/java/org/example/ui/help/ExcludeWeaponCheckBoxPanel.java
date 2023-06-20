package org.example.ui.help;

import org.example.generators.help.equipment.Weapon;

import javax.swing.*;
import java.awt.*;

public class ExcludeWeaponCheckBoxPanel extends ExcludeCheckBoxPanel{
    Weapon [] weapons;

    public ExcludeWeaponCheckBoxPanel(Weapon[] weapons) {
        this.weapons = weapons;

        setBackground(Color.gray);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Container containerCheckBoxes = new Container();
        containerCheckBoxes.setLayout(new BoxLayout(containerCheckBoxes, BoxLayout.PAGE_AXIS));

        JLabel weaponLabel = new JLabel("Исключить оружие: ");
        weaponLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(weaponLabel);
        add(containerCheckBoxes);

        equipmentCheckBoxes = new EquipmentCheckBox[weapons.length];
        for (int i = 0; i < weapons.length; i++) {
            EquipmentCheckBox equipmentCheckBox = new EquipmentCheckBox(weapons[i].getName(), weapons[i]);
            equipmentCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
            equipmentCheckBoxes[i] = equipmentCheckBox;
            containerCheckBoxes.add(equipmentCheckBox);
        }



    }




}