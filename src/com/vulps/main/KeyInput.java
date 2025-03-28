package com.vulps.main;

import com.vulps.main.Game.Game;
import com.vulps.main.Game.abstractObject.GameObject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private final Handler handler;
    private final Game game;

    public KeyInput(Handler handler, Game game){
        this.handler = handler;
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(Game.isPlayerLiving() && !game.menu.visible) {
            GameObject player = handler.getObject(ID.Player);
            if (player == null) return;
            switch (key) {
                case KeyEvent.VK_W -> player.setVelY(-5);
                case KeyEvent.VK_A -> player.setVelX(-5);
                case KeyEvent.VK_S -> player.setVelY(5);
                case KeyEvent.VK_D -> player.setVelX(5);
            }
        }

        if(key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(Game.isPlayerLiving() && !game.menu.visible) {
            GameObject player = handler.getObject(ID.Player);

            switch (key) {
                case KeyEvent.VK_W -> stopYNeg();
                case KeyEvent.VK_S -> stopYPos();
                case KeyEvent.VK_A -> stopXNeg();
                case KeyEvent.VK_D -> stopXPos();
            }
        }
    }

    private void stopXPos(){
        GameObject player = handler.getObject(ID.Player);

        if(player.getVelX() > 0) player.setVelX(0);

    }
    private void stopXNeg(){
        GameObject player = handler.getObject(ID.Player);

        if(player.getVelX() < 0) player.setVelX(0);

    }

    private void stopYPos(){
        GameObject player = handler.getObject(ID.Player);

        if(player.getVelY() > 0) player.setVelY(0);

    }
    private void stopYNeg(){
        GameObject player = handler.getObject(ID.Player);

        if(player.getVelY() < 0) player.setVelY(0);

    }
}
