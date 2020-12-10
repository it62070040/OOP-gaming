/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.*;
import java.awt.event.*;

import java.util.Random;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import view.GameFrame;
import view.Save;
import view.MyClock;

/**
 *
 * @author film
 */
public class Game extends JPanel {

    int numButtons;
    
    static String pics[] = {"/img/animal/camel1.png", "/img/animal/cricket1.png", "/img/animal/fox1.png", "/img/animal/monkey1.png",
        "/img/animal/parrot1.png", "/img/animal/pig1.png", "/img/animal/shark1.png", "/img/animal/walrus1.png"};
    
    static JButton[] buttons;
    ImageIcon cardBack = new ImageIcon(this.getClass().getResource("/img/quiz.png"));

    ImageIcon[] icons;
    private ImageIcon temp;
    public static int score = 0;
    public static boolean gameOver;
    Timer myTimer;
    public static int openImages = 0;
    public int currentIndex;
    public int oddClickIndex;
    public int numClicks;
    private GameFrame f;
    private Save s;

    public Game() {
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setLayout(new GridLayout(0, 4, 0, 0));
        setBackground(Color.WHITE);
        setVisible(true);
        addButtons();

    }

    private void addButtons() {
        numButtons = pics.length * 2;
        buttons = new JButton[numButtons];
        icons = new ImageIcon[numButtons];
        for (int i = 0, j = 0; i < pics.length; i++) {
            try {
                icons[j] = new ImageIcon(this.getClass().getResource(pics[i]));
            } catch (Exception ex) {
                System.out.println(i);
            }
            buttons[j] = new JButton("");
            buttons[j].addActionListener(new Game.ImageButtonListener());
            buttons[j].setIcon(cardBack);
            buttons[j].setBackground(Color.WHITE);
            add(buttons[j++]);

            icons[j] = icons[j - 1];
            buttons[j] = new JButton("");
            buttons[j].setIcon(cardBack);
            buttons[j].setBackground(Color.WHITE);
            buttons[j].addActionListener(new Game.ImageButtonListener());
            add(buttons[j++]);
        }
        Random rnd = new Random();
        for (int i = 0; i < numButtons; i++) {
            int j = rnd.nextInt(numButtons);
            temp = icons[i];
            icons[i] = icons[j];
            icons[j] = temp;
        }
        myTimer = new Timer(1000, new Game.TimerListener());
    }


    private class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            buttons[currentIndex].setIcon(cardBack);
            buttons[oddClickIndex].setIcon(cardBack);
            myTimer.stop();
        }
    }

    private class ImageButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (myTimer.isRunning()) {
                return;
            }
            openImages++;
            System.out.println(openImages);

            for (int i = 0; i < numButtons; i++) {
                if (e.getSource().equals(buttons[i])) {
                    buttons[i].setIcon(icons[i]);
                    currentIndex = i;
                }
            }
            if (openImages % 2 == 0) {
                if (currentIndex == oddClickIndex) {
                   
                    return;
                }
                if (icons[currentIndex] != icons[oddClickIndex]) {
                    myTimer.start();
                } else {
                    score++;
                    if (score == 8) {
                        setGameOver(true);
                        MyClock.status = false;
                        setVisible(false);
                        new Save();
                    }
                }
            } else {
                oddClickIndex = currentIndex;
            }
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
