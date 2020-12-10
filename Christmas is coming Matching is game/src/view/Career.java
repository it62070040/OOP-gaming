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
import controller.CareerController;
import controller.Game;

import java.awt.*;
import static java.awt.SystemColor.text;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.AttributedCharacterIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.text.Font.*;
import static javafx.scene.text.Font.font;
import javax.imageio.ImageIO;
import javax.smartcardio.*;
import javax.swing.*;
import view.MenuGame;

public class Career extends JPanel implements MouseListener, MouseMotionListener, WindowListener{

    private CareerController view;
    public JFrame fr;
    private Graphics2D g2d;
    private JLabel savescore;
    public int text;
    private MyClock clock;
    private Thread t;

    File high = new File("src/img/move.png");
    private BufferedImage highbut;
    File background = new File("src/img/backGame.png");
    private BufferedImage background2;
    File back = new File("src/img/back1.png");
    private BufferedImage backbut;
    File back2 = new File("src/img/back2.png");
    private BufferedImage backbut2;
    File frameTime = new File("src/img/time.png");
    private BufferedImage frameTimebut;
    File best = new File("src/img/highscore.png");
    private BufferedImage bestbut;

    public Career() {
        
        savescore = new JLabel();
        fr = new JFrame("Christmas matching game");
        fr.setBackground(Color.BLUE);
        fr.setVisible(true);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        fr.addWindowListener(this);
        fr.setSize(1400, 980);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(5, 5, 1400, 980);
        fr.setIconImage(Toolkit.getDefaultToolkit().getImage(GameFrame.class.getResource("/img/santa.png")));

        fr.setLayout(new BorderLayout());
        view = new CareerController();
        clock = new MyClock();
        t = new Thread(clock);
        t.start();

        fr.add(view, BorderLayout.WEST);
//        fr.add(clock, BorderLayout.SOUTH);

//        fr.add(move, BorderLayout.EAST);
        fr.add(this);
        fr.setResizable(false);
//        validate();
//        repaint();
        try {
            highbut = ImageIO.read(high);
            background2 = ImageIO.read(background);
            backbut = ImageIO.read(back);
            backbut2 = ImageIO.read(back2);
            bestbut = ImageIO.read(best);
            frameTimebut = ImageIO.read(frameTime);

        } catch (IOException ex) {
        }

    }
    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("enter");
        File f = new File("dataBotCareer.dat");
        if (f.exists()) {
            try (FileInputStream fin = new FileInputStream("dataBotCareer.dat");
                    ObjectInputStream in = new ObjectInputStream(fin);) {
                savescore.setText("" + (String) in.readObject());
            } catch (IOException i) {
                i.printStackTrace();
            } catch (ClassNotFoundException c) {
                c.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("Impact", Font.BOLD, 100));
        g2.setColor(Color.white);
        g2.drawImage(background2, 0, 0, 1400, 980, null);
        g2.drawImage(highbut, 40, 50, 350, 250, null);
        g2.drawString(CareerController.openImages + "", 170, 250);

        g2.setFont(new Font("Impact", Font.BOLD, 50));
        g2.drawImage(frameTimebut, 40, 300, 350, 250, null);
        g2.drawString(MyClock.clock + "", 100, 500);

        g2.drawImage(bestbut, 40, 550, 350, 250, null);
        g2.drawString(savescore.getText(), 170, 750);

        g2.drawImage(backbut, 120, 800, 180, 70, null);
        if (MyClock.status == false) {
            System.out.println("Close");
            fr.dispose();
        }

        repaint();
        if (MyClock.status == false && CareerController.openImages < Integer.parseInt(savescore.getText())) {
            try (FileOutputStream fOut = new FileOutputStream("dataBotCareer.dat");
                    ObjectOutputStream oout = new ObjectOutputStream(fOut);) {
                oout.writeObject(CareerController.openImages + "");
                System.out.println("Serialized data is saved");
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
//        g2.drawString((AttributedCharacterIterator) move, 0, 1);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        Rectangle mouseBounds = new Rectangle(e.getX(), e.getY(), 1, 1);
        if (mouseBounds.intersects(new Rectangle(120, 850, 180, 70))) {
            t.stop();
            MyClock.sec = 0;
            new MenuGame();
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
        if (mouseBounds.intersects(new Rectangle(120, 850, 180, 70))) {
            try {
                backbut = ImageIO.read(back2);
            } catch (IOException ex) {
            }

        } else {
            try {
                backbut = ImageIO.read(back);
            } catch (IOException ex) {
//                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
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
    public void windowClosing(WindowEvent e) {
        
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

}
