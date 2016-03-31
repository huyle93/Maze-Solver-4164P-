/**
 * MazeGUI - GUI for the Maze escape program CS416 Spring 2013
 * 
 * 02/18/13
 * rdb
 */
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MazeGUI extends JPanel implements Animated
{
   //---------------- class variables ------------------------------
   
   //--------------- instance variables ----------------------------
   Maze        _maze;
   Random      _rng;
   int         _moveTimeDelay = 1000;   
   FrameTimer  _timer;
   JPanel      _drawArea;

   /////////////////////////////////////////////////
   // You'll need more instance variables 
   /////////////////////////////////////////////////
   //------------------ constructor ---------------------------------
   public MazeGUI()     
   {
      this.setLayout( new BorderLayout() );
      _timer = new FrameTimer( _moveTimeDelay, this );
      _rng = new Random( MazeApp.seed );
      
      //////////////////////////////////////////////////////////////
      // You need to create a Vector or ArrayList to store your path
      //   and perhaps other variables you'll want or need.
      //////////////////////////////////////////////////////////////
      
      
      buildGUI();
      buildGraphics();      
   }
 
   //----------------------- buildGUI() ------------------------------
   /**
    * Create 3 buttons in the North panel; that's all we need
    */
   private void buildGUI( )
   {
      //////////////////////////////////////////////
      // You shouldn't need to change this method
      //////////////////////////////////////////////
      JPanel buttonPanel = new JPanel();
      
      JButton placeButton = new JButton( "Place player" ); 
      placeButton.addActionListener( 
         // This code shows an example of an "anonymous" inner class 
         // that extends ActionListener. After the invocation of the 
         // ActionListener constructor, a set of braces indicates that the
         // object to be created should be an instance of a new "unnamed"
         // class that extends ActionListener and overrides the actionPerformed
         // method as specified in the { ... } block that follows the new clause.
         new ActionListener()
         {
            public void actionPerformed( ActionEvent ae ) 
            { 
               placePlayerAction(); 
            } 
         }
      );
      buttonPanel.add( placeButton );
      
      JButton keyButton = new JButton( "Magic key escape" ); 
      keyButton.addActionListener( 
         new ActionListener()
         {  public void actionPerformed( ActionEvent ae ) 
            {  magicEscapeAction(); } 
         }
      );
      buttonPanel.add( keyButton );
      
      JButton escButton = new JButton( "Escape" );
      buttonPanel.add( escButton );
      escButton.addActionListener( 
         new ActionListener()
         {  public void actionPerformed( ActionEvent ae ) 
            {  searchEscapeAction(); } 
         }
      );
      
      this.add( buttonPanel, BorderLayout.NORTH );
   }
   //----------------------- buildGraphics() --------------------------
   /**
    * create a draw area, add the maze to it.
    * create a player position and tell the maze about it.
    */
   private void buildGraphics()
   {    
      //////////////////////////////////////////////
      // You shouldn't need to change this method
      //////////////////////////////////////////////
      _drawArea = new JPanel();
      _drawArea.setLayout( null );
      add( _drawArea );                                             
      _maze = new Maze();
      _drawArea.add( _maze );
      placePlayerAction();
   }
   //-------------- placePlayerAction() -------------------------------
   /**
    * generate a random player position in the maze of rooms and 
    * tell the maze about it.
    */
   private void placePlayerAction()
   {
      // Do not change the next 2 lines.
      int row = _rng.nextInt( _maze.getRows() );
      int col = _rng.nextInt( _maze.getCols() );
      
      /////////////////////////////////////////////////////////
      // finish this method
      ////////////////////////////////////////////////////////

   }
   //-------------- magicEscapeAction() -------------------------------
   /**
    * Respond to magic key escape button
    */
   private void magicEscapeAction()
   {
      System.out.println( "Use that key and get out fast!" );
      /////////////////////////////////////////////////////////
      // finish this method, which may require other methods
      ////////////////////////////////////////////////////////
   }
   //-------------- searchEscapeAction() -------------------------------
   /**
    * Respond to  escape button
    */
   private void searchEscapeAction()
   {
      System.out.println( "Search for an escape path" );
      /////////////////////////////////////////////////////////
      // finish this method, which will certainly require at least
      //    one more method -- the one that actually does the recursion.
      ////////////////////////////////////////////////////////
   }

   //----------------------- updateDisplay ---------------------------
   /**
    * This method invokes a special Swing method that immediately does a
    * repaint of the specified region of the specified component.
    * It's just what we need to update the display during recursion
    * We'll always do the entire component. This will also sleep
    * for the specified msecs.
    */
   private void updateDisplay( JComponent comp, int msec )
   {
      comp.paintImmediately( comp.getBounds() );
      try 
      { 
         Thread.sleep( msec ); 
      } 
      catch (InterruptedException ie ) 
      {
         System.err.println( "updateDisplay InterruptedException: " +
                               ie.getMessage() );
      }
   }
   //+++++++++++++++++++++ animated interface ++++++++++++++++++++++++++++++
   //------------------------ newFrame ----------------------------------
   /**
    * Here you need to implement the post-search traversal of your
    * final search path.
    */
   public void newFrame() 
   {
      ////////////////////////////////////////////////////////
      // On each new frame, you'll get another room from your
      //   path array, color it, tell the maze that this is where
      //   the player now should be displayed and then return.
      // You also need to find out if you've shown all rooms on
      //   the path so you can turn the interval timer off.
      ////////////////////////////////////////////////////////
      
      
      this.repaint();   
   }
   //----------------- isAnimated -----------------------------------
   private boolean animated = true;
   
   public boolean isAnimated()
   {
      return animated;
   }
   //---------------- setAnimated ------------------------------------
   public void setAnimated( boolean onOff )
   {
      animated = onOff;
   }
   //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
   //------------------------------- main -------------------------------
   public static void main( String [] args ) 
   {
      MazeApp app = new MazeApp( "MazeApp demo", args );
   }
}