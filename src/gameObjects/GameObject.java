package gameObjects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.EventHandler;

public abstract class GameObject {
    protected int x, y; //location of object
    protected float velX = 0, velY = 0; //speed of our object
    protected ID id;    //id of gameobjects
    private EventHandler handler;
    protected BufferedImage sprite;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public GameObject () {
        x = 0;
        y = 0;
    }

    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    public abstract void tick();    //every object needs to update
    public abstract void render(Graphics g);    //every object needs to be drawn
    public abstract Rectangle getBounds();  //for collision

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

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }
}
