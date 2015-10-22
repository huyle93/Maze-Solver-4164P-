/**
 * Room.java - implements a room in the Maze of Program 5 Spring 2013
 * 
 * rdb
 */

import javax.swing.*;
import java.awt.*;

public class Room extends JComponent
{
   //------------------- public enum -------------------------
   public enum Door { N, E, S, W };
   
   //----------------- instance variables --------------------
   private boolean[] doors = { false, false, false, false };
   private int[] x1;
   private int[] x2;
   private int[] y1;
   private int[] y2;
   private int _w;    // width of room
   private int _h;    //  height of room
   private int _r;
   private int _c;
   private int _lineWidth = 3;
   private Color _color;
   private boolean _dummy;
   
   //-------------------- constructors ------------------------
   public Room( int r, int c, int w, int h )
   {
      setSize( w, h );
      _w = w - _lineWidth;
      _h = h - _lineWidth;
      _r = r;
      _c = c;
      _color = null;
      _dummy = false;
   }
   /**
    * This is constructor for phony boundary rooms that are not drawn.
    */
   public Room( int r, int c )
   {
      setSize( 0, 0 );
      _w = 0;
      _h = 0;
      _r = r;
      _c = c;
      _dummy = true;
   }
   
   //----------------------- isDummy() -----------------------------
   /**
    * returns true if this Room is a dummy (exterior fake) room
    */
   public boolean isDummy()
   {
      return _dummy;
   }
   
   //-------------------- doorIsOpen( Door ) ------------------------
   /**
    * returns true if the door identified by the Door enum is unlocked
    */
   public boolean doorIsOpen( Door which )
   {
      return doors[ which.ordinal() ];
   }
   
   //-------------------- doorIsOpen( int ) ------------------------
   /**
    * returns true if the door identified by the ordinal() value of
    * the enum. 
    */
   public boolean doorIsOpen( int which )
   {
      return doors[ which ];
   }

   //------------------ setColor( Color ) ---------------------------
   /**
    * set the color of the interior of the room. "null" is a valid color
    */
   public void setColor( Color newColor )
   {
      _color = newColor;
   }
   //------------------ getColor(  ) -------------------------------
   /**
    * return the color of the interior of the room. "null" is a valid color
    */
   public Color getColor()
   {
      return _color;
   }
   //-------------------- setDoor( Door, boolean ) ---------------------
   /**
    * define whether the specified Door is open (unlocked) or locked
    */
   public void setDoor( Door which, boolean open )
   {
      doors[ which.ordinal() ] = open;
   }
   //-------------- getRow() -----------------------------------
   /**
    * return the row number of the room in the maze
    */
   public int getRow()
   {
      return _r;
   }
   //-------------- getCol() -----------------------------------
   /**
    * return the column number of the room in the maze
    */
   public int getCol()
   {
      return _c;
   }

   //-------------- toString() -----------------------------------
   /**
    * return a string representation of the room
    */
   public String toString()
   {
      return "<" + _r + "," + _c + ">";
   }
   //------------ paintComponent ---------------------------------
   /**
    * Draw the visual represenation of the room
    */
   public void paintComponent( Graphics g )
   {
      if ( _dummy ) 
         return;
      super.paintComponent( g );
      Graphics2D g2 = (Graphics2D) g;
      int lw = _lineWidth;
      
      g2.setClip( -lw, -lw, _w + 2 * lw, _h + 2 * lw );
      // draw 4 lines with thickness 2 to make a rectangle.
      // the line is green if the door is open, red if closed
      
      g2.setStroke( new BasicStroke( _lineWidth ));
      
      
      if ( doors[ 0 ] )
         g2.setColor( Color.GREEN );
      else
         g2.setColor( Color.RED );
      g2.drawLine( 0, 0, _w, 0 );
      
      if ( doors[ 1 ] )
         g2.setColor( Color.GREEN );
      else
         g2.setColor( Color.RED );
      g2.drawLine( _w, 0, _w, _h );
      
      if ( doors[ 2 ] )
         g2.setColor( Color.GREEN );
      else
         g2.setColor( Color.RED );
      g2.drawLine( _w, _h, 0, _h );
      
      if ( doors[ 3 ] )
         g2.setColor( Color.GREEN );
      else
         g2.setColor( Color.RED );
      g2.drawLine( 0, _h, 0, 0 ); 
      
      if ( _color != null )
      {
         g2.setColor( _color );
         g2.fillRect( 0, 0, _w, _h );
      }
   }
}