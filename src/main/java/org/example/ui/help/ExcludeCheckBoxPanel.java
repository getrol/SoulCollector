package org.example.ui.help;

import org.example.generators.help.equipment.Equipment;

import javax.swing.*;

public abstract class ExcludeCheckBoxPanel extends JPanel {

    public class EquipmentCheckBox extends JCheckBox{
        Equipment equipment;

        public EquipmentCheckBox(String text, Equipment equipment) {
            super(text);
            this.equipment = equipment;
        }
    }

}
