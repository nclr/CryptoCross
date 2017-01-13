/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.*;
import javax.swing.border.BevelBorder;

public class CryptoCross extends JFrame {

    private JFrame thisFrame;
    private Letter[][] ar_letters;
    private Player player; //Player object for the player
    private Board gameBoard; //Board object for the game board
    private GameEngine gameEngine;

    private Container contentPane;
    //JPanels
    private JPanel containerPanel; //Parent of all panels except for playerNamePanel, child of contentPane
    private JPanel leftPanel; //Child of containerPanel
    private JPanel rightPanel; //Child of containerPanel
    private JPanel boardPanel; //Child of leftPanel
    private JPanel helpPanel; //Child of rightPanel
    private JPanel playerNamePanel; //Child of contentPane
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
    private JButton btn_shuffleRow;
    private JButton btn_shuffleColumn;
    private JButton btn_shuffleBoard;
    private JButton btn_swapLetters;
    private JButton btn_checkWord;
    //JTextFields
    //Help JTF
    private JTextField tf_deleteRow;
    private JTextField tf_shuffleRow;
    private JTextField tf_shuffleColumn;
    //Player Info JTF
    private JTextField tf_playerName;
    //JLabels
    private JLabel lb_playerName;

    //Constructor
    public CryptoCross() {

        thisFrame = this;

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

        gameEngine = new GameEngine();

        initializeGUI();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Χρήση του θέματος εμφάνισης (LookAndFeel) Nimbus
                try {
                    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (Exception e) {
                    // If Nimbus is not available, you can set the GUI to another look and feel.
                }
                new CryptoCross();
            }
        });
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

        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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

                btnArray_letter[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (((JButton) e.getSource()).getBackground().equals(Color.YELLOW)) {
                            ((JButton) e.getSource()).setBackground(tempColor);
                        } else {
                            ((JButton) e.getSource()).setBackground(Color.YELLOW);
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
        System.exit(0);
    }

    private void helpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(thisFrame,
                "Συντελεστές:\nΧάρης Ιωαννίκιος Καώνης icsd10069\nΓεώργιος Μουστάκας icsd11102", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    //File Chooser Listener
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
    }

}
