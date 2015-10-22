/**
 * MazeApp.java -- Based on the canonical "main" program, SwingApp,
 *                  for a Swing application, SwingApp with GUI widgets
 *                  and a draw panel for graphics output.
 *
 */
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class MazeApp extends JFrame 
{
   static int seed = 1;
   
   public MazeApp( String title, String[] args ) 
   {
      super( title );
      this.setSize( 700, 600 );
      this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      System.out.println( "args: " + args.length );
      
      ////////////////////////////////////////////////////
      // Processing command line arguments.
      ////////////////////!////////////////////////////////
      int nRows = getArg( args, 0, 10 );
      int nCols = getArg( args, 1, nRows );
      int roomW = getArg( args, 2, 40 );
      int roomH = getArg( args, 3, roomW );
      Maze.setMazeSize( nRows, nCols );
      Maze.setRoomSize( roomW, roomH ); 
            
      MazeGUI appGUI = new MazeGUI();
      
      this.add( appGUI );
      this.setVisible( true );
   }

   //++++++++++++++++++++++ class methods ++++++++++++++++++++++++++++
   //------------- getArg( String[]. int, int ) ----------------------
   /**
    * This is a utility method for accessing one of the command line
    * string arguments and converting it to an int. It accepts the 
    * entire array of arguments in String form, an integer indicating 
    * which is to be converted to an int and a default value to be 
    * returned if this argument was not on the command line, or if 
    * the specified String is not a valid integer representation.
    */
   private static int getArg( String[ ] args, int which, int defaultVal )
   {
      try
      {
         // The Integer class has a class method that will convert a String
         //   to an int -- if it is a valid representation of an  integer. 
         return Integer.parseInt( args[ which ] );
      }
      catch ( ArrayIndexOutOfBoundsException oob )
      {
         // If there is no args[ which ] element, Java "throws" an exception.
         // This code "catches" the exception and handles it gracefull.
         // In this case, it is not an error. The parameter is optional
         // and there is a specified default value that is returned.
      }
      catch ( NumberFormatException nfe )
      {
         // If the string is not a valid representation of an integer
         // (such as 4Qd3), Integer.parseInt throws a "NumberFormatException".
         // Again, this code catches the exception, gives an error message
         // and uses the default value.
         
         System.err.println( "Error: improper command line argument " 
                            + which + " = " + args[ which ] 
                            + ".  Should be an integer; using default value: "
                            + defaultVal );
      }  
      return defaultVal;
   }
   //------------------------------- main -------------------------------
   public static void main( String [] args ) 
   {
      MazeApp app = new MazeApp( "MazeApp demo", args );
   }
}