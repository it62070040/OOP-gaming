/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author LAB205_49
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 *
 * @author LAB205_49
 */
public class MyClock extends JLabel implements Runnable {
    
    public static boolean status = true;
    public static String clock;
    public static int sec;
    @Override
    public void run(){
        sec = 0;
        while (status){
            try {
                sec++;
                setFont(new Font("Courier", Font.BOLD, 200));
                clock = String.format("%02d",(sec/3600)%24)+" : "+String.format("%02d",(sec/60)%60)+" : "+String.format("%02d",sec%60);
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }
    
}