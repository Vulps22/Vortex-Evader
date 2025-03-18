package com.vulps.main;

import com.vulps.main.Game.Level;
import com.vulps.main.Game.abstractObject.GameObject;
import com.vulps.main.Game.entities.Wormhole;
import com.vulps.main.ui.hud.HUD;

import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> objects = new LinkedList<GameObject>();
    LinkedList<Sound> sounds = new LinkedList<Sound>();
    private final Sound soundtrack = new Sound("soundtrack_through_prism.wav", -35f); // 0 = 100%, -100f = 0%
    public Level level = new Level();
    private int enemiesSpawned = 0;
    private int wormholesSpawned = 0;
    public HUD hud = new HUD();
    public void tick(){
        hud.tick();

        for (int i = 0; i < objects.size(); i++) { // Advanced forloop for some reason messes up

            GameObject tempObject = objects.get(i);
            tempObject.tick();

        }

        hud.setLevel(level.getLevel());

        if(!soundtrack.isLooping()) soundtrack.loop();
    }
    public void render(Graphics graphics){
        for (GameObject tempObject : objects) {
            tempObject.render(graphics);
        }
    }
    public void addObject(GameObject object){
        this.objects.add(object);
    }
    public GameObject getObject(ID id) {
        try {
            for (GameObject tempObject : objects) {
                if (tempObject.getId() == id) {
                    return tempObject;
                }
            }
            return null;
        } catch (ConcurrentModificationException exception) {
            return null;
        }
    }
    public void removeObject(GameObject object){
        this.objects.remove(object);
    }
    public int getEnemiesSpawned(){
        return enemiesSpawned;
    }
    public void setEnemiesSpawned(int enemies){
        this.enemiesSpawned = enemies;
    }

    public void addEnemy(GameObject object) {
        if(level.shouldSpawnEnemy(enemiesSpawned)){
            addObject(object);
            enemiesSpawned++;
        }
    }
    public void removeEnemy(GameObject object) {
        removeObject(object);
        enemiesSpawned--;
    }

    public void addWormhole(Wormhole wormhole){
        if(level.shouldSpawnWormhole(wormholesSpawned)) {
            addObject(wormhole);
            wormholesSpawned++;
        }
    }

    public void removeWormhole(Wormhole wormhole){
        removeObject(wormhole);
    }

    public void nextLevel(){
        level.nextLevel();
        wormholesSpawned = 0;
    }

    public void reset() {
        level.setLevel(1);
        objects.clear();
        enemiesSpawned = 0;
        wormholesSpawned = 0;

    }

    public LinkedList<GameObject> getObjects(){
        return this.objects;
    }
}
