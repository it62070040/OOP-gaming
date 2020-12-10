/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

//import java.awt.*;
//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//import java.util.logging.*;
import javax.swing.*;
import javax.imageio.ImageIO;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
//import sun.audio.AudioData;
//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;
//import sun.audio.ContinuousAudioDataStream;
//import controller.player;
import java.awt.event.*;
import java.util.ArrayList;

/**
 *
 * @author film
 */
public class Start extends JPanel implements MouseListener, MouseMotionListener, Runnable {

    private JFrame fr;
    AudioInputStream inputStream;
    Clip clip;

    ImageIcon box = new ImageIcon(getClass().getResource("/img/background2.png"));

    File LabelStart = new File("src/img/start.png");
    private BufferedImage img;

    File LabelStart2 = new File("src/img/start2.png");
    private BufferedImage img2;

    File bg = new File("src/img/background2.png");
    private BufferedImage imgbg;

    File nameGame = new File("src/img/title2.png");
    private BufferedImage nameG;

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

    public Start() {

        fr = new JFrame("Start");
        snow = new BufferedImage[3];
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        t = new Thread(this);
        t.start();

        try {
            img = ImageIO.read(LabelStart);
            imgbg = ImageIO.read(bg);
            nameG = ImageIO.read(nameGame);
            img2 = ImageIO.read(LabelStart2);

            snowbuf = ImageIO.read(snowOne);
            snowbuf2 = ImageIO.read(snowTwo);
            snowbuf3 = ImageIO.read(snowThree);
            snow[0] = snowbuf;
            snow[1] = snowbuf2;
            snow[2] = snowbuf3;
            System.out.println(snow[0]);
        } catch (IOException ex) {
            System.out.println("io");
        }

        if (LabelStart.exists()) {

        } else {
            System.out.println(LabelStart.getAbsoluteFile());
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
        // (สุ่มรูปหิมะ, สุ่มตำแหน่ง x (ซ้าย, ขวา), ให้แสดงหน้าจอนี้)
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(imgbg, 0, 0, 1400, 980, null);
        for (int i = 0; i < keepSnow.size(); i++) {
            keepSnow.get(i).paint(g2);
        }
        g2.drawImage(img, 500, 600, 375, 122, null);
        g2.drawImage(nameG, 200, 250, 993, 260, null);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("ingame");
        Rectangle mouseBounds = new Rectangle(e.getX(), e.getY(), 1, 1);
        if (mouseBounds.intersects(new Rectangle(500, 600, 375, 122))) {
            new MenuGame();
            fr.dispose();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        Rectangle mouseBounds = new Rectangle(e.getX(), e.getY(), 1, 1);
        if (mouseBounds.intersects(new Rectangle(500, 600, 375, 122))) {
            try {
                img = ImageIO.read(LabelStart2);
            } catch (IOException ex) {
            }
        } else {
            try {
                img = ImageIO.read(LabelStart);
            } catch (IOException ex) {
            }
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void playSongs() {
        try {
            inputStream = AudioSystem.getAudioInputStream(getClass().getResource("/song/jing.mid"));
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException e) {
        } catch (IOException e) {
        } catch (LineUnavailableException e) {
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                addSnow();
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
        }
    }

    public static void main(String[] args) {
        new Start().playSongs();
    }
}
