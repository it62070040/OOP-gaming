/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.imageio.ImageIO;

import controller.VehicleController;
import controller.Game;
import controller.CareerController;

import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 *
 * @author film
 */
public class MenuGame extends JPanel implements MouseListener, MouseMotionListener, Runnable {

    private JFrame fr;
    private JLabel pic1, gameone;

//     ImageIcon bn;
    ImageIcon box = new ImageIcon(getClass().getResource("/img/map/map1.png"));

    File bg = new File("src/img/map/map1.png");
    private BufferedImage imgbg;

    File animal = new File("src/img/map/animal_sign1.png");
    private BufferedImage animalBut;
    File animal2 = new File("src/img/map/animal_sign2.png");
    private BufferedImage animalBut2;

    File career = new File("src/img/map/career_sign1.png");
    private BufferedImage careerBut;
    File career2 = new File("src/img/map/career_sign2.png");
    private BufferedImage careerBut2;

    File vehicle = new File("src/img/map/vehicle_sign1.png");
    private BufferedImage vehicleBut;
    File vehicle2 = new File("src/img/map/vehicle_sign2.png");
    private BufferedImage vehicleBut2;
    
    int random;
    int randomSnow;

    File snowOne = new File("src/img/snow1.png");
    private BufferedImage snowbuf;
    File snowTwo = new File("src/img/snow2.png");
    private BufferedImage snowbuf2;
    File snowThree = new File("src/img/snow3.png");
    private BufferedImage snowbuf3;
    BufferedImage snow[];

    private ArrayList<ImageSnow> keepSnow = new ArrayList<ImageSnow>();
    private Thread t;

    public MenuGame() {
        fr = new JFrame("Menu");
        pic1 = new JLabel(box);
        
        snow = new BufferedImage[3];
       
        t = new Thread(this);
        t.start();
//        Graphics2D g2d = (Graphics2D) g;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        try {
            imgbg = ImageIO.read(bg);
            animalBut = ImageIO.read(animal);
            animalBut2 = ImageIO.read(animal2);

            careerBut = ImageIO.read(career);
            careerBut2 = ImageIO.read(career2);

            vehicleBut = ImageIO.read(vehicle);
            vehicleBut2 = ImageIO.read(vehicle2);
            
            snowbuf = ImageIO.read(snowOne);
            snowbuf2 = ImageIO.read(snowTwo);
            snowbuf3 = ImageIO.read(snowThree);
            snow[0] = snowbuf;
            snow[1] = snowbuf2;
            snow[2] = snowbuf3;
        } catch (IOException ex) {
        }

        fr.add(this);
        fr.setIconImage(Toolkit.getDefaultToolkit().getImage(GameFrame.class.getResource("/img/santa.png")));

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
        fr.setResizable(false);
        fr.setSize(1400, 1000);

    }
    
    public void addSnow() {
        random = (int) (Math.random() * (1400 - 200));
        randomSnow = (int) (Math.random() * snow.length);
        keepSnow.add(new ImageSnow(snow[randomSnow], random, this));
//         System.out.println(snow[randomSnow]);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2 = (Graphics2D) g;
//        System.out.println("yes");
        g2.drawImage(imgbg, 0, 0, 1400, 980, null);
        for (int i = 0; i < keepSnow.size(); i++) {
            keepSnow.get(i).paint(g2);
        }
        g2.drawImage(animalBut, 330, 300, 116, 142, null);
        g2.drawImage(careerBut, 1150, 220, 116, 142, null);
        g2.drawImage(vehicleBut, 880, 440, 116, 142, null);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        Rectangle mouseBounds = new Rectangle(e.getX(), e.getY(), 1, 1);
        if (mouseBounds.intersects(new Rectangle(330, 300, 116, 142))) {
            GameFrame g1 = new GameFrame();
            g1.setVisible(true);
            fr.dispose();
        }
        
        else if (mouseBounds.intersects(new Rectangle(1150, 220, 116, 142))) {
            Career g2 = new Career();
            g2.setVisible(true);
            fr.dispose();
        }
        else if (mouseBounds.intersects(new Rectangle(880, 440, 116, 142))) {
            Vehicle g3 = new Vehicle();
            g3.setVisible(true);
            fr.dispose();
        }


    }

    @Override
    public void mouseDragged(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Rectangle mouseBounds = new Rectangle(e.getX(), e.getY(), 1, 1);
        if (mouseBounds.intersects(new Rectangle(330, 300, 116, 142))) {

            try {
                animalBut = ImageIO.read(animal2);
            } catch (IOException ex) {
            }
        } else {
            try {
                animalBut = ImageIO.read(animal);
            } catch (IOException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (mouseBounds.intersects(new Rectangle(1150, 220, 116, 142))) {
          

            try {
                careerBut = ImageIO.read(career2);
            } catch (IOException ex) {
            }
        } else {
            try {
                careerBut = ImageIO.read(career);
            } catch (IOException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (mouseBounds.intersects(new Rectangle(880, 440, 116, 142))) {
            

            try {
                vehicleBut = ImageIO.read(vehicle2);
            } catch (IOException ex) {
            }
        } else {
            try {
                vehicleBut = ImageIO.read(vehicle);
            } catch (IOException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
       

        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void run() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        while (true) {
            try {
                addSnow();
                Thread.sleep(100);
            } catch (InterruptedException ex) {
//                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    

}
