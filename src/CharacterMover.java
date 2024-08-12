import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class CharacterMover extends JPanel implements KeyListener {

    private BufferedImage[] frames;
    private int currentFrame = 0;
    private int x = 0, y = 0; // Initial position of the character
    public CharacterMover() {
        loadFrames();
        setPreferredSize(new Dimension(800, 600)); // Set the preferred size of the panel
        setBackground(Color.BLACK); // Set the background color
        addKeyListener(this);
        setFocusable(true);
    }

    private void loadFrames() {
        try {
            BufferedImage spriteSheet = ImageIO.read(new File("C://Users//Thaiv//java2//diff//448575392_1122080938892173_7178048838988480726_n.png"));
            frames = new BufferedImage[8];
            for (int i = 0; i < 8; i++) {
                frames[i] = spriteSheet.getSubimage(i * (76+24)+38, 38, 24, 24);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void flip(){
        
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (frames != null && frames[currentFrame] != null) {
            g.drawImage(frames[currentFrame], x, y, null);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W -> y -= 5;
            case KeyEvent.VK_A -> x -= 5;
            case KeyEvent.VK_S -> y += 5;
            case KeyEvent.VK_D -> x += 5;
        }
        currentFrame = (currentFrame + 1) % frames.length; // Cycle through the frames
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Character Mover");
        CharacterMover mover = new CharacterMover();
        frame.add(mover);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
