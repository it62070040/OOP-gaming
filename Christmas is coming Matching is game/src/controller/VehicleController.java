/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.smartcardio.Card;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JApplet;
import view.GameFrame;
import view.SaveVehicle;
import view.MyClock;



/**
 *
 * @author film
 */
public class VehicleController extends JPanel {

    int numButtons;

    static String pics[] = {"/img/vehicle/balloon1.png", "/img/vehicle/bicycle1.png", "/img/vehicle/bus1.png", "/img/vehicle/car1.png",
                     "/img/vehicle/motorbike1.png", "/img/vehicle/ship1.png", "/img/vehicle/submarine1.png", "/img/vehicle/taxi.png", "/img/vehicle/train1.png",
                     "/img/vehicle/van (1).png"};
    static JButton[] buttons;
//    Image[] cardBack = new Image[16];
    ImageIcon cardBack = new ImageIcon(this.getClass().getResource("/img/vehicle/quiz.png"));

    ImageIcon[] icons;
    private ImageIcon temp;
    public static int score = 0;
    public static boolean gameOver;
    Timer myTimer;
    public static int openImages=0;
    public int currentIndex;
    public int oddClickIndex;
    public int numClicks;
    private GameFrame f;
    private SaveVehicle s;
    


    public VehicleController() {
//        gf = new GameFrame();
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setLayout(new GridLayout(5, 4, 0, 0));
//        Font font = new Font("Courier", Font.BOLD, 12);
//        font.deriveFont(100F);
//        setFont(font);
        setBackground(Color.WHITE);
        setVisible(true);
        addButtons();


    }

    private void addButtons() {
        numButtons = pics.length * 2;
        buttons = new JButton[numButtons];
        icons = new ImageIcon[numButtons];
        for (int i = 0, j = 0; i < pics.length; i++) {
//            icons[j] = new ImageIcon(this.getClass().getResource(pics[i]));
            try {
                icons[j] = new ImageIcon(this.getClass().getResource(pics[i]));
            } catch (Exception ex) {
                System.out.println(i);
            }
            buttons[j] = new JButton("");
            buttons[j].addActionListener(new VehicleController.ImageButtonListener());
            buttons[j].setIcon(cardBack);
            buttons[j].setBackground(Color.WHITE);
            add(buttons[j++]);

            icons[j] = icons[j - 1];
            buttons[j] = new JButton("");
            buttons[j].setIcon(cardBack);
            buttons[j].setBackground(Color.WHITE);
            buttons[j].addActionListener(new VehicleController.ImageButtonListener());
            add(buttons[j++]);
        }
        Random rnd = new Random();
        for (int i = 0; i < numButtons; i++) {
            int j = rnd.nextInt(numButtons);
            temp = icons[i];
            icons[i] = icons[j];
            icons[j] = temp;
        }
        myTimer = new Timer(1000, new VehicleController.TimerListener());
    }

    private int makeButtons(int j) {
        buttons[j] = new JButton("");
        buttons[j].addActionListener(new VehicleController.ImageButtonListener());
        buttons[j].setIcon(cardBack);
        buttons[j].setBackground(Color.WHITE);
        add(buttons[j++]);
        return j;
    }

    private class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            buttons[currentIndex].setIcon(cardBack);
            buttons[oddClickIndex].setIcon(cardBack);
            myTimer.stop();
        }
    }

    private class ImageButtonListener implements ActionListener {
        private GameFrame gf;
        @Override
        public void actionPerformed(ActionEvent e) {
            if (myTimer.isRunning()) {
                return;
            }
            openImages++;
//            ff.getMove().setText(openImages + "");
            System.out.println(openImages);
            

            for (int i = 0; i < numButtons; i++) {
                if (e.getSource().equals(buttons[i])) {
                    buttons[i].setIcon(icons[i]);
                    currentIndex = i;
                }
            }
            if (openImages % 2 == 0) {
                if (currentIndex == oddClickIndex) {
                    numClicks--;
                    return;
                }
                if (icons[currentIndex] != icons[oddClickIndex]) {
                    myTimer.start();
                } else {
                    score++;
                    if (score == 10) {
                        setGameOver(true);
                        MyClock.status = false;
                        setVisible(false);
                        new SaveVehicle();
                        
                    }
                }
            } else {
                oddClickIndex = currentIndex;
            }
//            ff.getMove().setText(openImages + "");
        }
    }

    public static void setScore(int score) {
        Game.score = score;
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

//    public static void main(String[] args) {
//        new Game();
//    }
    public int getNumButtons() {
        return numButtons;
    }

    public void setNumButtons(int numButtons) {
        this.numButtons = numButtons;
    }

    public static String[] getPics() {
        return pics;
    }

    public static JButton[] getButtons() {
        return buttons;
    }

    public static void setButtons(JButton[] buttons) {
        Game.buttons = buttons;
    }

    public ImageIcon[] getIcons() {
        return icons;
    }

    public void setIcons(ImageIcon[] icons) {
        this.icons = icons;
    }

    public ImageIcon getTemp() {
        return temp;
    }

    public void setTemp(ImageIcon temp) {
        this.temp = temp;
    }

    public Timer getMyTimer() {
        return myTimer;
    }

    public void setMyTimer(Timer myTimer) {
        this.myTimer = myTimer;
    }

    public int getOpenImages() {
        return openImages;
    }

    public void setOpenImages(int openImages) {
        this.openImages = openImages;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public int getOddClickIndex() {
        return oddClickIndex;
    }

    public void setOddClickIndex(int oddClickIndex) {
        this.oddClickIndex = oddClickIndex;
    }

    public int getNumClicks() {
        return numClicks;
    }

    public void setNumClicks(int numClicks) {
        this.numClicks = numClicks;
    }

}
