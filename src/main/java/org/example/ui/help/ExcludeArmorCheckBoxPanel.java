package org.example.ui.help;

import org.example.generators.help.equipment.Armor;

import javax.swing.*;
import java.awt.*;


public class ExcludeArmorCheckBoxPanel extends ExcludeCheckBoxPanel {
    Armor[] armors;
    EquipmentCheckBox[] equipmentCheckBoxes;

    public ExcludeArmorCheckBoxPanel(Armor[] armors) {
        this.armors = armors;

        setBackground(Color.CYAN);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Label armorLabel = new Label("Исключить броню: ");
        add(armorLabel);

        equipmentCheckBoxes = new EquipmentCheckBox[armors.length];
        for (int i = 0; i < armors.length; i++) {
            EquipmentCheckBox equipmentCheckBox = new EquipmentCheckBox(armors[i].getName(), armors[i]);
            equipmentCheckBoxes[i] = equipmentCheckBox;
            add(equipmentCheckBox);
        }
    }
}
