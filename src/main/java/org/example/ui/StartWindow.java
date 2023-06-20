package org.example.ui;

import org.example.generators.ReadyEquipmentGenerator;
import org.example.generators.help.equipment.loaders.EquipmentLoader;
import org.example.generators.help.equipment.loaders.MaterialLoader;
import org.example.generators.help.equipment.types.*;
import org.example.ui.help.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class StartWindow extends JFrame {

    ReadyEquipmentGenerator readyEquipmentGenerator;

    ArmorWeaponRadioButtonPanel armorWeaponRadioButtonPanel;
    EquipmentCheckBoxPanel<Armor> armorCheckBoxesPanel;
    EquipmentCheckBoxPanel<Weapon> weaponCheckBoxesPanel;

    RarityCheckBoxJPanel rarityCheckBoxJPanel;

    public StartWindow() throws HeadlessException {

        this.readyEquipmentGenerator = new ReadyEquipmentGenerator(new MaterialLoader(), new EquipmentLoader());

        this.armorCheckBoxesPanel = new EquipmentCheckBoxPanel<>(readyEquipmentGenerator.getEquipmentLoader().rawArmorList);
        this.weaponCheckBoxesPanel = new EquipmentCheckBoxPanel<>(readyEquipmentGenerator.getEquipmentLoader().rawWeaponList);

        this.armorWeaponRadioButtonPanel = new ArmorWeaponRadioButtonPanel(armorCheckBoxesPanel, weaponCheckBoxesPanel);
        this.rarityCheckBoxJPanel = new RarityCheckBoxJPanel();

        this.setName("Main");
        //this.setSize(400, 800);
        this.setBounds(300, 100, 800, 500);
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
            Armor [] armorCheckBoxes = armorCheckBoxesPanel.getSelectedEquipment(new Armor[0]);
            Weapon [] weaponsCheckBoxes = weaponCheckBoxesPanel.getSelectedEquipment(new Weapon[0]);
            Rarity [] rarities = rarityCheckBoxJPanel.getRarities();

            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Result1.txt"));

                if(armorCheckBoxes.length!=0){
                    ReadyArmor [] readyArmors = readyEquipmentGenerator.makeReadyArmorsSeveralRarity(armorCheckBoxes, rarities, 10);
                    for (int i = 0; i<readyArmors.length; i++) {
                        bufferedWriter.write(i + ". ");
                        bufferedWriter.write(readyArmors[i].toString()+"\n");
                        bufferedWriter.flush();
                    }
                }
                if (weaponsCheckBoxes.length!=0){
                    ReadyWeapon [] readyWeapons = readyEquipmentGenerator.makeReadyWeaponsSeveralRarity(weaponsCheckBoxes,rarities,8);
                    for (int i = 0; i<readyWeapons.length; i++) {
                        bufferedWriter.write(i + ". ");
                        bufferedWriter.write(readyWeapons[i].toString()+"\n");
                        bufferedWriter.flush();
                    }
                }
                bufferedWriter.close();

            } catch (Exception p){
                p.printStackTrace();
            }












        }
    }
}
