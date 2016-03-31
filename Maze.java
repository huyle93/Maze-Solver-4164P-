/**
 * Maze.java -- a 2D rectangular grid of Rooms that can become a maze
 *              based on open/locked doors between the rooms
 * 
 * rdb
 * 02/19/13 - Program 5 - Spring 2013
 */ 

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Maze extends JComponent
{  
   //------------------- class variables -------------------------
   private static int nRows      = 10, nCols = 10;
   private static int roomWidth  = 30;
   private static int roomHeight = 30;
   //------------------- instance variables -------------------------
   private final int NORTH = 0;
   private final int EAST  = 1;
   private final int SOUTH = 2;
   private final int WEST  = 3;
   
   private Room [ ][ ] theMaze;
   private int _mazeX = 100;
   private int _mazeY = 50;
   
   private JEllipse _player = null;
   private int      _playerXoffset = 0; // gets center of player in room center
   private int      _playerYoffset = 0;
   //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
   //------------------ constructor ---------------------------------
   public Maze() 
   {  
      Random rng;
      setLayout( null );
      if ( MazeApp.seed < 0 )
         rng = new Random();
      else
         rng = new Random( MazeApp.seed );
      
      theMaze = new Room[ nRows ][ nCols ]; 
      
      for ( int r = 0; r < nRows; r++ )  
      {
         for ( int c = 0; c < nCols; c++ )
         {
            Room room =  new Room( r, c , roomWidth, roomHeight );
            room.setLocation( _mazeX + c * roomWidth, _mazeY + r * roomHeight );
            add( room );
            for ( Room.Door d:  Room.Door.values() )
               room.setDoor( d, rng.nextBoolean() );
            theMaze[ r ][ c ] = room;
            add( room );
         }
      }
      
      makePlayer();
      // We know the size needed, so just set it directly
      //   Need 2 extra rows and columns for the phantom exterior rooms.
      this.setBounds( 0, 0, 
                     ( nCols + 2 ) * roomWidth + _mazeX,
                     ( nRows + 2 ) * roomHeight + _mazeY );
   }
   //------------------ makePlayer() --------------------------------------
   /**
    * Create an icon for the player/prisoner (an ellipse) 
    */
   private void makePlayer()
   {
      _player = new JEllipse();
      _player.setSize(  roomWidth / 2, roomHeight / 2 );
      _player.setColor( Color.BLACK );
      _playerXoffset = roomWidth / 4;
      _playerYoffset = roomHeight / 4;  
      add( _player );
      
      // make sure the player is always the last thing drawn
      this.setComponentZOrder( _player, 0 );      
   }
   //------------------- setPlayerRoom( Room ) -----------------------------
   /**
    * place player in the specified room, which could be a dummy
    */
   public void setPlayerRoom( Room rm )
   {
       Point rmLoc = getLocFromRoom( rm );
       _player.setLocation( rmLoc.x + _playerXoffset, rmLoc.y + _playerYoffset );
       repaint();
   }                                 
   //------------------- getLocFromPosition( int, int  ) ----------------
   private Point getLocFromPosition( int r, int c )
   {
      //System.out.println( "Room: " + r + ", " + c );
      return new Point( _mazeX + c  * roomWidth, 
                        _mazeY + r * roomHeight );  
   } 
   //------------------------- getLocFromRoom( Room ) ----------------
   private Point getLocFromRoom( Room rm )
   {
      System.out.println( "Room: " + rm );
      if ( rm.isDummy() )
         return getLocFromPosition( rm.getRow(), rm.getCol() );
      else
         return rm.getLocation();
   } 
   
   //------------------------- getRows() -------------------------
   public int getRows( )
   {
         return nRows;   
   } 
   
   //------------------------- getCols() -------------------------
   public int getCols( )
   {
         return nCols;   
   } 
   
   //------------------------- getRoomWidth() -------------------------
   public int getRoomWidth( )
   {
         return roomWidth;   
   } 
   //------------------------- getRoomHeight() -------------------------
   public int getRoomHeight( )
   {
         return roomHeight;   
   } 
   
   //----------------------- getRoom( int, int ) -------------------------
   public Room getRoom( int r, int c )
   {
      if( r < 0 ||  r >= nRows || c < 0 || c >= nCols )
         return null;
      else
         return theMaze[ r ][ c ];    
   }
   //--------------------- cleanRooms() --------------------------------
   public void cleanRooms()
   {
      for ( int r = 0; r < nRows; r++ )  
      {
         for ( int c = 0; c < nCols; c++ )
         {
            Room room =  theMaze[ r ][ c ];
            room.setColor( null );
         }
      }
   }

   //++++++++++++++++++++++++++++ static methods +++++++++++++++++++++++++++
   //------------------------- setSize( int, int ) -----------------------------
   static void setMazeSize( int nr, int nc )
   {
      nRows = nr;
      nCols = nc;
   }
   //------------------------- setRoomSize( int, int ) -----------------------------
   static void setRoomSize( int w, int h )
   {
      roomWidth = w;
      roomHeight = h;
   }
   //------------------------------- main -------------------------------
   public static void main( String [] args ) 
   {
      MazeApp app = new MazeApp( "MazeApp demo", args );
   }
}