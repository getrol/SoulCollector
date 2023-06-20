package org.example.ui;

import org.example.generators.EquipmentGenerator;
import org.example.generators.help.equipment.*;
import org.example.ui.help.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow extends JFrame {

    EquipmentGenerator equipmentGenerator;

    ArmorWeaponRadioButtonPanel armorWeaponRadioButtonPanel;
    ExcludeCheckBoxPanel armorCheckBoxesPanel;
    ExcludeCheckBoxPanel weaponCheckBoxesPanel;

    RarityCheckBoxJPanel rarityCheckBoxJPanel;

    public StartWindow() throws HeadlessException {

        this.equipmentGenerator = new EquipmentGenerator(new MaterialLoader(), new EquipmentLoader());

        this.armorCheckBoxesPanel = new ExcludeArmorCheckBoxPanel(equipmentGenerator.getEquipmentLoader().rawArmorList);
        this.weaponCheckBoxesPanel = new ExcludeWeaponCheckBoxPanel(equipmentGenerator.getEquipmentLoader().rawWeaponList);

        this.armorWeaponRadioButtonPanel = new ArmorWeaponRadioButtonPanel(armorCheckBoxesPanel, weaponCheckBoxesPanel);
        this.rarityCheckBoxJPanel = new RarityCheckBoxJPanel();

        this.setName("Main");
        //this.setSize(400, 800);
        this.setBounds(300,100,800,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void buildUI() {

        //RadioPanel to Frame
        add(armorWeaponRadioButtonPanel, BorderLayout.NORTH);

        //Armor CheckBoxes
        add(armorCheckBoxesPanel, BorderLayout.WEST);
        //Equipment JCheckBoxes
        add(weaponCheckBoxesPanel, BorderLayout.EAST);
        //Rarity JCheckBoxes
        add(rarityCheckBoxJPanel, BorderLayout.SOUTH);

        JButton centerButton = new JButton("Generate");
        centerButton.addActionListener(new MyGenerateActionListener());
        add(centerButton, BorderLayout.CENTER);


        this.setVisible(true);
    }

    class MyGenerateActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JDialog jDialog = new JDialog();
            jDialog.setSize(1000,1000);
            jDialog.setVisible(true);

            ExcludeCheckBoxPanel.EquipmentCheckBox [] armorCheckBoxes = armorCheckBoxesPanel.getSelectedCheckBox();
            ExcludeCheckBoxPanel.EquipmentCheckBox [] weaponCheckBoxes = weaponCheckBoxesPanel.getSelectedCheckBox();

            JLabel label1 = new JLabel();
            jDialog.add(label1);


//            StringBuilder stringBuilder1 = new StringBuilder();
//            for (ExcludeCheckBoxPanel.EquipmentCheckBox a :
//                    armorCheckBoxes) {
//                stringBuilder1.append(a.getEquipment().getName()).append(" ");
//            }
//
//            stringBuilder1.append("\n");
//            for (ExcludeCheckBoxPanel.EquipmentCheckBox a :
//                    weaponCheckBoxes) {
//                stringBuilder1.append(a.getEquipment().getName()).append(" ");
//            }
//            label1.setText(stringBuilder1.toString());

            //Weapon weapon = (Weapon) weaponCheckBoxes[0].getEquipment();
            //System.out.println(weapon.getDamageByMaterial());
        }
    }
}
