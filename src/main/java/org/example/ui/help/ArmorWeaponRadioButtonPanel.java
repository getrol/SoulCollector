package org.example.ui.help;

import org.example.generators.help.equipment.types.Armor;
import org.example.generators.help.equipment.types.Weapon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArmorWeaponRadioButtonPanel extends JPanel {
    JRadioButton armorRadioButton;
    JRadioButton weaponRadioButton;
    JRadioButton armorWeaponRadioButton;
    ButtonGroup armorWeaponChoiceButtonGroup;
    EquipmentCheckBoxPanel<Armor> armorPanel;
    EquipmentCheckBoxPanel<Weapon> weaponPanel;

    JRadioButton selectedButton;

    public ArmorWeaponRadioButtonPanel(EquipmentCheckBoxPanel<Armor> armorPanel, EquipmentCheckBoxPanel<Weapon> weaponPanel) {
        this.armorPanel = armorPanel;
        this.weaponPanel = weaponPanel;

        armorRadioButton = new JRadioButton("Броня", false);
        weaponRadioButton = new JRadioButton("Оружие", false);
        armorWeaponRadioButton = new JRadioButton("Броня/Оружие", true);
        selectedButton = armorWeaponRadioButton;

        armorWeaponChoiceButtonGroup = new ButtonGroup();
        armorWeaponChoiceButtonGroup.add(armorRadioButton);
        armorWeaponChoiceButtonGroup.add(armorWeaponRadioButton);
        armorWeaponChoiceButtonGroup.add(weaponRadioButton);

        MyListener myListener = new MyListener();
        armorRadioButton.addActionListener(myListener);
        weaponRadioButton.addActionListener(myListener);
        armorWeaponRadioButton.addActionListener(myListener);

        add(armorRadioButton);
        add(armorWeaponRadioButton);
        add(weaponRadioButton);
    }

    public JRadioButton getSelectedButton() {
        return selectedButton;
    }

    class MyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (armorRadioButton.isSelected()){
                armorPanel.setVisible(true);
                weaponPanel.setVisible(false);
                weaponPanel.makeCheckBoxesNotSelected();
            } else if (armorWeaponRadioButton.isSelected()){
                armorPanel.setVisible(true);
                weaponPanel.setVisible(true);
            } else if (weaponRadioButton.isSelected()) {
                armorPanel.setVisible(false);
                weaponPanel.setVisible(true);
                armorPanel.makeCheckBoxesNotSelected();
            }
        }
    }


}
