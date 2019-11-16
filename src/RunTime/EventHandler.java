package RunTime;

import gameObjects.GameObject;
import gameObjects.ID;
import gameObjects.Player;
import gameObjects.PowerUp.HealthPowerUp;

import java.awt.*;
import java.util.LinkedList;

public class EventHandler {
    LinkedList<GameObject> object = new LinkedList<GameObject>();
    private final int maxHealthPacks = 10;
    private int healthPacks = 0;
    private Rectangle rectangle;
    private int HPTimer;
    private final int HealthPackCooldownTimer = 500;
    //player 1 controls
    private boolean shoot1 = false;
    private boolean ForwardPressed1 = false;
    private boolean BackwardPressed1 = false;
    private boolean LeftRotatePressed1 = false;
    private boolean RightRotatePressed1 = false;
    //player 2 controls
    private boolean ForwardPressed2 = false;
    private boolean BackwardPressed2 = false;
    private boolean shoot2 = false;
    private boolean LeftRotatePressed2 = false;
    private boolean RightRotatePressed2 = false;

    private boolean player1Win = false;
    private boolean player2Win = false;

    private final int mapHeight = 4096;
    private final int mapWidth = 2048;

    //player1
    public boolean isShoot1() {
        return shoot1;
    }
    public void setShoot1(boolean shoot1) {
        this.shoot1 = shoot1;
    }
    public boolean isLeftRotatePressed1() {
        return LeftRotatePressed1;
    }
    public void setLeftRotatePressed1(boolean LeftRotatePressed1) {
        this.LeftRotatePressed1 = LeftRotatePressed1;
    }

    public boolean isRightRotatePressed1() {
        return RightRotatePressed1;
    }
    public void setRightRotatePressed1(boolean RightRotatePressed1) {
        this.RightRotatePressed1 = RightRotatePressed1;
    }

    public boolean isForwardPressed1() {
        return ForwardPressed1;
    }
    public void setForwardPressed1(boolean ForwardPressed1) {
        this.ForwardPressed1 = ForwardPressed1;
    }

    public boolean isBackwardPressed1() {
        return BackwardPressed1;
    }
    public void setBackwardPressed1(boolean BackwardPressed1) {
        this.BackwardPressed1 = BackwardPressed1;
    }
    //player 2
    public boolean isShoot2() {
        return shoot2;
    }
    public void setShoot2(boolean shoot2) {
        this.shoot2 = shoot2;
    }

    public boolean isLeftRotatePressed2() {
        return LeftRotatePressed2;
    }
    public void setLeftRotatePressed2(boolean LeftRotatePressed2) {
        this.LeftRotatePressed2 = LeftRotatePressed2;
    }

    public boolean isRightRotatePressed2() {
        return RightRotatePressed2;
    }
    public void setRightRotatePressed2(boolean RightRotatePressed2) {
        this.RightRotatePressed2 = RightRotatePressed2;
    }

    public boolean isForwardPressed2() {
        return ForwardPressed2;
    }
    public void setForwardPressed2(boolean ForwardPressed2) {
        this.ForwardPressed2 = ForwardPressed2;
    }

    public boolean isBackwardPressed2() {
        return BackwardPressed2;
    }
    public void setBackwardPressed2(boolean BackwardPressed2) {
        this.BackwardPressed2 = BackwardPressed2;
    }
    public void tick(){
        for(int i = 0; i < object.size(); i++){ //for loop runs through all of our gameobjects; Storing each object into a temp object which gets linkedlist object's id
            GameObject tempObject = object.get(i);

            tempObject.tick();

            if(tempObject.getId().equals(ID.Player1)) {
                if( ((Player)tempObject).getLives() == 0) {
                    player2Win = true;
                }
            }else if(tempObject.getId().equals(ID.Player2)) {
                if( ((Player)tempObject).getLives() == 0) {
                    player1Win = true;
                }
            }

        }
        if (healthPacks < maxHealthPacks && HPTimer == 0) {
            int tempx = (int) (Math.random() * (mapWidth - 64) + (0 + 64));
            int tempy = (int) (Math.random() * (mapHeight - 64) + (0 + 64));

            for (int i = 0; i < object.size(); i++) {
                rectangle = new Rectangle(tempx,tempy,64,64);

                while (rectangle.intersects(object.get(i).getBounds())) {
                    tempx = (int) (Math.random() * ((mapWidth + 64) - (0 + 64)) + (0 + 64));
                    tempy = (int) (Math.random() * ((mapHeight + 64) - (0 + 64)) + (0 + 64));

                    rectangle = new Rectangle(tempx,tempy,64,64);
                }
            }

            addObject(new HealthPowerUp(tempx,tempy, ID.HealthPowerUp, this));

            HPTimer = 100;
            healthPacks++;
        }

        HPTimer--;
    }
    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){ //for loop runs through all of our gameobjects; Storing each object into a temp object which gets linkedlist object's id
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }
    public void addObject(GameObject tempObject){
        object.add(tempObject);
    }
    public void removeObject(GameObject tempObject){
        object.remove(tempObject);
    }
    public LinkedList<GameObject> getList(){
        return this.object;
    }
    public boolean getPlayer1Win() {
        return player1Win;
    }
    public boolean getPlayer2Win() {
        return player2Win;
    }
    }



