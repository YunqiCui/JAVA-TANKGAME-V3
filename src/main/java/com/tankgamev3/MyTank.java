package com.tankgamev3;/*
 * Class MyGraph to draw Tank
 * @author Yunqi Cui
 * 27/02/2018
 */


import com.tankgamev3.entity.Bullet;
import com.tankgamev3.entity.Bomb;
import com.tankgamev3.model.EnemyTank;
import com.tankgamev3.model.PlayerTank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyTank extends JPanel implements KeyListener, Runnable {

    private PlayerTank pt;
    private Vector<EnemyTank> etv = new Vector<EnemyTank>();
    private Vector<Bomb> bbv = new Vector<Bomb>();
    int enemySize = 3;
    public int x = 0;
    public int y = 0;
    Image im1,im2,im3;


    public MyTank() {
        pt = new PlayerTank(100, 100);
        pt.isLive = true;

        for (int i = 0; i < enemySize; i++) {
            EnemyTank et = new EnemyTank((i + 1) * 50, 0);
            et.isLive = true;
            et.setType(0);
            Thread t = new Thread(et);
            t.start();
            Bullet eb = new Bullet(et.x,et.y,et.direct);
            et.ebv.add(eb);
            Thread t2 = new Thread(eb);
            t2.start();
            etv.add(et);
        }

        x = pt.getX();
        y = pt.getY();

        //Define 3 images, combine them to make a bomb.
        im1 = Toolkit.getDefaultToolkit().getImage("/Users/cuiyunqi/Desktop/HSP/TANKGAMEV3/src/main/java/com/tankgamev3/images/bomb_1.gif");
        im2 = Toolkit.getDefaultToolkit().getImage("/Users/cuiyunqi/Desktop/HSP/TANKGAMEV3/src/main/java/com/tankgamev3/images/bomb_2.gif");
        im3 = Toolkit.getDefaultToolkit().getImage("/Users/cuiyunqi/Desktop/HSP/TANKGAMEV3/src/main/java/com/tankgamev3/images/bomb_3.gif");
    }

    public void paint(Graphics g) {

        super.paint(g);
        this.drawGameField(g);
        //draw Player Tank
        this.drawTank(pt.getX(), pt.getY(), g, pt.getDirect(), pt.getType());


        //traverse every bullet from the vector
        for (int i = 0; i < this.pt.bv.size(); i++) {
            Bullet mb = this.pt.bv.get(i);

            //Draw Player Bullet
            if (mb != null && mb.isAlive) {
                g.draw3DRect(mb.x, mb.y, 1, 1, false);
            } else if (!mb.isAlive) {
                pt.bv.remove(mb);
            }

        }

        //draw Enemy Tank
        for (int i = 0; i < enemySize; i++) {
            EnemyTank et = etv.get(i);
            if(et.isLive){
                this.drawTank(et.getX(), et.getY(), g, et.getDirect(), et.getType());
                for (int j = 0; j < et.bv.size(); j++) {
                    Bullet eb = et.bv.get(j);

                    //Draw Enemy Bullet
                    if (eb != null && eb.isAlive) {
                        g.draw3DRect(eb.x, eb.y, 1, 1, false);
                    } else if (!eb.isAlive) {
                        et.bv.remove(eb);
                    }
                }

            }
        }

        //draw Bomb

        for (int i = 0; i <bbv.size() ; i++) {
            Bomb bomb = bbv.get(i);

            //draw three different gif
            if(bomb.life > 6){
                g.drawImage(im3,bomb.x, bomb.y,30,30,this);
            }else if (bomb.life > 3){
                g.drawImage(im2,bomb.x, bomb.y,30,30,this);
            }else{
                g.drawImage(im1,bomb.x, bomb.y,30,30,this);
            }
            bomb.lifeDecrease();

            if(bomb.life <=0){
                bbv.remove(bomb);
            }
        }
    }

    public void drawGameField(Graphics g) {
        g.fillRect(0, 0, 400, 300);
    }

    //Draw Tank Function
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        //Tpye of Tank
        switch (type) {
            case 0:
                g.setColor(Color.cyan);
                break;

            case 1:
                g.setColor(Color.yellow);
                break;
        }
        switch (direct) {
            case 0:
                //Left Rectangular
                g.fill3DRect(x, y, 5, 30, false);
                //Middle Rectangular
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                //Right Rectangular
                g.fill3DRect(x + 15, y, 5, 30, false);
                //Circle
                g.fillOval(x + 7, y + 10, 6, 10);
                //UpLine
                g.drawLine(x + 10, y + 15, x + 10, y);
                break;

            case 1:
                //Up Rectangular
                g.fill3DRect(x, y, 30, 5, false);
                //Middle Rectangular
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                //Down Rectangular
                g.fill3DRect(x, y + 15, 30, 5, false);
                //Circle
                g.fillOval(x + 10, y + 7, 10, 6);
                //RightLine
                g.drawLine(x + 15, y + 10, x + 30, y + 10);
                break;

            case 2:
                //Left Rectangular
                g.fill3DRect(x, y, 5, 30, false);
                //Middle Rectangular
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                //Right Rectangular
                g.fill3DRect(x + 15, y, 5, 30, false);
                //Circle
                g.fillOval(x + 7, y + 10, 6, 10);
                //DownLine
                g.drawLine(x + 10, y + 15, x + 10, y + 30);
                break;

            case 3:
                //Up Rectangular
                g.fill3DRect(x, y, 30, 5, false);
                //Middle Rectangular
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                //Down Rectangular
                g.fill3DRect(x, y + 15, 30, 5, false);
                //Circle
                g.fillOval(x + 10, y + 7, 10, 6);
                //LeftLine
                g.drawLine(x + 15, y + 10, x, y + 10);
                break;
        }
    }


    public void keyTyped(KeyEvent e) {

    }

    //Move Tank by Keyboard
    public void keyPressed(KeyEvent e) {


        if (e.getKeyCode() == KeyEvent.VK_UP
                ) {

            pt.setDirect(0);
            pt.moveUp();

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

            pt.setDirect(1);
            pt.moveRight();

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

            pt.setDirect(2);
            pt.moveDown();

        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

            pt.setDirect(3);
            pt.moveLeft();

        } else {
            System.out.println("Not a direction key");
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {

            if (pt.bv.size() <= 4) {
                pt.shot();
            }

        }
        this.repaint();
    }

    public void keyReleased(KeyEvent e) {

    }

    public void hitTank(Bullet b, EnemyTank et) {

        switch (et.direct) {

            //Eenemy Tank is facing up or down
            case 0:
            case 2:
                if (b.x > et.x && b.x < et.x + 20 && b.y > et.y && b.y < et.y + 30) {
                    //hit
                    b.isAlive = false;
                    et.isLive = false;
                    Bomb bomb = new Bomb(et.x,et.y);
                    bbv.add(bomb);

                }

            case 1:
            case 3:
                if (b.x > et.x && b.x < et.x + 30 && b.y > et.y && b.y < et.y + 20) {
                    //hit
                    b.isAlive = false;
                    et.isLive = false;
                    Bomb bomb = new Bomb(et.x,et.y);
                    bbv.add(bomb);
                }


        }

    }

    public void run() {

        //Every 100ms repaint the Bullet
        while (true) {
            try {
                Thread.sleep(30);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //判断是否击中坦克，判断那一个坦克，击中哪一个坦克
            for (int i = 0; i < this.pt.bv.size(); i++) {

                Bullet b = pt.bv.get(i);
                //
                if (b.isAlive) {
                    for (int j = 0; j < etv.size(); j++) {
                        EnemyTank et = etv.get(j);
                        if (et.isLive) {
                            this.hitTank(b, et);
                        }
                    }
                }
            }
            this.repaint();
        }

    }
}
