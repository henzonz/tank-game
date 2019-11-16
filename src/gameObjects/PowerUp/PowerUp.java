package gameObjects.PowerUp;

import gameObjects.GameObject;
import gameObjects.ID;

import java.awt.*;

public class PowerUp extends GameObject {
    public PowerUp(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    public PowerUp() {
        super();
    }
}
