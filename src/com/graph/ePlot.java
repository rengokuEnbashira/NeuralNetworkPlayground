package com.graph;

import javax.swing.*;
import java.awt.*;

public class ePlot extends JFrame{

    eFigure fig;

    public ePlot(){
	super();
	fig = new eFigure();
	setTitle("Figure");
	add(fig);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void plot(double[] x, double[] y){
	fig.plot(x,y);
    }

    public void plot(double[] y){
	fig.plot(y);
    }

    public void size(int w, int h){
	fig.size(w,h);
    }
    
    public void title(String title){
	fig.title(title);
    }

    public void xlabel(String label){
	fig.xlabel(label);
    }

    public  void ylabel(String label){
	fig.ylabel(label);
    }

    public void showPlot(){
	pack();
	setVisible(true);
    }
}
