import javax.swing.*;//bruhhh
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AnimationApp {
    public static void main(String[] args) {
        // usd a option pane to show the the instruction for the movement
        JOptionPane.showMessageDialog(null, 
            "Use Arrow keys to move the character (first character).\n" + "Use WASD keys to move the bear (second character).", "Controls Information", JOptionPane.INFORMATION_MESSAGE);

        // Variables for the first character
        int[] frameInt1 = {0};    // an array holding the current frame for character 1 animation starts at frame 0
        int[] x1 = {100};  // an array holding the x coordinate of character 1 starts at 100 for horizontal position
        int[] y1 = {300};  // an array holding the y coordinate of character 1 starts at 300 for vertical position
        int characterSpeed1 = 10 ; // integer for character 1's movement speed sets the rate of movement per key press
        int[] frameInt2 = {0};// same thing but for char two
        int[] x2 = {200};
        int[] y2 = {300};
        int characterSpeed2 = 10;

       // Frames for both characters
        Image[] frames1 = { new ImageIcon("frame1.png").getImage(), new ImageIcon("frame2.png").getImage(), new ImageIcon("frame3.png").getImage(), new ImageIcon("frame4.png").getImage() }; // load images for character 1
        Image[] frames2 = { new ImageIcon("frame5.png").getImage(), new ImageIcon("frame6.png").getImage(), new ImageIcon("frame7.png").getImage(), new ImageIcon("frame8.png").getImage(), new ImageIcon("frame9.png").getImage(), new ImageIcon("frame10.png").getImage() }; // load images for character 2
    //Making it an array allows you to store multiple frames for an animation in one place and cycle through them efficiently.

        // Load the background image
        Image background = new ImageIcon("incel.PNG").getImage();// This line creates an Image object that represents the background image. 
// The ImageIcon constructor takes the file path "incel.PNG" as an argument and loads the image file from the given path.

        // Panel
        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);  //i call super.paintComponent(g) to make sure the panel is properly cleared and repainted this is needed to prevent any leftover graphics from previous drawings or updates without calling this method the panel could have strange overlapping visuals or glitches
                // Get the current width and height of the panel
                int panelWidth = getWidth();
                int panelHeight = getHeight();

                // Scale the background to fit the entire panel size
                g.drawImage(background, 0, 0, panelWidth, panelHeight, this);

                // Draw first character
                g.drawImage(frames1[frameInt1[0]], x1[0], y1[0], 100, 100, null);

                // Draw second character
                g.drawImage(frames2[frameInt2[0]], x2[0], y2[0], 100, 100, null);
            }
        };

        // Timer for animation
        Timer timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {                
                // For the first character, I start by getting the current frame index
                int currentFrame1 = frameInt1[0];

                // then increment by 1
                currentFrame1 = currentFrame1 + 1;

                // check if the index has gone beyond the frames
                if (currentFrame1 >= frames1.length) {
                    // if it has reset the frame index to 0
                    currentFrame1 = 0;
                }

                // update the frame index for the first character
                frameInt1[0] = currentFrame1;

                // or the second character, we start by getting the current frame index
                int currentFrame2 = frameInt2[0];

                // then we increment the frame index by 1
                currentFrame2 = currentFrame2 + 1;
//smae thing as before 
                if (currentFrame2 >= frames2.length) {
                    currentFrame2 = 0;
                }

                // Update the frame index for the second character
                frameInt2[0] = currentFrame2;

                // repait the panel to show changes
                panel.repaint();
            }
        });

        JFrame frame = new JFrame("Animation App");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true); 

        // KeyListener for movement
        frame.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_LEFT) {
                    x1[0] -= characterSpeed1;
                } else if (key == KeyEvent.VK_RIGHT) {
                    x1[0] += characterSpeed1;
                } else if (key == KeyEvent.VK_UP) {
                    y1[0] -= characterSpeed1;
                } else if (key == KeyEvent.VK_DOWN) {
                    y1[0] += characterSpeed1;
                }
// this code checks which key is pressed by the user and moves the first character accordingly when the left arrow key is pressed the character moves to the left by decreasing the x value when the right arrow key is pressed the character moves to the right by increasing the x value when the up arrow key is pressed the character moves up by decreasing the y value and when the down arrow key is pressed the character moves down by increasing the y value the character speed is controlled by the characterSpeed1 variable which determines how much the character moves each time a key is pressed 
// same thing for the code beloew but for character two the bear

                if (key == KeyEvent.VK_A) {
                    x2[0] -= characterSpeed2;
                } else if (key == KeyEvent.VK_D) {
                    x2[0] += characterSpeed2;
                } else if (key == KeyEvent.VK_W) {
                    y2[0] -= characterSpeed2;
                } else if (key == KeyEvent.VK_S) {
                    y2[0] += characterSpeed2;
                }

                int panelWidth = panel.getWidth();
                int panelHeight = panel.getHeight();
                int characterWidth = 100;
                int characterHeight = 100;
                x1[0] = Math.max(0, Math.min(x1[0], panelWidth - characterWidth));
                y1[0] = Math.max(0, Math.min(y1[0], panelHeight - characterHeight));
                x2[0] = Math.max(0, Math.min(x2[0], panelWidth - 100));
                y2[0] = Math.max(0, Math.min(y2[0], panelHeight - 100));
//this code gets the width and height of the panel where the characters are drawn and then it checks that the characters x and y positions dont go outside the panel by comparing the current position of the characters with the edges of the panel and then it makes sure that the characters stay within the panel boundaries by making sure the x and y coordinates of both characters stay between 0 and the width and height of the panel minus the size of the character this way no part of the character can go off the screen
            }

            public void keyReleased(KeyEvent e) {}

            public void keyTyped(KeyEvent e) {}
        });

        frame.add(panel);
        frame.setVisible(true);
        timer.start();
    }
}
