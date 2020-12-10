/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author film
 */
public class createFile extends JFrame implements WindowListener {

    private JLabel save;

    public createFile() {
        save = new JLabel("100");
        this.setLayout(new FlowLayout());
        add(save);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);
        this.addWindowListener(this);
        
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        try (FileOutputStream fOut = new FileOutputStream("dataBot.dat");
                ObjectOutputStream oout = new ObjectOutputStream(fOut);) {
            oout.writeObject(save.getText());
            System.out.println("Serialized data is saved");
        } catch (IOException i) {
            i.printStackTrace();
        }
        try (FileOutputStream fOut = new FileOutputStream("dataBotCareer.dat");
                ObjectOutputStream oout = new ObjectOutputStream(fOut);) {
            oout.writeObject(save.getText());
            System.out.println("Serialized data is saved");
        } catch (IOException i) {
            i.printStackTrace();
        }
        try (FileOutputStream fOut = new FileOutputStream("dataBotVehicle.dat");
                ObjectOutputStream oout = new ObjectOutputStream(fOut);) {
            oout.writeObject(save.getText());
            System.out.println("Serialized data is saved");
        } catch (IOException i) {
            i.printStackTrace();
        }
        }
    

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
    public static void main(String[] args) {
        new  createFile();
    }
}
