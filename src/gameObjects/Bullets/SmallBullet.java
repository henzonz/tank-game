package gameObjects.Bullets;

import RunTime.BufferedImageLoader;
import RunTime.EventHandler;
import gameObjects.*;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class SmallBullet extends Bullet {
    private int angle;
    private final int velocity = 20;
    private Player shooter;

    public SmallBullet(int x, int y, ID id, EventHandler eventHandler, Player shooter) {
        super(x, y, id, eventHandler);
        this.shooter = shooter;

        setAngle();

        BufferedImageLoader loader = new BufferedImageLoader();
        sprite = loader.loadImage("bullet.png");
    }

    public void setAngle() {
        for (int i = 0; i < eventHandler.getList().size(); i++) {
            GameObject temp = eventHandler.getList().get(i);
            if (temp.equals(shooter)) {
                angle = shooter.getAngle();
            }
        }
    }

    public void moveForward() {
        velX = (int) Math.round(velocity * Math.cos(Math.toRadians(angle - 90)));
        velY = (int) Math.round(velocity * Math.sin(Math.toRadians(angle - 90)));

        x += velX;
        y += velY;
    }

    public void tick() {
        moveForward();

        for (int i = 0; i < eventHandler.getList().size(); i++) {
            GameObject temp = eventHandler.getList().get(i);
            if (getBounds().intersects(temp.getBounds())) {
                if (temp.getId().equals(ID.Rock)) {
                    eventHandler.removeObject(this);
                    eventHandler.addObject(new Explosion(x,y,ID.Explosion, eventHandler));
                }
            }
        }
    }

    public void render(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.sprite.getWidth() / 2.0, this.sprite.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.sprite, rotation, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 16);
    }

    public Player getShooter() {
        return shooter;
    }
}
