package org.example.ui.help;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RarityCheckBoxJPanel extends JPanel { //Очень херово сделан класс

    boolean[] selectedCheckBox = new boolean[6];
    JCheckBox common;

    JCheckBox rare;

    JCheckBox veryRare;

    JCheckBox epic;

    JCheckBox master;

    JCheckBox legendary;

    public RarityCheckBoxJPanel() {

        setLayout(new FlowLayout());
        Label rarityLabel = new Label("Выбрать редкость: ");
        add(rarityLabel);
        RarityListener rarityListener = new RarityListener();

        common = new JCheckBox();
        common.addItemListener(rarityListener);

        rare = new JCheckBox();
        rare.setBackground(Color.GREEN);
        rare.addItemListener(rarityListener);

        veryRare = new JCheckBox();
        veryRare.setBackground(new Color(107, 207, 250));
        veryRare.addItemListener(rarityListener);

        epic = new JCheckBox();
        epic.setBackground(new Color(208, 130, 245));
        epic.addItemListener(rarityListener);

        master = new JCheckBox();
        master.setBackground(new Color(215, 245, 132));
        master.addItemListener(rarityListener);

        legendary = new JCheckBox();
        legendary.setBackground(Color.CYAN);
        legendary.addItemListener(rarityListener);

        add(common);
        add(rare);
        add(veryRare);
        add(epic);
        add(master);
        add(legendary);

    }

    public boolean[] getSelectedCheckBox() {
        return selectedCheckBox;
    }

    public String getSelectedCheckBoxString (){
        StringBuilder stringBuilder = new StringBuilder();
        for (boolean b :
                selectedCheckBox) {
            stringBuilder.append(b).append("\n");
        }
        return stringBuilder.toString();
    }

    class RarityListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            int i;
            JCheckBox checkbox = (JCheckBox) e.getItem();
            if (checkbox == common) {
                i = 0;
            } else if (checkbox == rare) {
                i = 1;
            } else if (checkbox == veryRare) {
                i = 2;
            } else if (checkbox == epic) {
                i = 3;
            } else if (checkbox == master) {
                i = 4;
            } else if (checkbox == legendary) {
                i = 5;
            } else {
                throw new IllegalArgumentException();
            }

            selectedCheckBox[i] = !selectedCheckBox[i];
        }
    }
}
