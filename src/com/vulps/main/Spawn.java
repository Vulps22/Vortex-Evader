package com.vulps.main;

import com.vulps.main.Game.Game;
import com.vulps.main.Game.abstractObject.GameObject;
import com.vulps.main.Game.character.BasicEnemy;
import com.vulps.main.Game.entities.Wormhole;


import java.util.Random;

public class Spawn {

    private final Handler handler;
    private int scoreKeep = 0;
    private final Random r = new Random();
    private int delta = 100;
    public Spawn(Handler handler){
        this.handler = handler;
    }

    public void tick(){
        if(Game.isPlayerLiving()) {
            scoreKeep++;
        }else{
            GameObject object = handler.getObject(ID.Player);
            if(object != null) handler.addObject(new BasicEnemy(object.getX(), object.getY(), ID.BasicEnemy, handler));

            handler.removeObject(object);
        }
        if(delta <= 0){
            if(scoreKeep < handler.level.getScoreLimit()){
                handler.addWormhole(new Wormhole(getSpawnableX(), getSpawnableY(), ID.Wormhole, handler, false));
            }else{
                handler.nextLevel();
                scoreKeep = 0;
            }
            delta = 100;
        }
        delta--;
    }

    private int getSpawnableX(){
        int x = r.nextInt(Game.WIDTH - 200);
        if(x < 100) x = 100;
        return x;
    }

    private int getSpawnableY(){
        int y = r.nextInt(Game.HEIGHT - 200);
        if(y < 100) y = 100;
        return y;
    }

}
