package org.example.ui.help;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArmorWeaponRadioButtonPanel extends JPanel {
    JRadioButton armorRadioButton;
    JRadioButton weaponRadioButton;
    JRadioButton armorWeaponRadioButton;
    ButtonGroup armorWeaponChoiceButtonGroup;
    ExcludeCheckBoxPanel armorPanel;
    ExcludeCheckBoxPanel weaponPanel;

    public ArmorWeaponRadioButtonPanel(ExcludeCheckBoxPanel armorPanel, ExcludeCheckBoxPanel weaponPanel) {
        this.armorPanel = armorPanel;
        this.weaponPanel = weaponPanel;

        armorRadioButton = new JRadioButton("Броня", false);
        weaponRadioButton = new JRadioButton("Оружие", false);
        armorWeaponRadioButton = new JRadioButton("Броня/Оружие", true);

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

    class MyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (armorRadioButton.isSelected()){
                armorPanel.setVisible(true);
                weaponPanel.setVisible(false);
            } else if (armorWeaponRadioButton.isSelected()){
                armorPanel.setVisible(true);
                weaponPanel.setVisible(true);
            } else if (weaponRadioButton.isSelected()) {
                armorPanel.setVisible(false);
                weaponPanel.setVisible(true);
            }
        }
    }


}
