package org.example.ui.help;

import org.example.generators.help.equipment.Equipment;

import javax.swing.*;
import java.util.ArrayList;

public abstract class ExcludeCheckBoxPanel extends JPanel {
    EquipmentCheckBox[] equipmentCheckBoxes;

    public static class EquipmentCheckBox extends JCheckBox {
        Equipment equipment;

        public EquipmentCheckBox(String text, Equipment equipment) {
            super(text);
            this.equipment = equipment;
        }


    }

    public Equipment[] getSelectedEquipment() {
        ArrayList<Equipment> equipments = new ArrayList<>();
        for (EquipmentCheckBox e :
                equipmentCheckBoxes) {
            if (e.isSelected()) {
                equipments.add(e.equipment);
            }
        }
        return equipments.toArray(new Equipment[0]);

    }

    public EquipmentCheckBox[] getSelectedCheckBox() {
        ArrayList<EquipmentCheckBox> equipments = new ArrayList<>();
        for (EquipmentCheckBox e :
                equipmentCheckBoxes) {
            if (e.isSelected()) {
                equipments.add(e);
            }
        }
        return equipments.toArray(new EquipmentCheckBox[0]);

    }
}
