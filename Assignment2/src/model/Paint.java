/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
//import java.awt.Color;

import java.util.Vector;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


/**
 *
 * @author lars
 * This class draws squares by using a vector with the numbers -2,-1, 1, 2, 3, 4, 5,
 * 5 is the goal and -2 is the start
 * The length of each square is calculated so that the whole map fits into the screen
 * Then it loops trough the vector and sets a color based on the value, and then draws a square
 * with the correct position by changing the 1 d coordinate to a 2d coordinate
 */
public class Paint {
    public static void drawSquares(GraphicsContext gc, Canvas mC,Vector<Integer> table, int width)
    {
        gc = mC.getGraphicsContext2D();
        double limit=Math.floor(mC.getHeight()/((table.size())/width));
        
        
        gc.setFill(javafx.scene.paint.Color.WHITESMOKE);
        gc.fillRect(0, 0, mC.getWidth(), mC.getHeight());
        for(int i = 0; i<table.size();i++)
        {
            if(table.get(i)==-1)
            {

                gc.setFill(javafx.scene.paint.Color.RED);
                
            }
            else if(table.get(i)==1)
            {
                gc.setFill(javafx.scene.paint.Color.WHITE);
            }
            else if(table.get(i)==2)
            {
                gc.setFill(javafx.scene.paint.Color.LIGHTGRAY);
            }
            else if(table.get(i)==3)
            {
                gc.setFill(javafx.scene.paint.Color.GRAY);
            }
            else if(table.get(i)==4)
            {
                gc.setFill(javafx.scene.paint.Color.BLACK);
            }
            else if(table.get(i)==5)
            {
                gc.setFill(javafx.scene.paint.Color.YELLOW);
            }
            else if(table.get(i)==-2)
            {
                gc.setFill(javafx.scene.paint.Color.BLUE);
            }
            double x=(i)%(width+1)*limit;
            double y=Math.floor((i)/(width+1))*limit;
            gc.fillRect(x,y,limit, limit);
        }
     
    }
}