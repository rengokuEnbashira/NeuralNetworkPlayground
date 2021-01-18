package com.app;

import java.awt.*;
import javax.swing.*;
import com.math.*;
import com.neural.*;
import com.graph.*;

public class eApp extends JFrame{

    int width;
    int height;

    eBoard board;
    eFigure fig;
    eBlockPanel blockPanel;
    
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem loadModel;
    JMenuItem saveModel;
    
    public eApp(){
	super();
	width = 500;
	height = 500;

	board = new eBoard();
	fig = new eFigure();
	blockPanel = new eBlockPanel();
	
	menuBar = new JMenuBar();
	fileMenu = new JMenu("File");
	loadModel = new JMenuItem("Load Model");
	saveModel = new JMenuItem("Save Model");
	
	setSize(width,height);
	setTitle("Neural Network App - by Elvin");
	setLayout(new BorderLayout());

	setJMenuBar(menuBar);
	menuBar.add(fileMenu);
	fileMenu.add(loadModel);
	fileMenu.add(saveModel);
	

	add(board,BorderLayout.CENTER);
	add(blockPanel,BoderLayout.EAST);
	
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setVisible(true);
    }
    
    public static void main(String args[]){
	new eApp();
    }
}
