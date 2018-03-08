package com.tankgamev3.model;/*
 * Class PlayerTank
 * @author Yunqi Cui
 * 27/02/2018
 */


import com.tankgamev3.entity.Tank;

public class PlayerTank extends Tank {

    //up:0 right:1 down:2 left:3

    public PlayerTank(int x, int y) {
        super(x, y);
        this.setDirect(0);
        this.setSpeed(5);
        this.setType(1);
    }


    public void moveUp(){
        if(this.y>0){
        this.y-=this.getSpeed();
        }
    }

    public void moveRight(){
        if(x<370){
        this.x+=this.getSpeed();
        }
    }

    public void moveDown(){
        if(y<270){
        this.y+=this.getSpeed();
        }
    }

    public void moveLeft(){
        if(x>0){
        this.x-=this.getSpeed();
        }
    }

}
