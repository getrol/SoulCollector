package org.example.ui.help;

import org.example.generators.help.equipment.Armor;

import javax.swing.*;
import java.awt.*;


public class ExcludeArmorCheckBoxPanel extends ExcludeCheckBoxPanel {
    Armor[] armors;


    public ExcludeArmorCheckBoxPanel(Armor[] armors) {
        this.armors = armors;

        setBackground(Color.CYAN);

        Container containerCheckBoxes = new Container();
        containerCheckBoxes.setLayout(new BoxLayout(containerCheckBoxes, BoxLayout.Y_AXIS));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel armorLabel = new JLabel("Исключить броню: ");
        armorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        equipmentCheckBoxes = new EquipmentCheckBox[armors.length];
        for (int i = 0; i < armors.length; i++) {
            EquipmentCheckBox equipmentCheckBox = new EquipmentCheckBox(armors[i].getName(), armors[i]);
            equipmentCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
            equipmentCheckBoxes[i] = equipmentCheckBox;
            containerCheckBoxes.add(equipmentCheckBox);
        }

        add(armorLabel);
        add(containerCheckBoxes);
    }


}
