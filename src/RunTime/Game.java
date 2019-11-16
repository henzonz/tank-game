package RunTime;

import gameObjects.ID;
import gameObjects.Player;
import gameObjects.Rock;
import gameObjects.Tree;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Game extends JPanel implements Runnable {
    private Thread thread;
    private boolean isRunning = false;
    private EventHandler eventHandler;
    private Camera camera;
    private BufferedImage lvl, background, gameOver, player1Win, player2Win;


    public Game(){
        eventHandler = new EventHandler();
        this.addKeyListener(new KeyInput(eventHandler));
        camera = new Camera(0,0);

        BufferedImageLoader load = new BufferedImageLoader();
        background = load.loadImage("grass.png");
        lvl = load.loadImage("backdrop.png");
        gameOver = load.loadImage("gameOver.png");
        player1Win = load.loadImage("player1Win.png");
        player2Win = load.loadImage("player2Win.png");


        loadLevel(lvl);

        window();
        start();
    }
    private void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }
    private void stop(){
        isRunning = false;
        try{
            thread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            repaint();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frames = 0;
            }
        }
        stop();

    }
    public void tick(){
        for(int i = 0; i < eventHandler.object.size(); i++){ //loops through all objects and finding out which object is the player, and putting that into the parameters of our camera
            if(eventHandler.object.get(i).getId() == ID.Player1){
                camera.tick(eventHandler.object.get(i));
            }
        }
        eventHandler.tick();

    }
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g2d.translate(-camera.getX(), -camera.getY());

        g2d.drawImage(background, 0, 0, null);

        eventHandler.render(g);

        if (eventHandler.getPlayer1Win() || eventHandler.getPlayer2Win()) {
            g.drawImage(gameOver, camera.getX() + 360, camera.getY() + 100, null);

            if (eventHandler.getPlayer1Win()) {
                g.drawImage(player1Win, camera.getX() + 180, camera.getY() + 400, null);
            } else {
                g.drawImage(player2Win, camera.getX() + 170, camera.getY() + 400, null);
            }
        }

        g2d.translate(camera.getX(), camera.getY());

        g.dispose();
        g2d.dispose();
    }
    private void loadLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();

        for (int xx = 0; xx < w; xx++) {
            for (int yy = 0; yy < h; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (green == 0 && blue == 0 && red == 255) eventHandler.addObject(new Rock(xx * 64, yy * 64, ID.Rock));
                if (green == 0 && blue == 255 && red == 0)
                    eventHandler.addObject(new Player(xx * 64, yy * 64, ID.Player1, eventHandler));
                if (green == 255 && blue == 255 && red == 0)
                    eventHandler.addObject(new Player(xx * 64, yy * 64, ID.Player2, eventHandler));
                if (green == 255 && blue == 0 && red == 0)
                    eventHandler.addObject(new Tree(xx * 64, yy * 64, ID.Tree, eventHandler));
            }
        }
    }
    public void window(){
        JFrame window = new JFrame ("Tank Wars Game");

        window.setPreferredSize(new Dimension(1280, 720));
        window.setMaximumSize(new Dimension(1280, 720));
        window.setMinimumSize(new Dimension(1280, 720));

        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);

        window.add(this);
        window.setVisible(true);
    }
    public static void main(String[] args){
        new Game();
    }
}
