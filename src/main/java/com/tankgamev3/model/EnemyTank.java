package com.tankgamev3.model;

import com.tankgamev3.entity.Tank;

public class EnemyTank extends Tank implements Runnable{

    public EnemyTank(int x, int y){
        super(x,y);
        this.setDirect((int)(Math.random()*4));
        this.setType(0);
        this.setSpeed(2);
    }

    public void run() {

        while(true){

            switch(this.direct){
                case 0:

                    for (int i = 0; i < 30; i++) {
                        if(this.y>0){
                            this.y-=speed;
                        }else{
                            break;
                        }
                        try{
                            Thread.sleep(50);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    break;

                case 1:

                    for (int i = 0; i < 30; i++) {
                        if(this.x<370) {
                            this.x += speed;
                        }else{
                            break;
                        }
                        try{
                            Thread.sleep(50);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    break;

                case 2:

                    for (int i = 0; i < 30; i++) {
                        if(this.y<270){
                            this.y+=speed;
                        }else{
                            break;
                        }
                        try{
                            Thread.sleep(50);
                        }catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case 3:

                    for (int i = 0; i < 30; i++) {
                        if(this.x>0){
                            this.x-=speed;
                        }else{
                            break;
                        }
                        try{
                            Thread.sleep(50);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    break;
                    }

            this.direct = (int)(Math.random()*4);

            if(!this.isLive){
                break;
            }
        }
    }
}
