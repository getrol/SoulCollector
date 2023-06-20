package org.example.ui.help;

import org.example.generators.help.equipment.types.Equipment;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EquipmentCheckBoxPanel<T extends Equipment> extends JPanel {
    EquipmentCheckBox<T>[] equipmentCheckBoxes;

    public EquipmentCheckBoxPanel(T [] equipments) {
        setBackground(Color.CYAN);

        Container containerCheckBoxes = new Container();
        containerCheckBoxes.setLayout(new BoxLayout(containerCheckBoxes, BoxLayout.Y_AXIS));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Включить снаряжение: ");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);


        equipmentCheckBoxes = new EquipmentCheckBox[equipments.length];
        for (int i = 0; i < equipments.length; i++) {
            EquipmentCheckBox<T> equipmentCheckBox = new EquipmentCheckBox<>(equipments[i].getName(), equipments[i]);
            equipmentCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
            equipmentCheckBoxes[i] = equipmentCheckBox;
            containerCheckBoxes.add(equipmentCheckBox);
        }

        add(label);
        add(containerCheckBoxes);
    }



    public <P extends Equipment> P[] getSelectedEquipment(P [] p) {
        ArrayList<T> equipments = new ArrayList<>();
        for (EquipmentCheckBox<T> e :
                equipmentCheckBoxes) {
            if (e.isSelected()) {
                equipments.add(e.equipment);
            }
        }
        return equipments.toArray(p);

    }

    public EquipmentCheckBox<T>[] getSelectedCheckBox() {
        ArrayList<EquipmentCheckBox<T>> equipments = new ArrayList<>();
        for (EquipmentCheckBox<T> e :
                equipmentCheckBoxes) {
            if (e.isSelected()) {
                equipments.add(e);
            }
        }
        return equipments.toArray(new EquipmentCheckBox[0]);

    }

    public void makeCheckBoxesNotSelected (){
        for (EquipmentCheckBox<T> equipmentCheckBox :
                equipmentCheckBoxes) {
            equipmentCheckBox.setSelected(false);
        }
    }

    public static class EquipmentCheckBox <T extends Equipment> extends JCheckBox {
        T equipment;

        public EquipmentCheckBox(String text, T equipment) {
            super(text);
            this.equipment = equipment;
        }

        public T getEquipment() {
            return equipment;
        }
    }
}
