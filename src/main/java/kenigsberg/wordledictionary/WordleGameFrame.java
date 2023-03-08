package kenigsberg.wordledictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WordleGameFrame extends JFrame {
    //It should instantiate the views, instantiate controller, probably set up Action Listener

    private final JButton[] keyboard = new JButton[26];
    private final WordleController controller;
    private JLabel[][] fields = new JLabel[6][5];

    private JButton enter;

    private JButton backspace;


    public WordleGameFrame(WordleGame wordleGame, WordleDictionary dictionary) {

        controller = new WordleController(wordleGame, dictionary, fields, keyboard, enter, backspace);

        String[] letters = new String[]{"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
                "A", "S", "D", "F", "G", "H", "J", "K", "L",
                "Z", "X", "C", "V", "B", "N", "M"};

        //Main Panel - Border Layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());


        //--> Center Panel - grid Layout
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(6, 5, 5, 5));


        //--> South Panel - boxLayout filled with 3 FlowLayouts
        JPanel flowLayout1 = new JPanel();
        flowLayout1.setLayout(new FlowLayout());

        JPanel flowLayout2 = new JPanel();
        flowLayout2.setLayout(new FlowLayout());

        JPanel flowLayout3 = new JPanel();
        flowLayout3.setLayout(new FlowLayout());


        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));

        southPanel.add(flowLayout1);
        southPanel.add(flowLayout2);
        southPanel.add(flowLayout3);

        JLabel title = new JLabel("Welcome to Wordle Game");
        mainPanel.add(title, BorderLayout.NORTH);
        title.setHorizontalAlignment(JLabel.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);


        //Add code here


        //For every iteration of JLabel 2D Array, create Label, add to array, and add to centerPanel
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                fields[i][j] = new JLabel();
                fields[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                centerPanel.add(fields[i][j]);
            }
        }

        //For every Button of keyboard, want to create new button and set the letter to the button
        // (in order of how it will appear in the keyboard)
        for (int i = 0; i < keyboard.length; i++) {
            keyboard[i] = new JButton(letters[i]);
            keyboard[i].setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        }

        //add first line of keyboard to flowLayout1
        for (int i = 0; i < 10; i++) {
            flowLayout1.add(keyboard[i]);
        }

        //add second line keyboard to flowLayout2
        for (int i = 10; i < 19; i++) {
            flowLayout2.add(keyboard[i]);
        }

        //add third line keyboard to flowLayout3

        enter = new JButton("Enter");
        enter.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        flowLayout3.add(enter);

        for (int i = 19; i < 26; i++) {
            flowLayout3.add(keyboard[i]);
        }
        backspace = new JButton("Del");
        backspace.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        flowLayout3.add(backspace);


        //AT THIS POINT THE UI IS CREATED


        //Now want to listen for the letters being pressed
        //(BOTH by pressing buttons & listening to keyboard typed)


        //This will listen out for buttons pressed

        //Letters pressed
        for (JButton letter : keyboard) {

            letter.addActionListener(e -> {
                //Now if one of the letters have been pressed (on screen buttons) want to call the Controller
                controller.addLetter(letter.getText());
                System.out.println("Letter pressed");

            });
        }

        //Enter pressed
        //!!!!QUESTION FOR PROF. SCHWIMMER NEXT CLASS:
        //WHY DID THE LAMBDA VERSION OF ENTER & DELETE NOT WORK???


        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.enterGuess();
                System.out.println("Enter pressed");
            }
        });

        //backspace pressed
        backspace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.backspace();
                System.out.println("Backspace pressed");
            }
        });

        //This will listen out for key's typed
        setFocusable(true);
        requestFocus();

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("Reached keyListener");
                char character = e.getKeyChar();
                if (Character.isAlphabetic(character)) {
                    controller.addLetter(String.valueOf(e.getKeyChar()));
                    System.out.println("Letter pressed on keyboard");
                } else if (character == KeyEvent.VK_ENTER) {
                    controller.enterGuess();
                    System.out.println("Enter pressed on keyboard");
                } else if (character == KeyEvent.VK_BACK_SPACE) {
                    controller.backspace();
                    System.out.println("Backspace pressed on keybaord");
                }
            }


            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


        setContentPane(mainPanel);
        setSize(800, 600);
        setTitle("Wordle Game");
        //What happens when we hit the exit button
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        /*
        Main Panel - Border Layout
            --> Center Panel - grid Layout
            --> South Panel - boxLayout filled with 3 FlowLayouts

        Wordle Game:
        2D Array of JLabels
            Arranged in a JPanel center with a GridLayout

        Keyboard - JButtons
            1 for each letter
            + backspace
            + enter

        Create 28 Buttons for the keyboard, and 28 ActionListeners for the buttons
        Every time action (button) is done, call method to guess
            --> LOOK AT WIKEPEDIA DESIGN PATTERNS
            --> MODEL VIEW CONTROLLER
                    Model - in memory representation of program without UI
                    Views - display the model
                    Controller - handle input, update model, and update views

        Features:
        Handle "not in wordList"

        Color code the guess result
            Not Found = grey
            Wrong Place = gold
            Correct = Green

         Allowed 6 guesses of 5-letter words

         Handle on screen keyboard + keys
         */

        /*
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(6, 5));

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                JTextField field = new JTextField("MY LABEL " + i + ", " + j);
                centerPanel.add(field);
            }
        }
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        */



        /*
        JPanel theMainPanel = new JPanel();
        theMainPanel.setLayout(new BorderLayout());

        JTextField guess = new JTextField("Enter your guess");
        theMainPanel.add(guess, BorderLayout.NORTH);


        JButton button = new JButton("Guess");
        theMainPanel.add(button, BorderLayout.SOUTH);

        JLabel result = new JLabel();
        theMainPanel.add(result, BorderLayout.CENTER);


        button.addActionListener(e -> {
            System.out.println("Guess Clicked. Calling guess method...");
            button.setText("Processing Guess...");

            theGuess = guess.getText();
            guessResult = wordleGame.guess(theGuess);

            result.setText(Arrays.toString(guessResult));
            button.setText("Guess Again");
        });


        setContentPane(theMainPanel);
        setSize(400, 400);
        setTitle("Wordle Game");
        //What happens when we hit the exit button
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }*/
    }
}
