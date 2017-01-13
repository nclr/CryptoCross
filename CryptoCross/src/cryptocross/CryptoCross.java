/*
 * @author Charis Ioannikios Kaonis 321/2010069
 * @author Georgios Moustakas 321/2011102
 * @project CryptoCross (Κρυπτόλεξο) 2016
 */
package cryptocross;

import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.*;

public class CryptoCross extends JFrame {

    private Container contentPane;
    //JPanels
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel boardPanel;
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
    private JButton letterButton;
    private JButton checkWordButton;

    public CryptoCross() {

        //Initialization of GUI Components
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


        //JPanel mainPanel = new JPanel(new GridLayout(1, 2, 0, 0));
        contentPane = this.getContentPane();

        contentPane.setLayout(new GridLayout(1, 2, 0, 0));

        leftPanel = new JPanel(new FlowLayout());
        
        boardPanel = new JPanel(new GridLayout(5, 5, 2, 2)); //TODO: GENERATE DYNAMICALLY
        
        letterButton = new JButton("0"); 
        checkWordButton = new JButton("Έλεγχος λέξης"); 
        
        for(int i=0;i<5;i++) {
            for (int j=0;j<5;j++) {
                boardPanel.add(new JButton("0"));
            }
        }
        
        //boardPanel.add(new JButton("Button"));

        leftPanel.add(boardPanel);
        leftPanel.add(checkWordButton);

        rightPanel = new JPanel(new GridLayout(11, 1, 2, 2));

        rightPanel.add(new JLabel("Βοήθειες"));

        contentPane.add(leftPanel);
        contentPane.add(rightPanel);

        pack();

        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("CryptoCross");  // 
        setSize(550, 450);   // 
        setVisible(true);    // 
        setResizable(false);
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
        Board newBoard = new Board(5);
    }

    private void newGameMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void stopGameMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void playerInfoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void helpSettingsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void pickWordFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void helpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

}
