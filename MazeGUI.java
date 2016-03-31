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
    Room room;
    ArrayList<Room> path;
    ArrayList<Room> _path;
    Room nextRoom;
    Room _room;
    
    int _r;
    int c;
    
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
        path = new ArrayList<Room>();
        
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
//        _r = row;
//        c = col;
//        
//        _maze.cleanRooms();
//        
//        Room room = _maze.getRoom( row, col );
//        _room = room;
//        _maze.setPlayerRoom( room );
        room = _maze.getRoom( row, col );
        _maze.setPlayerRoom( room );
        _maze.cleanRooms();
        
    }
    //-------------- magicEscapeAction() -------------------------------
    /**
     * Respond to magic key escape button
     */
    private void magicEscapeAction()
    {
        System.out.println( "Use that key and get out fast!" );
        
        int mazecol = _maze.getCols();
        int mazerow = _maze.getRows();
        
        int midMazeCol = mazecol / 2;
        int midMazeRow = mazerow / 2;
        
        int row = room.getRow() + 1;
        int col = room.getCol() + 1;
        
        if( col <= midMazeCol && row <= midMazeRow )
        {
            while( col >= row && row !=0 )
            {
                row--;
                room = new Room( row -1, col -1 );
                path.add( room );
            }
            while( col < row && col != 0 )
            {
                col--;
                room = new Room( row -1, col -1 );
                path.add( room );
            }
        }
        else if( col > midMazeCol && row <= midMazeRow )
        {
            
            while( col >= mazerow + 1 - row && col != mazecol + 1 )
            {
                col++;
                room = new Room( row -1, col -1 );
                path.add( room );
            }
            while( col < mazerow + 1 - row && row != 0 )
            {
                col--;
                room = new Room( row -1, col -1 );
                path.add( room );
            }
        }
        else if( col > midMazeCol && row > midMazeRow )
        {
            
            while( col <= row && row != mazerow + 1 )
            {
                row++;
                room = new Room( row -1, col -1 );
                path.add( room );
            }
            while( col > row && col != mazecol + 1 )
            {
                col++;
                room = new Room( row -1, col -1 );
                path.add( room );
            }
        }
        else
        {
            while( mazecol + 1 - col >= row && col != 0 )
            {
                col--;
                room = new Room( row -1, col -1 );
                path.add( room );
            }
            
            while( mazecol + 1 - col < row && row != mazerow + 1 )
            {
                row++;
                room = new Room( row -1, col -1 );
                path.add( room );
            }
        }
        _timer.start();
        
        
        
        
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
        escape( room, path );
        //System.out.println( "The ArrayList:  " + path );
        _timer.start();
    }
    private boolean escape( Room room, ArrayList<Room> path1 )
    {
        
        int row = room.getCol();
        int col = room.getRow();
        
        if( path.contains( room ) )
        {
            return false;    
        }
        path.add( room );
        
        if( room.getCol() >= _maze.getCols() || room.getCol() < 0  
               ||  room.getRow() >= _maze.getRows() || room.getRow() < 0 )
        {
            return true;
        }
        room.setColor( Color.MAGENTA );
        
        for( Room.Door d : Room.Door.values() )
        {
            if( room.doorIsOpen( d ) == true )
            {
                
                if( d == Room.Door.N ) //N
                {
                    col = room.getCol();
                    row = room.getRow() - 1;
                }
                else if( d == Room.Door.E ) //E
                {
                    col = room.getCol() + 1;
                    row = room.getRow();
                }
                else if( d == Room.Door.S ) //S
                {
                    col = room.getCol() ;
                    row = room.getRow() + 1;
                }
                else //W
                {
                    col = room.getCol() -1;
                    row = room.getRow();
                }
                
                nextRoom = _maze.getRoom( row, col );
                room.setColor( Color.BLUE );
                updateDisplay( _maze, 600 );
                
                
                if( nextRoom == null )
                {
                    nextRoom = new Room(row, col);
                } 
                if( escape ( nextRoom, path1 ) )
                {
                    return true;    
                }     
            }
            
        }
        printPath();
        updateDisplay( _maze, 600 );
        path.remove( room );
        return false;
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
        printPath();
        
        if( path.size() > 0 )
        {
            Room room1 = path.get( 0 );
            _maze.setPlayerRoom( room1 );
            path.remove( 0 );
            Room r = _maze.getRoom( room1.getRow(), room1.getCol() );
            
            if( r != null )
            {
                r.setColor( Color.CYAN );
            }
        }
        else
        {
            _timer.stop();
        }
        this.repaint();
        
        
        //this.repaint();   
    }
    private void printPath()
    {
        System.out.println( "The ArrayList: " + path );
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