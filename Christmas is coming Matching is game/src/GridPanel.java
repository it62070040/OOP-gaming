/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author O
 */
public class GridPanel extends JPanel{
    int numButtons;
    
    static String pics[] = {"a1.png", "a2.png", "a3.jpg", "a4.jpg",
                     "a5.jpg", "a6.jpg", "a7.jpg", "a8.jpg"};
    static JButton[] buttons;
    ImageIcon cardBack = new ImageIcon(this.getClass().getResource("player.png"));
    ImageIcon[] icons;
    private ImageIcon temp;
    static int score = 0;
    private boolean gameOver;
    Timer myTimer;
    int openImages;
    public int currentIndex;
    public int oddClickIndex;
    public int numClicks;
    
    public GridPanel(){
        setBorder(new EmptyBorder(0,0,0,0));
        setLayout(new GridLayout(0,4,0,0));
        setBackground(Color.WHITE);
        setVisible(true);
        addButtons();
        
    }
    private void addButtons(){
        numButtons = pics.length *2;
        buttons = new JButton[numButtons];
        icons = new ImageIcon[numButtons];
        for(int i = 0, j = 0; i<pics.length;i++){
            icons[j] = new ImageIcon(this.getClass().getResource(pics[i]));
            buttons[j] = new JButton("");
            buttons[j].addActionListener(new GridPanel.ImageButtonListener());
            buttons[j].setIcon(cardBack);
            buttons[j].setBackground(Color.WHITE);
            add(buttons[j++]);
            
            icons[j] = icons[j - 1];
            buttons[j] = new JButton("");
            buttons[j].setIcon(cardBack);
            buttons[j].setBackground(Color.WHITE);
            buttons[j].addActionListener(new GridPanel.ImageButtonListener());
            add(buttons[j++]);
        }
        Random rnd = new Random();
        for(int i=0;i<numButtons;i++){
            int j = rnd.nextInt(numButtons);
            temp = icons[i];
            icons[i] = icons[j];
            icons[j] = temp;
        }
        myTimer = new Timer(1000, new TimerListener());
    }
    private int makeButtons(int j){
        buttons[j] = new JButton("");
        buttons[j].addActionListener(new GridPanel.ImageButtonListener());
        buttons[j].setIcon(cardBack);
        buttons[j].setBackground(Color.WHITE);
        add(buttons[j++]);
        return j;
    }
    private class TimerListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            buttons[currentIndex].setIcon(cardBack);
            buttons[oddClickIndex].setIcon(cardBack);
            myTimer.stop();
        }
    }
    private class ImageButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(myTimer.isRunning())
                return;
            openImages++;
            System.out.println(openImages);
            
            for(int i = 0;i<numButtons;i++){
                if(e.getSource().equals(buttons[i])){
                    buttons[i].setIcon(icons[i]);
                    currentIndex = i;
                }
            }
            if(openImages % 2 == 0){
                if(currentIndex == oddClickIndex){
                    numClicks--;
                    return;
                }
                if(icons[currentIndex] != icons[oddClickIndex]){
                    myTimer.start();
                }else{
                    score++;
                    if(score == 8){
                        setGameOver(true);
                        setVisible(false);
                    }
                }
            }else{
                oddClickIndex = currentIndex;
            }
        }
    }
    public static void setScore(int score){
        GridPanel.score = score;
    }
    public int getScore(){
        return score;
    }
    public boolean isGameOver(){
        return gameOver;
    }
    public void setGameOver(boolean gameOver){
        this.gameOver = gameOver;
    }
    
    public static void main(String[] args) {
        new GridPanel();
    }
}
