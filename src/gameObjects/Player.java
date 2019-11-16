package gameObjects;

import RunTime.EventHandler;
import gameObjects.Bullets.SmallBullet;
import RunTime.BufferedImageLoader;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player extends GameObject {
    private int angle;
    private int velocity = 5;
    private final int rotationSpeed = 2;
    private boolean canRotate = true;

    private int cooldownSmall;
    private int cooldownLarge;

    private int health = 100;
    private final int maxHealth = 100;

    private int lives = 3;

    private EventHandler eventHandler;

    public Player(int x, int y, ID id, EventHandler eventHandler) {
        super(x, y, id);
        this.eventHandler = eventHandler;

        cooldownSmall = 0;
        cooldownLarge = 0;

        BufferedImageLoader loader = new BufferedImageLoader();

        if (id.equals(ID.Player1)) sprite = loader.loadImage("tank1.png");
        if (id.equals(ID.Player2)) sprite = loader.loadImage("tank2.png");
    }

    public void tick() {
        if (lives == 0) {
            eventHandler.removeObject(this);
        }

        if (this.id.equals(ID.Player1)) {
            if (health == 0) {
                lives--;
                x = 128;
                y = 128;
                System.out.println(lives);
                health = 100;
            }

            if (eventHandler.isShoot1() && cooldownSmall == 0) {
                if (cooldownSmall == 0) {
                    eventHandler.addObject(new SmallBullet(x + 16, y + 16, ID.SmallBullet, eventHandler, this));
                    eventHandler.setShoot1(false);

                    cooldownSmall = 50;
                } else cooldownSmall--;
            } else if (cooldownSmall != 0) cooldownSmall--;

            if (eventHandler.isForwardPressed1()) moveForward();
            else if (!eventHandler.isBackwardPressed1()) {
                velX = 0;
                velY = 0;
            }

            if (eventHandler.isBackwardPressed1()) moveBackward();
            else if (!eventHandler.isForwardPressed1()) {
                velX = 0;
                velY = 0;
            }

            collision();

            if (eventHandler.isRightRotatePressed1() && canRotate) rotateRight();
            if (eventHandler.isLeftRotatePressed1() && canRotate) rotateLeft();
        }

        if (this.id.equals(ID.Player2)) {
            if (health == 0) {
                lives--;
                x = 256;
                y = 128;
                System.out.println(lives);
                health = 100;
            }

            if (eventHandler.isShoot2() && cooldownSmall == 0) {
                if (cooldownSmall == 0) {
                    eventHandler.addObject(new SmallBullet(x + 16, y + 16, ID.SmallBullet, eventHandler, this));
                    eventHandler.setShoot2(false);

                    cooldownSmall = 50;
                } else cooldownSmall--;
            } else if (cooldownSmall != 0) cooldownSmall--;

            if (eventHandler.isForwardPressed2()) moveForward();
            else if (!eventHandler.isBackwardPressed2()) {
                velX = 0;
                velY = 0;
            }

            if (eventHandler.isBackwardPressed2()) moveBackward();
            else if (!eventHandler.isForwardPressed2()) {
                velX = 0;
                velY = 0;
            }

            collision();

            if (eventHandler.isRightRotatePressed2() && canRotate) rotateRight();
            if (eventHandler.isLeftRotatePressed2() && canRotate) rotateLeft();
        }
    }

    public void collision() {
        for (int i = 0; i < eventHandler.getList().size(); i++) {
            GameObject temp = eventHandler.getList().get(i);

            if ((temp.getId().equals(ID.Rock) || temp.getId().equals(ID.Tree) ||
                    temp.getId().equals(ID.Player1) || temp.getId().equals(ID.Player2)) && !temp.equals(this)) {

                if (id.equals((ID.Player1))) {
                    if (getBounds().intersects(temp.getBounds())) {
                        if (eventHandler.isForwardPressed1()) {
                            moveBackward();
                        } else if (eventHandler.isBackwardPressed1()) {
                            moveForward();
                        }

                        if (eventHandler.isLeftRotatePressed1() || eventHandler.isRightRotatePressed1()) canRotate = false;
                        else canRotate = true;
                    } else canRotate = true;
                }

                if (id.equals((ID.Player2))) {
                    if (getBounds().intersects(temp.getBounds())) {
                        if (eventHandler.isForwardPressed2()) {
                            moveBackward();
                        } else if (eventHandler.isBackwardPressed2()) {
                            moveForward();
                        }

                        if (eventHandler.isLeftRotatePressed2() || eventHandler.isRightRotatePressed2()) canRotate = false;
                        else canRotate = true;
                    } else canRotate = true;
                }
            }

            if (temp.getId().equals(ID.HealthPowerUp)) {
                if (getBounds().intersects(temp.getBounds())) {
                    if (health < maxHealth) health += (maxHealth - health);
                }


            }

            if (temp.getId().equals(ID.SmallBullet)) {
                if (!((SmallBullet) temp).getShooter().equals(this)) {
                    if (getBounds().intersects(temp.getBounds())) {
                        health-=10;
                        eventHandler.addObject(new Explosion(temp.getX(),temp.getY(),ID.Explosion, eventHandler));
                        eventHandler.removeObject(temp);
                    }
                }
            }
        }
    }


    public void render(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.sprite.getWidth() / 2.0, this.sprite.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.sprite, rotation, null);

        /** Health Bar **/
        g.setColor(Color.BLACK);
        g.fillRect(x-1,y-33,66,10);
        if (health < 70 && health > 40) {
            g.setColor(Color.YELLOW);
        } else if (health < 40) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.GREEN);
        }

        float width = ((float) health/(float) maxHealth) * 64;

        g.fillRect(x,y-32,(int) width,8);

        /** Lives **/
        g.setColor(Color.BLACK);
        g.fillOval(x+7,y + 79,18,18);
        g.fillOval(x+23,y + 79,18,18);
        g.fillOval(x+39,y + 79,18,18);
        if (lives >= 3) {
            g.setColor(Color.RED);
            g.fillOval(x+40,y + 80,16,16);
        } if (lives >= 2) {
            g.setColor(Color.RED);
            g.fillOval(x+24,y + 80,16,16);
        } if (lives >= 1) {
            g.setColor(Color.RED);
            g.fillOval(x+8,y + 80,16,16);
        }
    }

    private void moveBackward() {
        velX = (int) Math.round(velocity * Math.cos(Math.toRadians(angle - 270)));
        velY = (int) Math.round(velocity * Math.sin(Math.toRadians(angle - 270)));

        x += velX;
        y += velY;
    }

    private void moveForward() {
        velX = (int) Math.round(velocity * Math.cos(Math.toRadians(angle - 90)));
        velY = (int) Math.round(velocity * Math.sin(Math.toRadians(angle - 90)));

        x += velX;
        y += velY;
    }

    private void rotateLeft() {
        angle -= rotationSpeed;
    }

    private void rotateRight() {
        angle += rotationSpeed;
    }

    public int getAngle() {
        return angle;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 64, 64);
    }

    public int getLives() {
        return lives;
    }

    public void loseHealth() {
        this.health -= 5;
    }
}
