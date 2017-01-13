/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.*;
import javax.swing.border.BevelBorder;

public class CryptoCross extends JFrame {

    private JFrame thisFrame;
    private Letter[][] ar_letters; //board letter array
    private Player player; //Player object for the player
    private Board gameBoard; //Board object for the game board
    private WordPilot wordPilot;
    private Integer int_maxAllowedWords; //The max number of words the player is
    //allowed to complete
    private Integer int_goalPoints; //The target/goal number of points to be attained
    //by the player.
    private Integer int_currentWordPoints;
    private ArrayList<Letter> currentWord;

    //Total allowed helps
    private Integer int_TotalDeleteRow = 3;
    private Integer int_TotalReorderRow = 3;
    private Integer int_TotalReorderColumn = 3;
    private Integer int_TotalReorderBoard = 5;
    private Integer int_TotalSwapLetter = 6;

    //Number of used helps
    private Integer int_UsedDeleteRow;
    private Integer int_UsedReorderRow;
    private Integer int_UsedReorderColumn;
    private Integer int_UsedReorderBoard;
    private Integer int_UsedSwapLetter;

    private Container contentPane;
    //JPanels
    private JPanel containerPanel; //Parent of all panels except for playerNamePanel, child of contentPane
    private JPanel leftPanel; //Child of containerPanel
    private JPanel rightPanel; //Child of containerPanel
    private JPanel boardPanel; //Child of leftPanel
    private JPanel helpPanel; //Child of rightPanel
    private JPanel playerNamePanel; //Child of contentPane
    //Rows of rightPanel
    private JPanel row1Panel;
    private JPanel row2Panel;
    private JPanel row3Panel;
    private JPanel row4Panel;
    private JPanel row5Panel;
    private JPanel row6Panel;
    private JPanel row7Panel;
    private JPanel row8Panel;
    private JPanel row9Panel;
    private JPanel row10Panel;
    //Menus
    private JMenuBar menuBar; //Menu Bar that will hold the menus
    private JMenu mainMenu; //Main menu containing all the main game functions
    private JMenu toolsMenu; //Secondary menu for misc functions
    //mainMenu Items
    private JMenuItem newGameMenuItem; //Start a new game
    private JMenuItem stopGameMenuItem; //Stop current game
    private JMenuItem playerInfoMenuItem; //Insert player info
    private JMenuItem helpSettingsMenuItem; //Open help settings
    private JMenuItem pickWordFileMenuItem; //Pick the wordlist file
    private JMenuItem exitMenuItem; //Exit application
    //toolsMenu Items
    private JMenuItem helpMenuItem; //Open game help
    private JMenuItem aboutMenuItem; //Open about infoa
    //letter JButtons
    private JButton[][] btnArray_letter;
    private JButton btn_deleteRow;
    private JButton btn_reorderRow;
    private JButton btn_reorderColumn;
    private JButton btn_reorderBoard;
    private JButton btn_swapLetters;
    private JButton btn_checkWord;
    //JTextFields
    //Help JTF
    private JTextField tf_deleteRow;
    private JTextField tf_reorderRow;
    private JTextField tf_reorderColumn;
    //Player Info JTF
    private JTextField tf_playerName;
    //JLabels
    private JLabel lb_playerName;
    private JLabel lb_deleteRow;
    private JLabel lb_reorderRow;
    private JLabel lb_reorderColumn;
    private JLabel lb_reorderBoard;
    private JLabel lb_swapLetters;
    private JLabel lb1_goalPoints;
    private JLabel lb2_goalPoints;
    private JLabel lb1_totalPoints;
    private JLabel lb2_totalPoints;
    private JLabel lb1_wordPoints;
    private JLabel lb2_wordPoints;
    private JLabel lb1_wordsFound;
    private JLabel lb2_wordsFound;
    private JLabel lb_foundAword;

    //Constructor
    public CryptoCross() {

        wordPilot = new WordPilot(ar_letters);

        thisFrame = this;

        currentWord = new ArrayList<>();

        //Initialize Player
        initializePlayer();

        Integer int_boardSize = inputBoardSize();
        if (int_boardSize == 0) {
            System.exit(0);
        }

        try {
            gameBoard = new Board(int_boardSize);
        } catch (UknownCharacterException ex) {
            Logger.getLogger(CryptoCross.class.getName()).log(Level.SEVERE, null, ex);
        }

        initializeGameValues(); //goal score & helps

        initializeGUI();

        checkHelps();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Set game LookAndFeel to Nimbus
                try {
                    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (Exception e) {
                    // Nimbus unavailable
                }
                new CryptoCross();
            }
        });
    }

    private void initializeGameValues() {
        int_currentWordPoints = 0;

        if (gameBoard.getBoardLength() == 5) {
            int_maxAllowedWords = 4;
            int_goalPoints = 200;
            int_TotalDeleteRow = 3;
            int_TotalReorderRow = 3;
            int_TotalReorderColumn = 3;
            int_TotalReorderBoard = 5;
            int_TotalSwapLetter = 6;
        } else if (gameBoard.getBoardLength() == 8) {
            int_maxAllowedWords = 6;
            int_goalPoints = 300;
            int_TotalDeleteRow = 4;
            int_TotalReorderRow = 4;
            int_TotalReorderColumn = 4;
            int_TotalReorderBoard = 7;
            int_TotalSwapLetter = 8;
        } else {
            int_maxAllowedWords = 8;
            int_goalPoints = 400;
            int_TotalDeleteRow = 5;
            int_TotalReorderRow = 5;
            int_TotalReorderColumn = 5;
            int_TotalReorderBoard = 8;
            int_TotalSwapLetter = 10;
        }

        int_UsedDeleteRow = 0;
        int_UsedReorderRow = 0;
        int_UsedReorderColumn = 0;
        int_UsedReorderBoard = 0;
        int_UsedSwapLetter = 0;
    }

    //Checks if any more helps are available and grays out accordingly
    private void checkHelps() {
        if (int_UsedDeleteRow == int_TotalDeleteRow) {
            btn_deleteRow.setEnabled(false);
            tf_deleteRow.setEnabled(false);
        }
        if (int_UsedReorderRow == int_TotalReorderRow) {
            btn_reorderRow.setEnabled(false);
            tf_reorderRow.setEnabled(false);
            lb_reorderRow.setEnabled(false);
        }
        if (int_UsedReorderColumn == int_TotalReorderColumn) {
            btn_reorderColumn.setEnabled(false);
            tf_reorderColumn.setEnabled(false);
            lb_reorderColumn.setEnabled(false);
        }
    }

    private void initializePlayer() {
        player = new Player();
        player.setPlayerName(inputPlayerInfo());

        if (player.getPlayerName() == null) {
            System.exit(0);
        }

        while (player.getPlayerName().trim().length() == 0) {
            JOptionPane.showMessageDialog(thisFrame,
                    "Το όνομα του παίκτη δεν μπορεί να είναι κενό",
                    "Σφάλμα εισαγωγής!",
                    JOptionPane.ERROR_MESSAGE);

            player.setPlayerName(inputPlayerInfo());
            if (player.getPlayerName() == null) {
                System.exit(0);
            }
        }

    }

    private String inputPlayerInfo() {
        String str_playerName = (String) JOptionPane.showInputDialog(
                thisFrame,
                "Παρακαλώ εισάγετε το όνομα του παίκτη:",
                "Καλωσήρθατε στο CryptoCross",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null);
        if (str_playerName == null) {
            return null;
        } else {
            return str_playerName;
        }
    }

    private Integer inputBoardSize() {
        Object[] options = {"Ταμπλό 5x5",
            "Ταμπλό 8x8",
            "Ταμπλό 10x10"};
        String str_boardSize = (String) JOptionPane.showInputDialog(
                thisFrame,
                "Παρακαλώ επιλέξτε μέγεθος ταμπλό παιχνιδιού",
                "Επιλογή μεγέθους ταμπλό",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        if (str_boardSize == null) {
            return 0;
        }

        if (str_boardSize.equals("Ταμπλό 5x5")) {
            return 5;
        } else if (str_boardSize.equals("Ταμπλό 8x8")) {
            return 8;
        } else if (str_boardSize.equals("Ταμπλό 10x10")) {
            return 10;
        }

        return 0;
    }

    //Initialization of GUI Components
    private void initializeGUI() {

        initializeMenuBar();

        contentPane = thisFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        containerPanel = new JPanel(new GridLayout(1, 2, 4, 4)); //1 row, 2 columns
        containerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        leftPanel = new JPanel(new BorderLayout());

        addGameBoardToGUI(); //Prints the game board

        btn_checkWord = new JButton("Έλεγχος λέξης");

        leftPanel.add(btn_checkWord, BorderLayout.AFTER_LAST_LINE);

        rightPanel = new JPanel(new GridLayout(11, 1, 2, 2)); //11 rows, 1 column

        helpPanel = new JPanel(new BorderLayout());
        helpPanel.add(new JLabel("Βοήθειες"), BorderLayout.EAST);

        rightPanel.add(helpPanel);

        //Help Buttons
        btn_deleteRow = new JButton("Διαγραφή Γραμμής");
        btn_reorderRow = new JButton("Αναδιάταξη Γραμμής");
        btn_reorderColumn = new JButton("Αναδιάταξη Στήλης");
        btn_reorderBoard = new JButton("Αναδιάταξη Ταμπλό");
        btn_swapLetters = new JButton("Εναλλαγή Γραμμάτων");

        //Help Text Fields
        tf_deleteRow = new JTextField("0");
        tf_reorderRow = new JTextField("0");
        tf_reorderColumn = new JTextField("0");

        //Help Labels
        lb_deleteRow = new JLabel(int_UsedDeleteRow + "/" + int_TotalDeleteRow);
        lb_reorderRow = new JLabel(int_UsedReorderRow + "/" + int_TotalReorderRow);
        lb_reorderColumn = new JLabel(int_UsedReorderColumn + "/" + int_TotalReorderColumn);
        lb_reorderBoard = new JLabel(int_UsedReorderBoard + "/" + int_TotalReorderBoard);
        lb_swapLetters = new JLabel(int_UsedSwapLetter + "/" + int_TotalSwapLetter);
        lb1_goalPoints = new JLabel("Στόχος:");
        lb2_goalPoints = new JLabel(Integer.toString(int_goalPoints));
        lb1_totalPoints = new JLabel("Συνολική Βαθμολογία:");
        lb2_totalPoints = new JLabel(Integer.toString(player.getPlayerScore()));
        lb1_wordPoints = new JLabel("Βαθμολογία Λέξης:");
        lb2_wordPoints = new JLabel(Integer.toString(int_currentWordPoints));
        lb1_wordsFound = new JLabel("Λέξεις που βρέθηκαν:");
        lb2_wordsFound = new JLabel(player.getCompletedWordsNum() + "/" + int_maxAllowedWords);
        lb_foundAword = new JLabel("");

        row1Panel = new JPanel(new BorderLayout());
        row2Panel = new JPanel(new BorderLayout());
        row3Panel = new JPanel(new BorderLayout());
        row4Panel = new JPanel(new BorderLayout());
        row5Panel = new JPanel(new BorderLayout());
        row6Panel = new JPanel(new BorderLayout());
        row7Panel = new JPanel(new BorderLayout());
        row8Panel = new JPanel(new BorderLayout());
        row9Panel = new JPanel(new BorderLayout());
        row10Panel = new JPanel(new BorderLayout());

        //Right Row1
        row1Panel.add(btn_deleteRow, BorderLayout.LINE_START);
        row1Panel.add(tf_deleteRow, BorderLayout.CENTER);
        row1Panel.add(lb_deleteRow, BorderLayout.LINE_END);

        rightPanel.add(row1Panel);

        //Right Row2
        row2Panel.add(btn_reorderRow, BorderLayout.LINE_START);
        row2Panel.add(tf_reorderRow, BorderLayout.CENTER);
        row2Panel.add(lb_reorderRow, BorderLayout.LINE_END);

        rightPanel.add(row2Panel);

        //Right Row3
        row3Panel.add(btn_reorderColumn, BorderLayout.LINE_START);
        row3Panel.add(tf_reorderColumn, BorderLayout.CENTER);
        row3Panel.add(lb_reorderColumn, BorderLayout.LINE_END);

        rightPanel.add(row3Panel);

        //Right Row4
        row4Panel.add(btn_reorderBoard, BorderLayout.LINE_START);
        row4Panel.add(lb_reorderBoard, BorderLayout.LINE_END);

        rightPanel.add(row4Panel);

        //Right Row5
        row5Panel.add(btn_swapLetters, BorderLayout.LINE_START);
        row5Panel.add(lb_swapLetters, BorderLayout.LINE_END);

        rightPanel.add(row5Panel);

        //Right Row6
        row6Panel.add(lb1_goalPoints, BorderLayout.LINE_START);
        row6Panel.add(lb2_goalPoints, BorderLayout.LINE_END);

        rightPanel.add(row6Panel);

        //Right Row7
        row7Panel.add(lb1_totalPoints, BorderLayout.LINE_START);
        row7Panel.add(lb2_totalPoints, BorderLayout.LINE_END);

        rightPanel.add(row7Panel);

        //Right Row8
        row8Panel.add(lb1_wordPoints, BorderLayout.LINE_START);
        row8Panel.add(lb2_wordPoints, BorderLayout.LINE_END);

        rightPanel.add(row8Panel);

        //Right Row9
        row9Panel.add(lb1_wordsFound, BorderLayout.LINE_START);
        row9Panel.add(lb2_wordsFound, BorderLayout.LINE_END);

        rightPanel.add(row9Panel);

        //Right Row10
        row10Panel.add(lb_foundAword, BorderLayout.LINE_START);

        rightPanel.add(row10Panel);

        containerPanel.add(leftPanel);
        containerPanel.add(rightPanel);
        contentPane.add(containerPanel);

        lb_playerName = new JLabel("Παίκτης: " + player.getPlayerName());
        lb_playerName.setFont(new Font("Arial Black", Font.BOLD, 13));

        playerNamePanel = new JPanel();
        playerNamePanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        contentPane.add(playerNamePanel, BorderLayout.SOUTH);
        playerNamePanel.setPreferredSize(new Dimension(thisFrame.getWidth(), 21));
        playerNamePanel.setLayout(new BoxLayout(playerNamePanel, BoxLayout.X_AXIS));
        lb_playerName.setHorizontalAlignment(SwingConstants.LEFT);
        playerNamePanel.add(lb_playerName);

        contentPane.add(containerPanel, BorderLayout.NORTH);

        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("CryptoCross");  // 
//        if (gameBoard.getBoardLength() == 5) {
//            setSize(650, 350);
//        }
        setLocationRelativeTo(null); //
        setVisible(true);    // 
        setResizable(false);
    }

    private void addGameBoardToGUI() {
        boardPanel = new JPanel(new GridLayout(gameBoard.getBoardLength(), gameBoard.getBoardLength(), 2, 2));
        ar_letters = gameBoard.getBoardArray();

        btnArray_letter = new JButton[gameBoard.getBoardLength()][gameBoard.getBoardLength()];

        for (int i = 0; i < gameBoard.getBoardLength(); i++) {
            for (int j = 0; j < gameBoard.getBoardLength(); j++) {
                btnArray_letter[i][j] = new JButton();

                String str_letterBtnTxt = ar_letters[i][j].getLetterChar()
                        + " " + ar_letters[i][j].getPoints();

                //Only some letters icons have been made
                try {
                    if (ar_letters[i][j].getLetterChar() == 'Α') {

                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Α.png"))));

                    } else if (ar_letters[i][j].getLetterChar() == 'Β') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Β.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Γ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Γ.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Δ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Δ.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Ε') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Ε.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Ζ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Ζ.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Η') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Η.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == 'Θ') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Θ.png"))));
                    } else if (ar_letters[i][j].getLetterChar() == '?') {
                        btnArray_letter[i][j].setIcon(new ImageIcon(
                                ImageIO.read(getClass().getResource("/images/Balandeur.png"))));
                    }
                } catch (IOException ex) {
                    Logger.getLogger(CryptoCross.class.getName()).log(Level.SEVERE, null, ex);
                }

                Color tempColor = ar_letters[i][j].getColor();
                btnArray_letter[i][j].setBackground(tempColor);
                if (btnArray_letter[i][j].getIcon() == null) {
                    btnArray_letter[i][j].setText(str_letterBtnTxt);
                }

                btnArray_letter[i][j].setBorder(BorderFactory.createEmptyBorder());
                btnArray_letter[i][j].setPreferredSize(new Dimension(48, 48));

                Letter tempLetter = ar_letters[i][j];
                Integer tempXpos = currentWord.get(currentWord.size() - 1).getXcoord();
                Integer tempYpos = currentWord.get(currentWord.size() - 1).getYcoord();

                btnArray_letter[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (((JButton) e.getSource()).getBackground().equals(Color.YELLOW)) {

//                            if (wordPilot.isNeighbour(i, j, tempXpos, tempYpos)) {
//                                ((JButton) e.getSource()).setBackground(tempColor);
//                            }
                        } else {
                            ((JButton) e.getSource()).setBackground(Color.YELLOW);

                            currentWord.add(tempLetter);
                        }
                    }
                });
                boardPanel.add(btnArray_letter[i][j]);
            }
        }
        leftPanel.add(boardPanel, BorderLayout.NORTH);
    }

    private void initializeMenuBar() {
        menuBar = new javax.swing.JMenuBar();

        //MainMenu initialization
        mainMenu = new javax.swing.JMenu();
        newGameMenuItem = new javax.swing.JMenuItem();
        stopGameMenuItem = new javax.swing.JMenuItem();
        playerInfoMenuItem = new javax.swing.JMenuItem();
        helpSettingsMenuItem = new javax.swing.JMenuItem();
        pickWordFileMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();

        mainMenu.setText("Μενού");

        newGameMenuItem.setText("Νέο Παιχνίδι");
        newGameMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameMenuItemActionPerformed(evt);
            }
        });
        mainMenu.add(newGameMenuItem);

        stopGameMenuItem.setText("Ακύρωση/Τερματισμός Παιχνιδιού");
        stopGameMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopGameMenuItemActionPerformed(evt);
            }
        });
        mainMenu.add(stopGameMenuItem);

        playerInfoMenuItem.setText("Εισαγωγή στοιχείων παίχτη");
        playerInfoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerInfoMenuItemActionPerformed(evt);
            }
        });
        mainMenu.add(playerInfoMenuItem);

        helpSettingsMenuItem.setText("Ρυθμίσεις βοηθειών");
        helpSettingsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpSettingsMenuItemActionPerformed(evt);
            }
        });
        mainMenu.add(helpSettingsMenuItem);

        pickWordFileMenuItem.setText("Αναζήτηση αρχείου λέξεων");
        pickWordFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pickWordFileMenuItemActionPerformed(evt);
            }
        });
        mainMenu.add(pickWordFileMenuItem);

        exitMenuItem.setText("Έξοδος");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        mainMenu.add(exitMenuItem);

        //ToolsMenu initialization
        toolsMenu = new javax.swing.JMenu();
        helpMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        toolsMenu.setText("Εργαλεία");

        helpMenuItem.setText("Βοήθεια");
        helpMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenuItemActionPerformed(evt);
            }
        });
        toolsMenu.add(helpMenuItem);

        aboutMenuItem.setText("About...");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        toolsMenu.add(aboutMenuItem);

        menuBar.add(mainMenu);
        menuBar.add(toolsMenu);

        setJMenuBar(menuBar);
    }

    private void newGameMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        thisFrame.dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
        new CryptoCross();
    }

    private void stopGameMenuItemActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void playerInfoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        player.setPlayerName(inputPlayerInfo());
        lb_playerName.setText("Παίκτης: " + player.getPlayerName());

    }

    private void helpSettingsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void pickWordFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        //Create a file chooser
        final JFileChooser fileChooser = new JFileChooser();

        int returnVal = fileChooser.showOpenDialog(new JButton());

    }

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        thisFrame.dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
        System.exit(0);
    }

    private void helpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(thisFrame,
                "Συντελεστές:\nΧάρης Ιωαννίκιος Καώνης icsd10069\nΓεώργιος Μουστάκας icsd11102", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    //Additional listeners
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == openButton) {
//            int returnVal = ((JFileChooser) e.getSource()).showOpenDialog();
//
//            if (returnVal == JFileChooser.APPROVE_OPTION) {
//                File file = ((JFileChooser) e.getSource()).getSelectedFile();
//                //Logger.getLogger(CryptoCross.class.getName()).append("Opening: " + file.getName() + "." + "\n");
//            } else {
//                //Logger.getLogger(CryptoCross.class.getName()).append("Open command cancelled by user." + "\n");
//            }
//        }
        if (e.getSource() == btn_checkWord) {

        } else if (e.getSource() == btn_checkWord) {
            gameBoard.checkWordValidity(currentWord);
        } else if (e.getSource() == btn_deleteRow) {

        } else if (e.getSource() == btn_reorderRow) {

        } else if (e.getSource() == btn_reorderColumn) {

        } else if (e.getSource() == btn_reorderBoard) {

        } else if (e.getSource() == btn_swapLetters) {

        }
    }

}
