/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Point;

/**
 *
 * @author lars
 */
//The node class contains the coordinate, state, parent, fcost, gcost, hcost
public class Node<T> 
{
    private Point point;
    private int state;
    private Node<T> parent;
    private double fcost;
    private double gcost;
    private double hcost;
    public Node(Point t,int s,double gc,double hc)
    {
        point=t;
        state=s;
        gcost=gc;
        hcost=hc;
        fcost=gc+hc;
    }
    public Point getData()
    {
        return point;
    }
    public void setParent(Node<T> p)
    {
        parent=p;
    }
    public double getfCost()
    {
        return fcost;
    }
    public double getgCost()
    {
        return gcost;
    }
    public double gethCost()
    {
        return hcost;
    }
    
    public void setCosts(double gc, double hc)
    {
        gcost=gc;
        hcost=hc;
        fcost=gc+hc;
    }
    public Node getParent()
    {
        return parent;
    }
    
    public int getState()
    {
        return state;
    }
    public void setState(int i)
    {
        state=i;
    }
 
} 
