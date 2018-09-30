package ru.zlygostev;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Multithreading extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextArea textArea1;
    private MultithreadingExperiments experiment;

    public Multithreading() {
        experiment = new MultithreadingExperiments();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.append(experiment.start() + "\n\n");
            }
        });
    }

    public static void main(String[] args) {
        final Multithreading dialog = new Multithreading();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
