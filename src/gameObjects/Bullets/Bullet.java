package gameObjects.Bullets;

import RunTime.EventHandler;
import gameObjects.GameObject;
import gameObjects.ID;

import java.awt.*;


public abstract class Bullet extends GameObject {
    private int angle;
    EventHandler eventHandler;

    public Bullet(int x, int y, ID id, EventHandler eventHandler) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.eventHandler = eventHandler;
    }

    public Bullet() {
        super();
    }

    public abstract void tick();
    public abstract void render(Graphics g);


    public abstract Rectangle getBounds();
}
