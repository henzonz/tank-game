package RunTime;

import gameObjects.GameObject;

public class Camera {
    private int x, y;
    public Camera(int x, int y){
        this.x = x;
        this.y = y;
    }

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

    public void tick(GameObject object){
        x += ((object.getX() - x) - 1000/2) * 0.05f;    //algorithm
        y += ((object.getY() - y) - 563/2) * 0.05f;    //algorithm
        if(x <= 0) x = 0;   //limits the camera based on our map (so it doesnt move outside of our map)
        if(x >= 1032) x = 1032;
        if(y <= 0) y = 0;
        if(y >= 563+48) y=563+48;
    }
}
