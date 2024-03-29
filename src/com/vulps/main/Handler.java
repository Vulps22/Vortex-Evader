package com.vulps.main;

import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();
    LinkedList<Sound> sounds = new LinkedList<Sound>();
    private final Sound soundtrack = new Sound("soundtrack_through_prism.wav", -35f); // 0 = 100%, -100f = 0%
    public Level level = new Level();
    private int enemiesSpawned = 0;
    private int wormholesSpawned = 0;
    public HUD hud = new HUD();
    public void tick(){
        hud.tick();

        for (int i = 0; i < object.size(); i++) { // Advanced forloop for some reason messes up

            GameObject tempObject = object.get(i);
            tempObject.tick();

        }

        hud.setLevel(level.getLevel());

        if(!soundtrack.isLooping()) soundtrack.loop();
    }
    public void render(Graphics graphics){
        for (GameObject tempObject : object) {
            tempObject.render(graphics);
        }
    }
    public void addObject(GameObject object){
        this.object.add(object);
    }
    public GameObject getObject(ID id) {
        try {
            for (GameObject tempObject : object) {
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
        this.object.remove(object);
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
        object.clear();
        enemiesSpawned = 0;
        wormholesSpawned = 0;

    }
}
