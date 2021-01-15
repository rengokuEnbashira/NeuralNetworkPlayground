package com.graph;

import java.awt.*;
import javax.swing.*;

public class eGraph extends JPanel{

    double[] x;
    double[] y;
    double xMinValue;
    double yMinValue;
    double xMaxValue;
    double yMaxValue;
    
    public eGraph(){
	super();
    }

    public void setData(double[] x, double[] y){
	this.x = x;
	this.y = y;
	xMinValue = x[0];
	xMaxValue = x[0];
	yMinValue = y[0];
	yMaxValue = y[0];
	for(int i = 1;i<x.length;i++){
	    if(x[i] < xMinValue)
		xMinValue = x[i];
	    if(x[i] > xMaxValue)
		xMaxValue = x[i];
	    if(y[i] < yMinValue)
		yMinValue = y[i];
	    if(y[i] > xMaxValue)
		yMaxValue = y[i];
	}
    }

    public void setData(double[] y){
	int N = y.length;
	this.y = y;
	this.x = new double[N];
	yMinValue = y[0];
	yMaxValue = y[0];
	xMinValue = 0;
	xMaxValue = N-1;
	this.x[0] = 0;
	for(int i = 1;i<y.length;i++){
	    if(y[i] < yMinValue)
		yMinValue = y[i];
	    if(y[i] > yMaxValue)
		yMaxValue = y[i];
	    this.x[i] = i;
	}
    }

    public static double mapValue(double x, double a, double b, double p, double q){
	return (q-p)*(x-a)/(b-a) + p;
    }
    public void paintComponent(Graphics g){
	super.paintComponent(g);
	int w,h;
	Dimension dim = getSize();
	double px1,py1,px2,py2; 
	w = dim.width;
	h = dim.height;
	for(int i=0;i<x.length-1;i++){
	    px1 = mapValue(x[i],xMinValue,xMaxValue,0,w);
	    py1 = mapValue(y[i],yMinValue,yMaxValue,h,0);
	    px2 = mapValue(x[i+1],xMinValue,xMaxValue,0,w);
	    py2 = mapValue(y[i+1],yMinValue,yMaxValue,h,0);
	    g.drawLine((int)px1,(int)py1,(int)px2,(int)py2);
	}
    }
}
