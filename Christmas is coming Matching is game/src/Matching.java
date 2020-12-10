/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;


/**
 *
 * @author film
 */
public class Matching implements ActionListener {

    private JFrame fr;
    private JPanel one, second;
    private JLabel l, live, pic1, pic2;
    int count = 5;
    private JButton bn1, bn2, bn3, bn4, bn5, bn6, bn7, bn8, bn9, bn10, bn11, bn12, bn13, bn14, bn15, bn16;
    BufferedImage buttonIcon1;
    private JOptionPane alertInsert;
 
    ArrayList<JButton> answer = new ArrayList<JButton>();
    ImageIcon box = new ImageIcon("C:\\Users\\film\\Documents\\NetBeansProjects\\Matching\\src\\matching\\box.png");
    ImageIcon icon1 = new ImageIcon("C:\\Users\\film\\Documents\\NetBeansProjects\\Matching\\src\\matching\\wall.png");
    ImageIcon icon2 = new ImageIcon("C:\\Users\\film\\Documents\\NetBeansProjects\\Matching\\src\\matching\\beemo.jpg");

    public Matching() {
        fr = new JFrame("Matching Game");

//        bn1 = new JButton(box);
//        bn2 = new JButton(box);
//        bn3 = new JButton(box);
//        bn4 = new JButton(box);
//        bn5 = new JButton(box);
//        bn6 = new JButton(box);
//        bn7 = new JButton(box);
//        bn8 = new JButton(box);
//        bn9 = new JButton(box);
//        bn10 = new JButton(box);
//        bn11 = new JButton(box);
//        bn12 = new JButton(box);
//        bn13 = new JButton(box);
//        bn14 = new JButton(box);
//        bn15 = new JButton(box);
//        bn16 = new JButton(box);

        pic1 = new JLabel(box);

        live = new JLabel("lives:");
        l = new JLabel("5");

        // Add Listener
        pic1.addMouseListener(null);
//        bn2.addActionListener(this);
//        bn3.addActionListener(this);
//        bn4.addActionListener(this);
//        bn5.addActionListener(this);
//        bn6.addActionListener(this);
//        bn7.addActionListener(this);
//        bn8.addActionListener(this);
//        bn9.addActionListener(this);
//        bn10.addActionListener(this);
//        bn11.addActionListener(this);
//        bn12.addActionListener(this);
//        bn13.addActionListener(this);
//        bn14.addActionListener(this);
//        bn15.addActionListener(this);
//        bn16.addActionListener(this);

        second = new JPanel();
        second.setLayout(new FlowLayout());
        second.setSize(10, 640);
        second.add(live);
        second.add(l);

        one = new JPanel();
        one.setLayout(new GridLayout(4, 4));
        one.add(pic1);
//        one.add(bn2);
//        one.add(bn3);
//        one.add(bn4);
//        one.add(bn5);
//        one.add(bn6);
//        one.add(bn7);
//        one.add(bn8);
//        one.add(bn9);
//        one.add(bn10);
//        one.add(bn11);
//        one.add(bn12);
//        one.add(bn13);
//        one.add(bn14);
//        one.add(bn15);
//        one.add(bn16);

        fr.setLayout(new BorderLayout());
        fr.add(second, BorderLayout.NORTH);
        fr.add(one);

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
        fr.setSize(640, 640);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Matching();
        // TODO code application logic here
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(bn1)) {
            if (bn1.getIcon().equals(this.box)) {
                bn1.setIcon(this.icon1);
                answer.add(bn1);
                if (answer.size() == 2) {
                    if (answer.get(0).getIcon().equals(answer.get(1).getIcon())) {
                        answer.get(0).setEnabled(false);
                        answer.get(1).setEnabled(false);
                        answer.remove(0);
                        answer.remove(0);
                        count--;
                    } else {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Matching.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        answer.get(0).setIcon(box);
                        answer.get(1).setIcon(box);
                        answer.remove(0);
                        answer.remove(0);
                    }
                    count--;
                    l.setText(count + "");
                    if (count == 0) {
                        alertInsert.showMessageDialog(null, "lose it!");
                    }
                }

            }
        }
        if (e.getSource().equals(bn2)) {
            if (bn2.getIcon().equals(this.box)) {
                bn2.setIcon(this.icon2);
                answer.add(bn2);
                if (answer.size() == 2) {
                    if (answer.get(0).getIcon().equals(answer.get(1).getIcon())) { // RIGHT
                        answer.get(0).setEnabled(false);
                        answer.get(1).setEnabled(false);
                        answer.remove(0);
                        answer.remove(0);
                    } else {

                        answer.get(0).setIcon(box); // WRONG
                        answer.get(1).setIcon(box);
                        answer.remove(0);
                        answer.remove(0);
                    }
                    count--;
                    l.setText(count + "");
                    if (count == 0) {
                        alertInsert.showMessageDialog(null, "lose it!");
                    }
                }
            }
        }
        if (e.getSource().equals(bn3)) {
            if (bn3.getIcon().equals(this.box)) {
                bn3.setIcon(this.icon2);
                answer.add(bn3);
                if (answer.size() == 2) {
                    if (answer.get(0).getIcon().equals(answer.get(1).getIcon())) { // RIGHT
                        answer.get(0).setEnabled(false);
                        answer.get(1).setEnabled(false);
                        answer.remove(0);
                        answer.remove(0);
                    } else {

                        answer.get(0).setIcon(box); // WRONG
                        answer.get(1).setIcon(box);
                        answer.remove(0);
                        answer.remove(0);
                    }
                    count--;
                    l.setText(count + "");
                    if (count == 0) {
                        alertInsert.showMessageDialog(null, "lose it!");
                    }
                }
            }
        }

        if (e.getSource().equals(bn7)) {
            if (bn7.getIcon().equals(this.box)) {
                bn7.setIcon(this.icon1);
                answer.add(bn7);
                if (answer.size() == 2) {
                    if (answer.get(0).getIcon().equals(answer.get(1).getIcon())) { // RIGHT
                        answer.get(0).setEnabled(false);
                        answer.get(1).setEnabled(false);
                        answer.remove(0);
                        answer.remove(0);
                    } else {

                        answer.get(0).setIcon(box); // WRONG
                        answer.get(1).setIcon(box);
                        answer.remove(0);
                        answer.remove(0);
                    }
                    count--;
                    l.setText(count + "");
                    if (count == 0) {
                        alertInsert.showMessageDialog(null, "lose it!");
                    }
                }
            }
        }
    }

}
