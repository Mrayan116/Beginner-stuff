import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;// bruh
//bossmannn
public class MazeGame {
    private static final int tilesize = 37; // Represents the size of each tile in the maze , and I used private static so it's accessible all over the code and I also used final because I don't want this value to be changed after it's initialized.
    private static final int mazeheight = 17;// represents the the the height of the maze(columns), used private static final because its accessible all over the code and I also used final because I don't want this value to be changed after its initialized
    private static final int mazewidth = 20;// same thing but for the width (rows) and use private static final
    private int playerrow = 1, playercol = 1;// start the player at col1 and ro1 1, so the player starts off there
    private int exitrow = mazeheight - 2, exitcol = mazewidth - 2; // this is going to be the exit for the player, i take the maze height and maze width and minus them by two, so the exit would show up at the bottom right of the maze, basically take the whole make minus the rows by two and column by two and that is the location of the exit,

    private final int[][] maze = {  // 1 is walls and 0 is walkable and 2 is the easter egg.                                  
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 2, 1, 0, 0, 0, 1, 1, 1, 2, 1},// This array is declared as private to restrict access to this variable within the class.It is also final because the maze's structure is not meant to change after being initialized.
        {1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1},// this is a 2d arrays, most maze codes use this because it helps you visualize the code better, for example u can see each individual piece in the array, this helps better with positioning with the player, exit and enemies
        {1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1},
        {1, 2, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
        {1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1},
        {1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
        {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1},
        {1, 0, 1, 2, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1},
        {1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    private MazePanel mazePanel;// private instance variable and this is where the maze will be displayed 
    private int score = 50;// starting score for the 0, these are all private because I restrict it from the class, so nothing messes with them
    private int timeLeft = 180;// time limit, the time starts at 180s and decreases by 1 over time
    private Timer gameTimer;// // This variable is used to manage the game's timing, such as counting down the time left (timeLeft)

    private int[] frameInt = {0};// animation app,// This array holds the current frame index for the animation, starting at 0. It's used to cycle through frames to animate the player.  
    private Image[] playerFrames = {
        new ImageIcon("frame1.png").getImage(), new ImageIcon("frame2.png").getImage(), new ImageIcon("frame3.png").getImage(), new ImageIcon("frame4.png").getImage(),// load the images
    };
// vertical
    private int enemyRow = 1;// Tracks the current row where the enemy is located, starting at row 1.  
    private int enemyCol = 8;// same thing but at column 8
    private int enemyDirection = 1;// this means the enemy direction moves one up and one down 1 is going down -1 is up
    private Image enemyImage = new ImageIcon("enemy.png").getImage();// get the image
    //horizontal
    private int enemy2Row = 11; // tracks the enemy, located at row 11
    private int enemy2Col = 1;// column 1
    private int enemy2Direction = 1;// direction right
    private Image enemy2Image = new ImageIcon("enemy2.png").getImage();// get the image
    //vertical
    private int enemy3Row = 3;
    private int enemy3Col = 16;
    private int enemy3Direction = 1;
    private Image enemy3Image = new ImageIcon("enemy3.png").getImage();
    //vertical
    private int enemy4Row = 10;
    private int enemy4Col = 14;
    private int enemy4Direction = 1;  
    private Image enemy4Image = new ImageIcon("enemy4.png").getImage();  

    // Table setup
    String[] columnNames = {"Mazegame", "Score"};// make an array of column names
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
    JTable table = new JTable(tableModel);
    JScrollPane tableScrollPane = new JScrollPane(table);// //table model with column names but no data. then it creates a table using that model and adds the table to a scrollable pane so you can scroll through the table if it gets too big.

    public MazeGame() {
        JFrame frame = new JFrame("Maze Game");// make a frame called frame and title it maze game
        int frameWidth = mazewidth * tilesize;// set the width and height of the frame by the sizes I chose for the mazewidth and tile size
        int frameHeight = mazeheight * tilesize;
        frame.setSize(frameWidth, frameHeight);// set the size of the frame, using framewidth and frame height which I got from multiple tilesize bu maze width
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(0, 0); // Place the window at the top left corner of the screen (coordinates 0, 0). This is so I can make sure the frame is always at a constant position.
        frame.setLayout(new BorderLayout());// set the layout of the frame borderlayout
        mazePanel = new MazePanel(); // Creates a new instance of MazePanel, a custom class that defines the maze's design and behavior. This object will handle drawing the maze and managing its interactions.
        frame.add(mazePanel, BorderLayout.CENTER);// Pass it to the frame

        // Score Panel
        JPanel scorePanel = new JPanel();// create a panel called score panel
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));// set the laout for the score panel box layout because I find anything to do with scoring, I usually place them near a timer, so having a box layout I can box them together.
        scorePanel.setBackground(Color.RED);

        JLabel scoreLabel = new JLabel("Score: " + score);// make a label called score label with the score label I say the score is and the score
        JLabel timerLabel = new JLabel("Time Left: " + timeLeft + "s"); // same thing with this line 
        Font font = new Font("Arial", Font.BOLD, 18);// set the font to arial, bold and the size to be 18
        scoreLabel.setFont(font);// set the font to the score label
        timerLabel.setFont(font);// set font to the timer label
        scorePanel.add(scoreLabel);// add the lalebs
        scorePanel.add(timerLabel);
        scorePanel.add(tableScrollPane);
        frame.add(scorePanel, BorderLayout.EAST);// add scroll panel to the frame and using border layout set it east 
        mazePanel.setFocusable(true); // Ensures that the mazePanel can receive focus, which is necessary for it to detect and respond to user input like keyboard events.
        mazePanel.requestFocusInWindow();

        mazePanel.addKeyListener(new KeyAdapter() { // Adds a KeyListener to the mazePanel to listen for keyboard events.
            @Override
            public void keyPressed(KeyEvent e) { 
                movePlayer(e.getKeyCode()); // // Calls the movePlayer method, passing the key code of the pressed key to handle the player's movement.
            }
        });

        frame.setVisible(true);// set the frame to be visible 
        Timer animationTimer = new Timer(100, e -> updatePlayerAnimation());// Creates a timer that runs every 100 milliseconds, calling the updatePlayerAnimation method each time.

        animationTimer.start();// Starts the animation timer so that it begins running.

        gameTimer = new Timer(1000, e -> {// Creates another timer that runs every sec, The code block inside the lambda (which is not shown in full) will execute once every second to check for tasks such as updating the game state (checking for collisions, updating scores, or handling time based events).
            if (timeLeft > 0) {// if the timeleft is greater than 0
                timeLeft--;// time loses by 1
                score--;// score losses by one over time
                scoreLabel.setText("Score: " + score);// set it to the score label so it display the score and same for the timer
                timerLabel.setText("Time Left: " + timeLeft + "s");

                frame.setTitle("Maze Game - Score: " + score + " | Time Left: " + timeLeft + "s");// also set this the title for the maze so u have to places to look for score and timer
                updateEnemyPosition();// update enemy position
                updateEnemy2Position();
                updateEnemy3Position();
                updateEnemy4Position();
            }

            if (timeLeft == 0) {// if the time reaches zero then
                gameTimer.stop();
                JOptionPane.showMessageDialog(null, "Time's up! You lose. Final Score: " + score);// show a losing score to the user saying times up and show the final score to them

                int response = JOptionPane.showConfirmDialog(null, "Do you want to try again?", "Game Over", JOptionPane.YES_NO_OPTION);// asks the user if they want to restart the game

                if (response == JOptionPane.YES_OPTION) {// if the user does I just made it so it restarts the player,timer and score because the enemies will just keep moving in the same pattern
                    timeLeft = 180;
                    score = 50;
                    playerrow = 1;
                    playercol = 1;
                    gameTimer.start();
                } else {
                    System.exit(0);// if not exit the system
                }
            }
        });
        gameTimer.start();
    }

    private void updateEnemyPosition() {
        if (enemyDirection == 1) {// if the enemy is moving downward, move them down by, if not then move the enemy back up by 1 (remember the enemy direction variable, 1 means down -1 means up)
            enemyRow++;
        } else {
            enemyRow--;
        }

        if (enemyRow == 8 || enemyRow == 1) { // if the enemy reach row 8 OR row 1 revert the direction of the enemy
            enemyDirection *= -1;
        }
    }
// same for everything instead this one us just horizontal
    private void updateEnemy2Position() {
        if (enemy2Direction == 1) {
            enemy2Col++;
        } else {
            enemy2Col--;
        }

        if (enemy2Col == 11 || enemy2Col == 1) {
            enemy2Direction *= -1;
        }
    }

    private void updateEnemy3Position() {
        if (enemy3Direction == 1) {
            enemy3Row++;
        } else {
            enemy3Row--;
        }

        if (enemy3Row == 8 || enemy3Row == 3) {
            enemy3Direction *= -1;
        }
    }

    private void updateEnemy4Position() {
        if (enemy4Direction == 1) {
            enemy4Row++;
        } else {
            enemy4Row--;
        }

        if (enemy4Row == 8 || enemy4Row == 16) {
            enemy4Direction *= -1;
        }
    }
 
    private void movePlayer(int keyCode) { // Method to move the player based on the key pressed by the user                 ///////////////////
        int newRow = playerrow, newCol = playercol;// variables to store the player's new position based on the movement direction
        if (keyCode == KeyEvent.VK_UP) {// if the up key is pressed decrease the rows or the index of rows to move the player up
            newRow--;
        } else if (keyCode == KeyEvent.VK_DOWN) {// if the down key is pressed then increase the rows or the index or rows to move the player down
            newRow++;
        } else if (keyCode == KeyEvent.VK_LEFT) {
            newCol--;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            newCol++;
        }

        if (maze[newRow][newCol] == 0 || maze[newRow][newCol] == 2) {// if the newrow/newcol = to 0 then track the movement because 0 is the walkable spaces and 2 is the easter egg so they are allowed or are valid movements
            playerrow = newRow;
            playercol = newCol;

            // Check if player reached the exit
            if (playerrow == exitrow && playercol == exitcol) {// checks if the playerrow is the same as exit row AND if the playercol is the same as exit col, then the timer would stop and a option pane would show up saying you win and showing the final score
                gameTimer.stop();
                JOptionPane.showMessageDialog(null, "You Win! Final Score: " + score);

                Object[] rowData = {"Maze Completion", String.valueOf(score), String.valueOf(score)};
                tableModel.addRow(rowData);// after winning and only after winning the score gets added to the table

                int response = JOptionPane.showConfirmDialog(null, "Do you want to restart the game?", "Game Over", JOptionPane.YES_NO_OPTION);// allow for restart after winning 
                if (response == JOptionPane.YES_OPTION) {// same code as before
                    score = 50;
                    timeLeft = 180;
                    playerrow = 1;
                    playercol = 1;
                    gameTimer.start();
                } else {
                    System.exit(0);
                }
            }

            // Check for Easter egg
            if (maze[newRow][newCol] == 2) {// if the newrow,newcol = to 2 then it shows the message and increments the score by 50 and makes it a white tile after
                JOptionPane.showMessageDialog(null, 
        "You found the Easter egg! Bonus Points!\n" +
        "Easter eggs are hidden features inside programs. Finding them gives you extra points, \n" +
        "opens up hidden areas, and makes the program/game more enjoyable.");
                score += 20;
                maze[newRow][newCol] = 0;
            }

            // Check for collision with enemies by doing if the player row/col hit the enemy row/col and if it did u hit the enemy and game over and it restarts the game and it also shows a Joption pane saying u lost 
             if ((playercol == enemyCol && playerrow == enemyRow) ||(playercol == enemy2Col && playerrow == enemy2Row) ||(playercol == enemy3Col && playerrow == enemy3Row) ||(playercol == enemy4Col && playerrow == enemy4Row)) {
                JOptionPane.showMessageDialog(null, "You hit an enemy! Game Over.");
                // Stop the game timer (only the countdown)
                gameTimer.stop();
                // let the user to restart or exit
                int response = JOptionPane.showConfirmDialog(null, "Do you want to restart the game?", "Game Over", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {// smae code
                    score = 50;
                    timeLeft = 180;  
                    playerrow = 1;
                    playercol = 1;
                    gameTimer.start();  
                } else {
                    System.exit(0);  // Close the game
                }
            }
        }

        mazePanel.repaint();
    }

    private void updatePlayerAnimation() {   // This method updates the player's animation frame each time it is called     ////////////
        int currentFrame = frameInt[0];// Gets the current frame of the player animation from frameInt array (index 0)
        currentFrame++;// Increments the current frame by 1 to move to the next frame in the animation sequence

        if (currentFrame >= playerFrames.length) {
            currentFrame = 0;// Resets the current frame to 0 (the first frame) to loop the animation
        }
        frameInt[0] = currentFrame;// Updates the first element of the frameInt array with the new currentFrame value, setting the new animation frame index
        mazePanel.repaint();
    }

    private class MazePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);// Calls the parent classs paint component method to ensure the panel is properly rendered before custom drawing.

            for (int row = 0; row < mazeheight; row++) { // Loops through each row of the maze to draw tiles.
                for (int col = 0; col < mazewidth; col++) { // Loops through each column in the current row of the maze
                    if (maze[row][col] == 1) { // If the current tile is a wall set the tile color to black.
                        g.setColor(Color.BLACK);
                    } else if (maze[row][col] == 2) {// If the current tile is the easter egg set it 2
                        g.setColor(Color.WHITE);
                    } else if (row == exitrow && col == exitcol) {
                        g.setColor(Color.GREEN);  // Color the exit tile green
                    } else {
                        g.setColor(Color.WHITE);// color to white
                    }
                    g.fillRect(col * tilesize, row * tilesize, tilesize, tilesize); // Draws a filled rectangle for the current tile at the specified location and size.
                    g.setColor(Color.GRAY);// sets border to grey
                    g.drawRect(col * tilesize, row * tilesize, tilesize, tilesize);// Draws the border around the current tile.
                }
            }

            // Draw  player
            g.drawImage(playerFrames[frameInt[0]], playercol * tilesize, playerrow * tilesize, tilesize, tilesize, null);

            // Draw  enemies
            g.drawImage(enemyImage, enemyCol * tilesize, enemyRow * tilesize, tilesize, tilesize, null);
            g.drawImage(enemy2Image, enemy2Col * tilesize, enemy2Row * tilesize, tilesize, tilesize, null);
            g.drawImage(enemy3Image, enemy3Col * tilesize, enemy3Row * tilesize, tilesize, tilesize, null);
            g.drawImage(enemy4Image, enemy4Col * tilesize, enemy4Row * tilesize, tilesize, tilesize, null);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MazeGame::new);
    }
}









