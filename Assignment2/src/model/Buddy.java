/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


/**
 *
 * @author Lars
 * 
 * This class is the implementation of the pseudo code for A*
 * It contains an vector array with the map data, the start positions and goal positions and the maps height and width.
 * There are get methods for getting the state of a "pixel" in the map that takes x and y coordinates getPosState(int x, int y);
 * There is also possible to change the pixel state with changeState(int x, int y) this is used when the correct path has been found and it needs to be visualised
 * The method setStartAndGoal() finds the start and end coordinates by looping over the table with two for loops. 
 */
public class Buddy {
    private Vector<Integer> table;
    private int endx,endy,startx,starty;
    private int width,height;
    public Buddy(Vector<Integer> t, int w)
    {
        table=t;
        width=w;
        height=table.size()/w;
    }
    
    public int getPosState(int x, int y)
    {
        
        return table.get(x+y*(width+1));
    }
    
    public void changeState(int x, int y, int value)
    {
        table.set(x+y*(width+1), value);
    }
    
    public Vector<Integer> getTable()
    {
        return table;
    }
    public void setStartAndGoal()
    {
        for(int x=0;x<width-1;x++)
        {
            for(int y=0;y<height-1;y++)
            {
                if(getPosState(x,y)==5)
                {
                    endx=x;
                    endy=y;
                }
                else if(getPosState(x,y)==-2)
                {
                    startx=x;
                    starty=y;
                }
            }
        }
    }
    //This method is the actual implementation of the A*, in my version it is not possible to move diagonally, this can be changed by modifying line 104 if statement, 
    //but i liked this better due to in real life moving one diagonal vs horizontal or vertical is longer and should inncur a higher cost.
    //if the next node has the end coordinates the the boolean foundEnd turns into true, and by looping through the parents backwards and adding them to the correctPath list it is possible to use this list to mark the path
    //The g cost value is calculated by going through the parents until the starting point is found and summing up all the different costs that the nodes have
    //The h cost value uses phytegoras to find the distance
    public void findPath()
    {
        List<Node> open = new ArrayList<Node>();
        List<Node> closed = new ArrayList<Node>();
        List<Point> correctPath = new ArrayList<Point>();
        Point point = new Point(startx,starty);
        Node root= new Node(point,getPosState(startx, starty),0,((startx-endx)^2+(starty-endy)^2));
        open.add(root);
        boolean foundEnd=false;
        while(!open.isEmpty()&&!foundEnd)
        {
            double lowestValue=open.get(0).getfCost();
            int lowestIndex=0;
            for(int i=0;i<open.size();i++)
            {
                if(open.get(i).getfCost()<lowestValue)
                {
                    lowestValue=open.get(i).getfCost();
                    lowestIndex=i;
                }
                
            }
            closed.add(open.get(lowestIndex));
            open.remove(lowestIndex);
            
            Point current=closed.get(closed.size()-1).getData();
            for(int x=-1;x<2;x++)
            {
                for(int y=-1;y<2;y++)
                {
                    if(!(x==-1&&y==0||x==1&&y==0||x==0&&y==-1||x==0&&y==1))
                    {

                    }
                    else
                    {
                        if(getPosState(current.x+x, current.y+y)==-1)
                        {
                            
                            
                        }
                        else if(getPosState(current.x+x, current.y+y)==5)
                        {
                            Node n=closed.get(closed.size()-1);
                            while(n.getParent()!=null)
                            {
                                correctPath.add(n.getData());
                                n=n.getParent();
                            }
                            foundEnd=true;
                        }
                        else    
                        {   
                            boolean inclosed=false;
                            for(int i=0;i<closed.size();i++)
                            {
                                if((closed.get(i).getData().x==current.x+x)&&(closed.get(i).getData().y==current.y+y))
                                {
                                    inclosed=true;
                                }
                            }
                            if(!inclosed)
                            {
                                boolean inopen=false;
                                for(int i=0;i<open.size();i++)
                                {
                                    if((open.get(i).getData().x==current.x+x)&&(open.get(i).getData().y==current.y+y))
                                    {
                                        inopen=true;
                                        if(open.get(i).getgCost()>closed.get(closed.size()-1).getgCost())
                                        {
                                            open.get(i).setParent(closed.get(closed.size()-1));
                                            double travelCost=getTravelCost(open.get(i));
                                            open.get(i).setCosts(travelCost,((current.x+x-endx)^2+(current.y+y-endy)^2));
                                        }
                                    }
                                }
                                if(inopen)
                                {
                                    
                                }
                                else
                                {
                                    Point tpoint = new Point(current.x+x,current.y+y);
                                    Node temp= new Node(tpoint,getPosState(current.x+x, current.y+y),0,0);
                                    temp.setParent(closed.get(closed.size()-1));
                                    double travelCost=getTravelCost(temp);
                                    temp.setCosts(travelCost,((current.x+x-endx)^2+(current.y+y-endy)^2));
                                    open.add(temp);
                                }
                            }
                            
                        }
                    }
                }
            }  
        }
        
        
        for(int i=0;i<correctPath.size();i++)
        {
            changeState(correctPath.get(i).x,correctPath.get(i).y,-2);
        }
        
    }
    
    public int getTravelCost(Node n)
    {
        int sum=0;
        while(n.getParent()!=null)
        {
            sum+=n.getState();
            n=n.getParent();
        }
        return sum;
    }
}
