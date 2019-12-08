/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import model.Buddy;
import model.FileHandler;
import model.Paint;
/**
 *
 * @author lars
 * The control file serves only three purposes for now, loading the csv file using FileHandler and then making 
 * Using the Buddy class to change the array using A* and also changing the canvas with the Paint method
 */
public class Controls implements Initializable {
    
    
    
    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) 
    {
       
       
       timer.scheduleAtFixedRate(task, 500, 500);
       
    }
    @FXML private Button startBtn;
    @FXML private Button stopBtn;
    @FXML private Button resetBtn;
    @FXML private Canvas mainCanvas;
    
    private static boolean play=false;
    Vector<Integer> table = new Vector<>();
    Buddy buddy;
    int width;
    
    
    Timer timer = new Timer(true);
    RunTimer task = new RunTimer();
    
    
    
    //user events
    
    public void canvasClick(MouseEvent event)
    {
       
     
    }

 
    public void stopButton(ActionEvent event)
    {
	play=false;
       
    }
    
    public void resetButton(ActionEvent event)
    {
      
     
    }
    
    public void loadEvent(ActionEvent event)
    {
        play=false;
        table=FileHandler.loadFile();
        width=table.get(0);
        table.remove(0);
        
        buddy = new Buddy(table, width);
        //Using two changeState arguments sets the start and end coordinates
        buddy.changeState(32, 28, -2);
        buddy.changeState(32, 6, 5);
        buddy.setStartAndGoal();
        buddy.findPath();
        GraphicsContext gc = mainCanvas.getGraphicsContext2D();
        Paint.drawSquares(gc, mainCanvas, buddy.getTable(), width);
    }
    
   
    public void startButton(ActionEvent event)
    {
        play=true;
    }
    
   
    
    private class RunTimer extends TimerTask
    {
        @Override 
        public void run()
        {
            if(play)
            {
                GraphicsContext gc = mainCanvas.getGraphicsContext2D();
                Paint.drawSquares(gc, mainCanvas, buddy.getTable(), width);
            }
        }
    }
}
