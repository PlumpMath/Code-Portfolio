/*
 * This program counts both the # of words in a text file and its location within
 * a text file. It allows the user to input as many text files as they like and
 * the user can choose to display a list of all the words found thus far, what file
 * the word is located in, and the location of each word. Users can also search for
 * a specific word, remove a text file and its associated word from the list.
 * 
 * This program makes extensive use of hashmaps, hashsets, and iterators.
 */

package com.kurtronaldmueller.hashmaps;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * Handles the GUI elements of the project.
 */
@SuppressWarnings("serial")
public class FileIndexGUI extends JFrame {

    /*
     * FILE INDEX
     */
    
    private FileIndex fileIndex;
    
    /*
     * GUI ELEMENTS
     */
    
    private JMenuBar menuBar;          // the menu bar used to access each operation
    private JMenu fileMenu;            // the file menu option
    private JMenuItem addFileItem;     // the file->addFile button
    private JMenuItem showFileItem;    // the file->showFile button
    private JMenuItem removeFileItem;  // the file->removeFile button
    private JMenuItem listIndexItem;   // the file->listIndex button
    private JMenuItem indexSearchItem; // the file->indexSearch button
    
    private JMenu exitMenu;            // the exit menu option
    private JMenuItem exitProgramItem; // the exit->exitPRogram button
    
    private JLabel mainContentLabel;  // the main content label that stores all the output
    private JScrollPane mainScrollPane; // the scrollpane used to handle overlfowing information
    
    private static final int initialWindowWidth  = 800; // the initial window width 
    private static final int initialWindowHeight = 500; // the initial window height
    
    
    /*
     * STRINGS THAT HANDLE BUTTON INPUT
     */
    
    private static final String ADD_FILE     = "Add File to Index";
    private static final String SHOW_FILES   = "Show Files Indexed";
    private static final String REMOVE_FILE  = "Remove Files from Index";
    private static final String LIST_INDEX   = "List Index of Words";
    private static final String SEARCH_INDEX = "Search Index of Words";
    private static final String EXIT_PROGRAM = "Exit the program";
    
    
    /* 
     * HTML Codes 
     */
    
    private static final String htmlParagraphPrefix = "<html><body style='width: " + initialWindowWidth + "px;'><p>";
    private static final String htmlParagraphSuffix = "</p><body></html>";
    private static final String tab = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; // 5 spaces
    
    
    /**
     * The main program method. Creates the gui window and assigns it behaviors.
     * 
     * @param args Default args.
     */
    public static void main( String[] args ) {
        
        // create a new gui windows
        FileIndexGUI fileIndexGui = new FileIndexGUI();
        
        // change its behaviors
        fileIndexGui.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        fileIndexGui.setTitle(  "File Index Program" );
        fileIndexGui.setSize( initialWindowWidth, initialWindowHeight );
        fileIndexGui.setLocationRelativeTo( null );
        fileIndexGui.setVisible( true );
    }
    
    /**
     * Default constructor. Creates gui menu elements.
     */
    public FileIndexGUI() {
        
        // create a new file index
        fileIndex = new FileIndex(); 
        
        /*
         * CREATE MENU BAR 
         */
        
        // create the menu bar
        menuBar = new JMenuBar();
        
        // create the file menu button
        fileMenu = new JMenu( "File" );
        fileMenu.setMnemonic( KeyEvent.VK_F );
        menuBar.add( fileMenu );
        
        // create "File"->"Add File to Index" button
        addFileItem = new JMenuItem( ADD_FILE );
        addFileItem.setName( "addfile" );
        addFileItem.setMnemonic( KeyEvent.VK_A );
        addFileItem.addActionListener( new MenuActionListener() );
        fileMenu.add( addFileItem );
        
        // create "File"->"Show Files Indexed" button
        showFileItem = new JMenuItem( SHOW_FILES );
        showFileItem.setName( "showfiles" );
        showFileItem.setMnemonic( KeyEvent.VK_S );
        showFileItem.addActionListener( new MenuActionListener() );
        fileMenu.add( showFileItem );
        
        // create "File"->"Remove Files from Index" button
        removeFileItem = new JMenuItem( REMOVE_FILE );
        removeFileItem.setName( "removefile" );
        removeFileItem.setMnemonic( KeyEvent.VK_R );
        removeFileItem.addActionListener( new MenuActionListener() );
        fileMenu.add( removeFileItem );
        
        // create "File"->"List Index of Words" button
        listIndexItem = new JMenuItem( LIST_INDEX );
        listIndexItem.setName( "listindex" );
        listIndexItem.setMnemonic( KeyEvent.VK_L );
        listIndexItem.addActionListener( new MenuActionListener() );
        fileMenu.add( listIndexItem );
        
        // create "File"->"Search Index of Words"
        indexSearchItem = new JMenuItem( SEARCH_INDEX );
        indexSearchItem.setName( "searchindex" );
        indexSearchItem.setMnemonic( KeyEvent.VK_I );
        indexSearchItem.addActionListener( new MenuActionListener() );
        fileMenu.add( indexSearchItem );
        
        
        // create exit menu button
        exitMenu = new JMenu( EXIT_PROGRAM );
        exitMenu.setMnemonic( KeyEvent.VK_E );
        exitMenu.addActionListener( new MenuActionListener() );
        menuBar.add( exitMenu );
        
        // create "exit"->"Exit" button
        exitProgramItem = new JMenuItem( EXIT_PROGRAM );
        exitProgramItem.setMnemonic( KeyEvent.VK_S );
        exitProgramItem.addActionListener( new MenuActionListener() );
        exitMenu.add( exitProgramItem );
        
        // set the menu bar for the main window
        this.setJMenuBar( menuBar );
        
        /*
         * CREATE MAIN CONTENT PANE 
         */
        
        // set welcome message
        mainContentLabel = new JLabel( htmlParagraphPrefix + tab
        		                     + "Welcome. This program allows you to enter words from a file into an index.<br/>"
        		                     + tab
        		                     + "Choose from the file menu to begin."
        		                     + htmlParagraphSuffix );
        
        // create a new scroll pane, and add it to the main gui window
        mainScrollPane = new JScrollPane( mainContentLabel );
        mainScrollPane.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        mainScrollPane.setPreferredSize( new Dimension( 300, 300 ) );
        
        this.add( mainScrollPane, BorderLayout.CENTER );
        
        // create a window lister so if the user decides to exit the program by clicking the 'x' button,
        // the program closes correctly
        this.addWindowListener(new WindowAdapter()
        {
           public void windowClosing( WindowEvent e )
           {
             dispose();
             System.exit(0); //calling the method is a must
           }
        });
        
    }

    /**
     * Class handles button inputs (i.e. clicks) and calls the necessary action.
     */
    private class MenuActionListener implements ActionListener {

        // create the first 
        public static final String PATH = "image/";  // path for icon image files

        // Icon image files
        public static final String ADD_FILE_ICON     = "addFile80x80.png";     // add file icon
        public static final String REMOVE_FILE_ICON  = "removeFile80x80.png";  // remove file icon
        public static final String INDEX_SEARCH_ICON = "searchIndex80x80.png"; // search index icon
        
        @Override
        
        /**
         * A button has been clicked. 
         */
        public void actionPerformed( ActionEvent e ) {
            
            // get the action string
            String action = e.getActionCommand();
            
            // the user has chosen to add a file
            if( action.equalsIgnoreCase( ADD_FILE ) ) {
                addFileToIndex();
            }
            // the user has chosen to show files parsed
            else if( action.equalsIgnoreCase( SHOW_FILES ) ) {
                showFiles();
            }
            // the user has chosen to remove a file
            else if( action.equalsIgnoreCase( REMOVE_FILE ) ) {
                removeFile();
            }
            // the user has chosen to list the index
            else if( action.equalsIgnoreCase( LIST_INDEX ) ) {
                listIndex();
            }
            // the user has chosen to search the index
            else if( action.equals( SEARCH_INDEX ) ) {
                searchIndex();
            }
            // the user has chosen to exit the program
            else if( action.equalsIgnoreCase( EXIT_PROGRAM ) ) {
                exitProgram();
            }
        }
        
        /**
         * Add a file to the index.
         */
		private void addFileToIndex() {
            
            JFrame frame = new JFrame();
            
            /*
             * GET THE FILE TO ADD 
             */
            
            // create a dialog box that alllows the user to enter in a file to add to the index
            String fileName = (String) JOptionPane.showInputDialog( frame, 
                    "Enter NAME of the file to index:",
                    "Add File To Index",
                    JOptionPane.OK_OPTION,
                    new ImageIcon( PATH + ADD_FILE_ICON ),
                    null, 
                    "");
            
            /*
             * PROCESS THE REQUEST
             */
            
            // if the user clicks cancel or click ok without entering any input
            // 1. cancel the operation
            // 2. exit the method
            if( fileName == null || fileName.length() == 0 ) {
                JOptionPane.showMessageDialog( frame, "Canceled 'Add File' operation." );
                return;
            }
            
            // add the file to the file index and let the user know the operation was successful
            if( fileIndex.addFile( fileName ) ) {
                mainContentLabel.setText( htmlParagraphPrefix + tab 
                		                + "File \"" + fileName + "\" added to the file index." 
                		                + htmlParagraphSuffix );
            }
            // the the user know the operation was unsuccessful
            else {
                mainContentLabel.setText(  htmlParagraphPrefix + tab  
                		                + "File \"" + fileName + "\" was NOT added to the file index." 
                		                + htmlParagraphSuffix );
            }
        }
        
		/**
		 * Show all files in the file index.
		 */
        private void showFiles() {
            
            // if at least 1 file has been parsed, show to the main panel what files have been parsed
            if( fileIndex.numOfFilesParsed() != 0 ) {
                mainContentLabel.setText( htmlParagraphPrefix
                        + tab
                        + "List of Indexed Files:" 
                        + "<br/>"
                        + fileIndex.showFiles( "<br/>" + tab ) 
                        + htmlParagraphSuffix );                
            }
            // tell the user that there are no files to list
            else {
                mainContentLabel.setText( htmlParagraphPrefix
                        + tab
                        + "There are no files to list."
                        + htmlParagraphSuffix );
                
                // display an error dialog box
                JOptionPane.showMessageDialog( new JFrame(), "ERROR: There are no files to list." );
            }
        }
        
        
        /**
         * Remove a file from the index.
         */
        private void removeFile() {
            
            JFrame frame = new JFrame();
            
            // if there is at least one file parsed
            if( fileIndex.numOfFilesParsed() != 0 ) {
                
                // get the file name to remove
                String fileName = (String) JOptionPane.showInputDialog( frame, 
                        "Enter NAME of the file to remove:",
                        "Remove File From Index",
                        JOptionPane.OK_OPTION,
                        new ImageIcon( PATH + REMOVE_FILE_ICON ),
                        null, 
                        "");
                
                // if user has clicked cancel or clicked ok without entering a filename,
                // show an error dialog
                if( fileName == null || fileName.length() == 0 ) {
                    JOptionPane.showMessageDialog( frame, "Canceled 'Remove File' operation." );
                    return;
                }
                
                // if the file name is able to e removed, output the results to the main panel
                if( fileIndex.removeFile( fileName ) ) {
                    mainContentLabel.setText( htmlParagraphPrefix + tab
                            + "File \"" + fileName + "\" was removed from the file index." 
                            + htmlParagraphSuffix );
                }
                else {
                    // output the fact that were no results
                    mainContentLabel.setText( htmlParagraphPrefix + tab
                            + "File \"" + fileName + "\" was NOT removed from the file index." 
                            + htmlParagraphSuffix );
                }                
            }
            // no files are currently associated with the file index, so show an error dialog informing the user of this
            else {
                JOptionPane.showMessageDialog( frame, "ERROR: There are no files to remove." );
                mainContentLabel.setText( htmlParagraphPrefix + tab + "ERROR: There are no files to remove." + htmlParagraphSuffix );
            }
        }
        
        /**
         * Search the index for a specified word.
         */
        private void searchIndex() {
            
            // if at least one file has been parsed
            if( fileIndex.numOfFilesParsed() != 0 ) {
                
                // show an input dialog box and get the file name from user
                String searchWord = (String) JOptionPane.showInputDialog( new JFrame(), 
                        "Enter word to search for:",
                        "Word to Search for",
                        JOptionPane.OK_OPTION,
                        new ImageIcon( PATH + INDEX_SEARCH_ICON ),
                        null, 
                        "");
                
                // if the user has clicked the cancel button or clicked the ok button without entering in a search term
                if( searchWord == null || searchWord.length() == 0 ) {
                    
                    // show an error dialog box
                    JOptionPane.showMessageDialog( new JFrame(), "Canceled 'Search For' operation." );
                    return;
                }
                
                // get the search results
                String searchResults = fileIndex.searchFor( searchWord, "<br/>" + tab );
                
                // there is at least one search result
                if( searchResults.length() > 0  ) {
                    
                    // output the resutls to the screen
                    mainContentLabel.setText( htmlParagraphPrefix + tab + searchResults + htmlParagraphSuffix );
                }
                else {
                    
                    // let the user no search terms were found
                    JOptionPane.showMessageDialog( new JFrame(), searchWord + " was not found." );
                    mainContentLabel.setText( htmlParagraphPrefix + tab + searchWord + " was not found." + htmlParagraphSuffix );
                }
                    
            }
            // no files have been added to the index so it's not possible to search for words
            else {
                JOptionPane.showMessageDialog( new JFrame(), "ERROR: There are no words to search for because the file index is empty." );
                mainContentLabel.setText( htmlParagraphPrefix + tab + "ERROR: There are no words to search for because the file index is empty." + htmlParagraphSuffix );
            }
        }
        
        /**
         * List the content of index: all the words, all the files associated with each word, and every line number associated with each file.
         */
        private void listIndex() {
            
            // if the file index has at least one word entry, output the contents of the index to the screen
            if( fileIndex.size() != 0 ) {
                mainContentLabel.setText( htmlParagraphPrefix + tab + fileIndex.toString( "<br/>" + tab ) + htmlParagraphSuffix );
            }
            // let the user know there aren't any more files to display
            else {
                mainContentLabel.setText( htmlParagraphPrefix + tab + "There aren't any words or files to display." + htmlParagraphSuffix );
            }
        }
        
        // the user has chosen to exit the program
        private void exitProgram() {
    		// Display message to the user and terminate program
    		System.exit( 0 );	
		}
    }
}