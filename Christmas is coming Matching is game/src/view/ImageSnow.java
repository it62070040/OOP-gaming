/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author film
 */
public class ImageSnow implements Runnable{
    private BufferedImage ImageSnow;
    private int x;
    private int y=-50;
    private Thread t;
    private Start p;
    private Save s;
    private SaveCareer sc;
    private SaveVehicle sv;
    private MenuGame m;
    
    
    public ImageSnow(BufferedImage ImageSnow, int x, Start p) {
        this.ImageSnow = ImageSnow;
        this.x = x;
        this.p = p;
        t = new Thread(this);
        t.start();
    }
    public ImageSnow(BufferedImage ImageSnow, int x, Save s) {
        this.ImageSnow = ImageSnow;
        this.x = x;
        this.s = s;
        t = new Thread(this);
        t.start();
    }
    public ImageSnow(BufferedImage ImageSnow, int x, MenuGame m) {
        this.ImageSnow = ImageSnow;
        this.x = x;
        this.m = m;
        t = new Thread(this);
        t.start();
    }
    public ImageSnow(BufferedImage ImageSnow, int x, SaveCareer sc) {
        this.ImageSnow = ImageSnow;
        this.x = x;
        this.sc = sc;
        t = new Thread(this);
        t.start();
    }

    public ImageSnow(BufferedImage ImageSnow, int x, SaveVehicle sv) {
        this.ImageSnow = ImageSnow;
        this.x = x;
        this.sv = sv;
        t = new Thread(this);
        t.start();
    }



    @Override
    public void run() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        while(true) {
            try {
                y += 10;
                Thread.sleep(10);
            } catch (InterruptedException ex) {
//                Logger.getLogger(ImageSnow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
     public void paint(Graphics2D g2) {
//        System.out.println("draw");
        g2.drawImage(ImageSnow, x, y, 20, 20, null);
        if (p != null) {
            p.repaint();
        }
        if (s != null) {
           s.repaint();
        }
        if (m != null){
            m.repaint();
        }
        if (sc != null){
            sc.repaint();
        }
        if (sv != null){
            sv.repaint();
        }
    
}
}
