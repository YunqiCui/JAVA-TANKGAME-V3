package com.tankgamev3.entity;/*
 * Class Tank
 * @author Yunqi Cui
 * 27/02/2018
 */


import java.util.Vector;

public class Tank{

    //Tank Position
    public int x;
    public int y;
    public int type;
    public int direct;
    public int speed;
    public boolean isLive;
    public Vector<Bullet> bv = new Vector<Bullet>();
    public Bullet b = null;

    public Tank(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed(){
        return speed;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public void shot(){

        switch (this.direct){
            case 0:
                b = new Bullet(x+10,y,0);
                bv.add(b);
                break;

            case 1:
                b = new Bullet(x+30,y+10,1);
                bv.add(b);
                break;

            case 2:
                b = new Bullet(x+10,y+30,2);
                bv.add(b);
                break;

            case 3:
                b = new Bullet(x,y+10,3);
                bv.add(b);
                break;
                }
        Thread t = new Thread(b);
        t.start();
    }


}
