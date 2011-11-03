/* Homework:       12
 * Chapter:        17
 * Project:         2
 * Page:         1010
 * Filename:     TicTacToe.java
 * @author:      Kurt Mueller
 * Date:         05/11/2011
 * 
 * Problem Statement: This is a Swing GUI for a two-player 3x3 
 * tic-tac-toe game. It follows the specification as provided in the
 * book to create a gameboard with a 3x3 grid of buttons to keep 
 * track of who moved where and a label at the top of the program 
 * to keep track of whose turn it is and well as the outcome of the
 * game. 
 *
 * Overall Plan
 * A. Create & initialize the main window.
 *    1. Set the title.
 *    2. Set the window size.
 *    3. Set other window behaviors.
 * B. Create gui elements.
 *    1. Create a label to output whose turn it is as well as the 
 *       outcome of the game.
 *    2. Create a panel that will hold the button grid.
 *    3. Create a 2-d array at will hold the game buttons.
 * C. In the default constructor:
 *    1. Initialize each of the gui elements.
 *    2. For the JButton array, go from row to row, left to right:
 *       a. Initialize a JButton with a blank label.
 *       b. Add it to the game board panel.
 *       c. Hook on an action listener to the button that also
 *          sends the listener its coordinates within the array.
 *    3. Add the turn label and game board panel to the main window.
 * D. In the Game Board Button Listener class.
 *    1. Whenever a class is initialized, take in and store the
 *       relevant button's location within the array.
 *    2. For whomever's turn:
 *       a. Set the text of the button just pressed to either 'x' or
 *          'o' (based on whose turn it is)
 *       b. Set the turn label and turn holder to the opposing
 *          player's turn.
 *    3. Disable the game board button that was just clicked.
 *    4. Increment the moves counter.
 *    5. Once the moves counter is greater than or equal to 5 but
 *       less than or equal to 8:
 *       a. Check to see if there's a winner. 
 *          i. First, check the rows of the game board.
 *             - Create a counter to keep track of how many pieces in
 *               a row are the same.
 *             - In a nested for loop
 *               * go through each cell of each row
 *               * compare the button just clicked to the button
 *                 labels in the row.
 *               * For each button label that matches the button just
 *                 clicked, increment the counter by one.
 *               * If at the end of the row, the tic tac toe counter
 *                 has reached 3, return true, indicating that there
 *                 is a winner.
 *               * Else, reset the counter & go to the next row.
 *             - If there is no winner, return false.
 *         ii. Check the columns of the game board using the same
 *             logic above.
 *        iii. Check the diagonals of the game board.
 *             - Instead of a nested for loop, only one for loop
 *               is needed for each diagonal.
 *             - For the diagonal that goes from bottom left to top
 *               right, use a second counter that increments while
 *               the primary counter decrements
 *       b. If there is a winner:
 *          i. set the turn label to display whoever won
 *         ii. disable all the game board pieces
 *        iii. return true, indicating that there is a winner
 *    6. If there is no winner & the moves counter equals 9, set the
 *       turn label to indicate that there is a draw.
 *       
 * Classes Needed:
 * Main       - TicTacToe
 * GUI        - BorderLayout, GridLayout, JButton, JFrame, JLabel,
 *              JPanel 
 * Processing - ActionEvent, ActionListener
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TicTacToe extends JFrame
{
    public static void main(String[] args)
    {
        // initialize & display the main window
        TicTacToe frame = new TicTacToe();
        frame.setTitle("Tic Tac Toe");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
    
    JLabel whoseTurn; // displays the current turn as well as the
                      // outcome of the game (who won or if the game
                      // was a draw)
    
    JPanel gameBoard; // panel that will hold the game board
    JButton[][] pieces; // array of JButtons that will be game board
    
    boolean xTurn = true; // keeps track of whose turn it is
    int moves = 0; // keeps track of the number of moves so far
    
    public TicTacToe() 
    {
        // initialize the gui elements
        whoseTurn = new JLabel("X's turn");
        gameBoard = new JPanel(new GridLayout(3, 3, 3, 3));
        pieces    = new JButton[3][3];
        
        // set the text for each gameboard piece, add it to the 
        // grid layout, and add a button click handler
        for(int i=0; i < 3; i++)
            for(int j=0; j < 3; j++)
            {
                pieces[i][j] = new JButton(" ");
                gameBoard.add(pieces[i][j]);
                pieces[i][j].addActionListener(
                                  new GameBoardButtonListener(i, j));
            }
        
        // add label & gameboard to the main window
        this.add(whoseTurn, BorderLayout.NORTH);
        this.add(gameBoard, BorderLayout.CENTER);
    }
    
    /**
     * Class used to listen for button clicks. 
     * 
     * @author kurtronaldmueller
     */
    private class GameBoardButtonListener implements ActionListener
    {
        // holds the array's x & y coordinates of the button
        private int x, y;
        
        /**
         * Stores the coordinates of the specific JButton in the
         * array.
         * 
         * @param i The x-coordinate.
         * @param j The y-coordinate.
         */
        public GameBoardButtonListener(int i, int j)
        {
            this.x = i;
            this.y = j;
        }
        
        /**
         * The class used to perform an action when a button is
         * pressed.
         * 
         * @param arg0 The gui element doing the action. 
         */
        public void actionPerformed(ActionEvent arg0)
        {
            if(xTurn == true) // X's Turn
            {
                // set label of appropriate button to 'X'
                // set the JLabel to O's Turn
                // set the x's turn to false
                pieces[x][y].setText("X"); 
                whoseTurn.setText("O's Turn");
                xTurn = false;
            }
            else // O's Turn
            {
                pieces[x][y].setText("O");
                whoseTurn.setText("X's Turn");
                xTurn = true;
            }
            
            // when the button is clicked, disable it so it can't be
            // clicked again
            pieces[x][y].setEnabled(false);
            moves++; // increase the moves count every time a button
                     // is pressed
            
            // only after five moves can there be a winner
            if(moves >= 5 && moves <= 8) 
            {
                // check to see if there's a winner
                checkWinner();
            }
            else if(moves == 9 && !checkWinner())
            {
                // all the buttons have been pressed & there is no
                // determined winner
                whoseTurn.setText("No winner, only a tie.");
            }
        }
        
        /**
         * Checks the board to see if there's a winner.
         * 
         * @return True if there's a winner. False if there is not.
         */
        public boolean checkWinner()
        {
            // check the game board's rows
            if(checkRows())
            { 
                whoseTurn.setText(pieces[x][y].getText() + 
                                                  " is the winner!");
                disableAllPieces(); // if there is a winner, disable
                                    // all game board pieces
                return true;
            }
            
            // check the game board's columns
            else if(checkColumns())
            {
                whoseTurn.setText(pieces[x][y].getText() + 
                                                  " is the winner!");
                disableAllPieces();
                return true;
            }
            
            // check the game board's diagonals
            else if(checkDiagonals())
            {
                whoseTurn.setText(pieces[x][y].getText() + 
                                                  " is the winner!");
                disableAllPieces();
                return true;
            }
            
            return false;
        }
        
        /**
         * Checks the rows of the board to see if the current player
         * has formed a tic tac toe.
         * 
         * @return True if there is a winner. False if there is not.
         */
        private boolean checkRows()
        {
            int ticTacToe = 0; // counts how many pieces in a row the
                               // current player has
            
            // a double for loop is used to check the cells in each
            // row
            for(int i=0; i < pieces.length; i++)
            {
                for(int j=0; j < pieces[0].length; j++ )
                    if(pieces[i][j].getText().equalsIgnoreCase(
                                             pieces[x][y].getText()))
                        ticTacToe++; // if the piece being checked is
                                     // the current button being,
                                     // increment the counter

                // if the counter has reached 3, there is a winner
                if(ticTacToe == 3)
                    return true; 
                else
                    ticTacToe = 0;
            }
            
            // if the counter has not reached 3, then there is no
            // winner
            return false;
        }
        
        /**
         * Checks the columns of the board to see if the current
         * player has formed a tic tac toe.
         * 
         * @return True if there is a winner. False if there is not.
         */
        private boolean checkColumns()
        {
            int ticTacToe = 0; 
            
            // i & j are switched!
            for(int j=0; j < pieces.length; j++)
            {
                for(int i=0; i < pieces[0].length; i++)
                    if(pieces[i][j].getText().equalsIgnoreCase(
                                             pieces[x][y].getText()))
                        ticTacToe++;
                    
                if(ticTacToe == 3)
                    return true; 
                else
                    ticTacToe = 0;
            }
            
            return false;
        }
        
        /**
         * Checks the rows of the board to see if the current player
         * has formed a tic tac toe.
         * 
         * @return True if there is a winner. False if htere is not.
         */
        private boolean checkDiagonals()
        {
            int ticTacToe = 0; // counts how many pieces in a row the
                               // current player has
            
            // this loop goes from upper left to lower right
            for(int i=0; i < pieces.length; i++) 
                if(pieces[i][i].getText().equalsIgnoreCase(
                                             pieces[x][y].getText()))
                   ticTacToe++;
            
            if(ticTacToe == 3)
                return true; 
            else
                ticTacToe = 0;
            
            
            // this loop uses two variables to go from bottom left
            // to upper right
            int j = 0;
            for(int i=pieces[0].length-1; i >= 0; i--)
            {
                if(pieces[i][j].getText().equalsIgnoreCase(pieces[x][y].getText()))
                   ticTacToe++;
                
                j++;
            }
            
            if(ticTacToe == 3)
                return true; 
            else
                ticTacToe = 0;
            
            return false;
        }
        
        /**
         * Disables all the pieces on the gameboard.  This is used
         * when it is determined that there is a winner and the game
         * is over.
         */
        private void disableAllPieces()
        {
            for(int i=0; i < pieces.length; i++)
                for(int j=0; j < pieces[0].length; j++)
                {
                    if(pieces[i][j].isEnabled())
                        pieces[i][j].setEnabled(false);                    
                }
        }
    }
}