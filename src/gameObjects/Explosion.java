package gameObjects;

import RunTime.BufferedImageLoader;
import RunTime.EventHandler;
import RunTime.Game;

import java.awt.*;

public class Explosion extends GameObject {
    private int counter = 0;
    private EventHandler eventHandler;

    public Explosion(int x, int y, ID id, EventHandler eventHandler) {
        super(x,y,id);
        this.eventHandler = eventHandler;

        BufferedImageLoader loader = new BufferedImageLoader();
        sprite = loader.loadImage("explode1.png");
    }

    public void tick() {
        BufferedImageLoader loader = new BufferedImageLoader();

        switch (counter) {
            case 2:
                sprite = loader.loadImage("explode2.png");
                break;
            case 4:
                sprite = loader.loadImage("explode3.png");
                break;
            case 6:
                sprite = loader.loadImage("explode4.png");
                break;
            case 8:
                sprite = loader.loadImage("explode5.png");
                break;
            case 10:
                sprite = loader.loadImage("explode6.png");
                break;
            case 12:
                sprite = loader.loadImage("explode7.png");
                break;
            case 14:
                sprite = loader.loadImage("explode8.png");
                break;
            case 16:
                sprite = loader.loadImage("explode9.png");
                break;
            case 18:
                sprite = loader.loadImage("explode10.png");
                break;
            case 20:
                sprite = loader.loadImage("explode11.png");
                break;
            case 22:
                sprite = loader.loadImage("explode12.png");
                break;
            case 24:
                sprite = loader.loadImage("explode13.png");
                break;
            case 26:
                sprite = loader.loadImage("explode14.png");
                break;
            case 28:
                sprite = loader.loadImage("explode15.png");
                break;
            case 30:
                eventHandler.removeObject(this);
                break;
        } counter++;
    }

    public void render(Graphics g) {
        g.drawImage(sprite,x,y,null);
    }
    public Rectangle getBounds() {
        return new Rectangle (0,0,1,1);
    }
}
