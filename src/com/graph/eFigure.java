package com.graph;

import java.awt.*;
import javax.swing.*;

public class eFigure extends JPanel{

    JLabel titleValue;
    JLabel xlabelValue;
    JLabel ylabelValue;
    eGraph graph;
    int width = 500;
    int height = 500;

    public eFigure(){
	super();
	titleValue = new JLabel();
	xlabelValue = new JLabel();
	ylabelValue = new JLabel();
	graph = new eGraph();

	setPreferredSize(new Dimension(width,height));
	setLayout(new BorderLayout());
	JPanel title_panel = new JPanel();
	title_panel.setLayout(new GridLayout(1,3));
	title_panel.add(new JLabel(""));
	title_panel.add(titleValue);
	title_panel.add(new JLabel(""));

	JPanel ylabel_panel = new JPanel();
	ylabel_panel.setLayout(new GridLayout(3,1));
	ylabel_panel.add(new JLabel(""));
	ylabel_panel.add(ylabelValue);
	ylabel_panel.add(new JLabel(""));

	JPanel xlabel_panel = new JPanel();
	xlabel_panel.setLayout(new GridLayout(1,3));
	xlabel_panel.add(new JLabel(""));
	xlabel_panel.add(xlabelValue);
	xlabel_panel.add(new JLabel(""));

	add(title_panel,BorderLayout.NORTH);
	add(ylabel_panel,BorderLayout.WEST);
	add(graph,BorderLayout.CENTER);
	add(xlabel_panel,BorderLayout.SOUTH);
    }
        public void plot(double[] x, double[] y){
	if(x.length != y.length){
	    System.err.println("vectors are from different size");
	    return;
	}
	graph.setData(x,y);
    }

    public void plot(double[] y){
	graph.setData(y);
    }

    public void size(int w, int h){
	width = w;
	height = h;
	setSize(width,height);
    }
    
    public void title(String title){
	titleValue.setText(title);
    }

    public void xlabel(String label){
	xlabelValue.setText(label);
    }

    public  void ylabel(String label){
	ylabelValue.setText(label);
    }
}
