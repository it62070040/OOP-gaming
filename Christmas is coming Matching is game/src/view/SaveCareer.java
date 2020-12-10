/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author film
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author O
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

import controller.CareerController;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class SaveCareer extends JPanel implements  Runnable {

    private JFrame fr;
    private JPanel p;
    private JLabel l;
    private CareerController g;
    private int score;
    File bg = new File("src/img/end.png");
    private BufferedImage imgbg;
    File high = new File("src/img/move.png");
    private BufferedImage highbut;
    
    
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

    public SaveCareer() {

     
        fr = new JFrame("END");
        g = new CareerController();
        score = CareerController.openImages;
        l = new JLabel(CareerController.openImages+"");
        p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(l);
        t = new Thread(this);
        t.start();
        
        snow = new BufferedImage[3];
        
        try {
            imgbg = ImageIO.read(bg);
            highbut = ImageIO.read(high);
            
            snowbuf = ImageIO.read(snowOne);
            snowbuf2 = ImageIO.read(snowTwo);
            snowbuf3 = ImageIO.read(snowThree);
            snow[0] = snowbuf;
            snow[1] = snowbuf2;
            snow[2] = snowbuf3;
        } catch (IOException ex) {
//            Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
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
        g2.setFont(new Font("Impact", Font.BOLD, 90));
        g2.setColor(Color.white);
        g2.drawImage(imgbg, 0, 0, 1400, 980, null);
        for (int i = 0; i < keepSnow.size(); i++) {
            keepSnow.get(i).paint(g2);
        }
//        g2.drawImage(highbut, 0, 100, 433, 241, null);
        g2.drawString(CareerController.openImages + "", 810, 700);
        g2.setFont(new Font("Impact", Font.BOLD, 30));
        g2.drawString(MyClock.clock + "", 750, 780);
    }

   
    
     @Override
    public void run() {
        while (true) {
            try {
                addSnow();
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
//                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        new SaveCareer();
    }
}
