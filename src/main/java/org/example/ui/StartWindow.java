package org.example.ui;

import org.example.generators.EquipmentGenerator;
import org.example.generators.help.equipment.EquipmentLoader;
import org.example.generators.help.equipment.MaterialLoader;
import org.example.ui.help.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow extends JFrame {

    JRadioButton armorRadioButton;
    JRadioButton weaponRadioButton;
    JRadioButton armorWeaponRadioButton;
    EquipmentGenerator equipmentGenerator;

    ExcludeCheckBoxPanel armorCheckBoxesPanel;
    ExcludeCheckBoxPanel weaponCheckBoxesPanel;

    RarityCheckBoxJPanel rarityCheckBoxJPanel;

    public StartWindow() throws HeadlessException {

        this.equipmentGenerator = new EquipmentGenerator(new MaterialLoader(), new EquipmentLoader());
        armorRadioButton = new JRadioButton("Броня", true);
        weaponRadioButton = new JRadioButton("Оружие", false);
        armorWeaponRadioButton = new JRadioButton("Броня/Оружие", false);

        armorCheckBoxesPanel = new ExcludeArmorCheckBoxPanel(equipmentGenerator.getEquipmentLoader().rawArmorList);
        weaponCheckBoxesPanel = new ExcludeWeaponCheckBoxPanel(equipmentGenerator.getEquipmentLoader().rawWeaponList);

        rarityCheckBoxJPanel = new RarityCheckBoxJPanel();

        this.setName("Main");
        this.setSize(400, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void buildUI() {

        //RadioPanel to Frame
        add(new ArmorWeaponRadioButtonPanel(armorCheckBoxesPanel, weaponCheckBoxesPanel), BorderLayout.NORTH);

        //Armor CheckBoxes
        add(armorCheckBoxesPanel, BorderLayout.WEST);
        //Equipment JCheckBoxes
        add(weaponCheckBoxesPanel, BorderLayout.EAST);

        add(rarityCheckBoxJPanel, BorderLayout.SOUTH);

        JButton centerButton = new JButton();
        centerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog jDialog = new JDialog();
                jDialog.setSize(300,300);
                jDialog.add(new Label(rarityCheckBoxJPanel.getSelectedCheckBoxString()));
                jDialog.setVisible(true);
            }
        });
        add(centerButton, BorderLayout.CENTER);

        pack();

        this.setVisible(true);
    }
}
