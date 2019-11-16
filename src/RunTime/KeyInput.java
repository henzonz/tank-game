package RunTime;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    EventHandler eventHandler;

    public KeyInput(EventHandler h) {
        this.eventHandler = h;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) eventHandler.setForwardPressed1(true);
        else if (key == KeyEvent.VK_S) eventHandler.setBackwardPressed1(true);
        else if (key == KeyEvent.VK_A) eventHandler.setLeftRotatePressed1(true);
        else if (key == KeyEvent.VK_D) eventHandler.setRightRotatePressed1(true);
        else if (key == KeyEvent.VK_SPACE) eventHandler.setShoot1(true);

        if (key == KeyEvent.VK_UP) eventHandler.setForwardPressed2(true);
        else if (key == KeyEvent.VK_DOWN) eventHandler.setBackwardPressed2(true);
        else if (key == KeyEvent.VK_LEFT) eventHandler.setLeftRotatePressed2(true);
        else if (key == KeyEvent.VK_RIGHT) eventHandler.setRightRotatePressed2(true);
        else if (key == KeyEvent.VK_SLASH) eventHandler.setShoot2(true);


    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) eventHandler.setForwardPressed1(false);
        else if (key == KeyEvent.VK_S) eventHandler.setBackwardPressed1(false);
        else if (key == KeyEvent.VK_A) eventHandler.setLeftRotatePressed1(false);
        else if (key == KeyEvent.VK_D) eventHandler.setRightRotatePressed1(false);
        else if (key == KeyEvent.VK_SPACE) eventHandler.setShoot1(false);

        if (key == KeyEvent.VK_UP) eventHandler.setForwardPressed2(false);
        else if (key == KeyEvent.VK_DOWN) eventHandler.setBackwardPressed2(false);
        else if (key == KeyEvent.VK_LEFT) eventHandler.setLeftRotatePressed2(false);
        else if (key == KeyEvent.VK_RIGHT) eventHandler.setRightRotatePressed2(false);
        else if (key == KeyEvent.VK_SLASH) eventHandler.setShoot2(false);
    }

}
