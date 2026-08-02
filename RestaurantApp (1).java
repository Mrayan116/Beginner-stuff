
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
// import the things
public class RestaurantApp {
    
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Restaurant App"); // make a frame titled resturant app
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// exit on close 
        frame.setSize(500, 400);// set the size to 500, 400
        JPanel mpanel = new JPanel();// create a panel called mpanel and this panel is going to be used for the menu panel which is the first panel pizza, burger,salad,drink and salad
        
        frame.setLayout(new BorderLayout());// set the main layout of the frame to be borderlayout so I can move the components when i add them to the frame at the end
        mpanel.setBackground(new Color(0, 100, 100)); //for the menu panel set the background color to be green i used rgb color because they are easier
        mpanel.setLayout(new GridLayout(0, 1));// used grid layout because using grid layout is much easier for each indivuial panels that box layout, the grid layout 0 means the rows and 1 means the the column 
        JPanel summary = new JPanel(); // this panel is for the simmary panel the one in the middle for where the price and total shows up
        summary.setBackground(new Color(255, 200, 200));//set color to a pinkish
        summary.setBorder(BorderFactory.createTitledBorder("Order Summary"));// https://docs.oracle.com/javase/tutorial/uiswing/components/border.html this adds a border around the summary panel the border has a title called order summary the title makes it easy to understand what the panel shows
        summary.setLayout(new BorderLayout());
        summary.setPreferredSize(new Dimension(150, 200));//this sets the layout of the summary panel to borderlayout so components can be placed in regions like north or center it also sets the size of the panel to 150 pixels wide and 200 pixels tall
        JTextArea order = new JTextArea();// textarea so the user sees there order in  ther
        order.setEditable(false);// cant edit it

        JScrollPane scrollPane = new JScrollPane(order);
        summary.add(scrollPane, BorderLayout.CENTER);// this adds a scroll pane around the text area called order so you can scroll if the text gets too long the scroll pane is placed in the center of the summary panel using borderlayout
        mpanel.setBorder(BorderFactory.createTitledBorder("Menu"));// five buttons for different food items each button shows the item name and its price
        JButton pizzaButton = new JButton("Pizza $12");
        JButton burgerButton = new JButton("Burger $8");
        JButton pastaButton = new JButton("Pasta $10");
        JButton saladButton = new JButton("Salad $7");
        JButton drinkButton = new JButton("Drink $3");
        mpanel.add(pizzaButton);
        mpanel.add(burgerButton);// adds all the buttons to the menu panel
        mpanel.add(pastaButton);
        mpanel.add(saladButton);
        mpanel.add(drinkButton);
        
        double[] total = {0};// this code tracks the total price and updates the order text area when the pizza button is clicked. first, a double array total is initialized to hold the total price, starting at 0. when the pizza button is clicked, an action listener triggers, and inside the actionPerformed method, the total is increased by 12, as the pizza costs 12 dollars. then, it appends the string Pizza: $12 to the order text area to show the selection and adds the updated total, displaying the new total price after the pizza is ordered

        pizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {// now i did the same thing for the menu items and the cutomazations item regarding addaction listnener 
                total[0] += 12;
                order.append("Pizza: $12\n"); 
                order.append("Total: $" + total[0] + "\n\n"); 
            }
        });

        burgerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                total[0] += 8;
                order.append("Burger: $8\n"); 
                order.append("Total: $" + total[0] + "\n\n"); 
            }
        });

        pastaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                total[0] += 10;
                order.append("Pasta: $10\n"); 
                order.append("Total: $" + total[0] + "\n\n");
            }
        });

        saladButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                total[0] += 7;
                order.append("Salad: $7\n"); 
                order.append("Total: $" + total[0] + "\n\n"); 
            }
        });

        drinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                total[0] += 3;
                order.append("Drink: $3\n"); 
                order.append("Total: $" + total[0] + "\n\n"); 
            }
        });
        
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(new Color(156, 39, 199));
        sidePanel.setLayout(new GridLayout(0, 1));  
        sidePanel.setBorder(BorderFactory.createTitledBorder("Sides"));
        
        sidePanel.setPreferredSize(new Dimension(150, 200));

        JButton extraCbutton = new JButton("Extra Cheese $2");
        JButton extraSButton = new JButton("Extra Sauce $1");
        JButton glutenFButton = new JButton("Gluten Free $3");
        JButton doubleMButton = new JButton("Double Meat $4");
        JButton spicyButton = new JButton("Spicy $1");
        sidePanel.add(extraCbutton);
        sidePanel.add(extraSButton);
        sidePanel.add(glutenFButton);
        sidePanel.add(doubleMButton);
        sidePanel.add(spicyButton);

        extraCbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                total[0] += 2;
                order.append("Extra Cheese: $2\n"); 
                order.append("Total: $" + total[0] + "\n\n"); 
            }
        });

        extraSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                total[0] += 1;
                order.append("Extra Sauce: $1\n"); 
                order.append("Total: $" + total[0] + "\n\n"); 
            }
        });

        glutenFButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                total[0] += 3;
                order.append("Gluten-Free: $3\n"); 
                order.append("Total: $" + total[0] + "\n\n"); 
            }
        });

        doubleMButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                total[0] += 4;
                order.append("Double Meat: $4\n"); 
                order.append("Total: $" + total[0] + "\n\n"); 
            }
        });

        spicyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                total[0] += 1;
                order.append("Spicy: $1\n"); 
                order.append("Total: $" + total[0] + "\n\n"); 
            }
        });
        JPanel clear = new JPanel();
        JButton cButton = new JButton("CLEAR");
        clear.add(cButton);
        
        cButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                order.setText("");  
                total[0] = 0;       //this code creates a panel called clear and adds a button labeled "CLEAR" to it. when the button is clicked, an action listener is triggered that clears the order text area by setting its text to an empty string and resets the total price to 0 this allows the user to clear the order and start fresh
            }
        });

        frame.add(mpanel, BorderLayout.WEST);
        frame.add(sidePanel, BorderLayout.EAST);
        frame.add(summary, BorderLayout.CENTER);
        frame.add(clear, BorderLayout.SOUTH); 
        frame.setVisible(true);
    }
}
