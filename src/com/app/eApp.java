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
    JPanel leftSide;
    JPanel rightSide;
    JPanel outputPanel;
    
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem loadModel;
    JMenuItem saveModel;

    JTextArea outputText;
    
    public eApp(){
	super();
	width = 500;
	height = 500;

	board = new eBoard();
	fig = new eFigure();
	blockPanel = new eBlockPanel();
	outputText = new JTextArea();
	
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

	leftSide = new JPanel();
	leftSide.setLayout(new BoxLayout(leftSide,BoxLayout.Y_AXIS));
	JLabel blocksLabel = new JLabel("Blocks");
	blocksLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
	leftSide.add(blocksLabel);
	blockPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	leftSide.add(blockPanel);


	rightSide = new JPanel();
	rightSide.setLayout(new BoxLayout(rightSide,BoxLayout.Y_AXIS));
	JLabel lossLabel = new JLabel("Loss");
	lossLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
	rightSide.add(lossLabel);
	fig.setAlignmentX(Component.LEFT_ALIGNMENT);
	//rightSide.add(fig);

	outputPanel = new JPanel();
	outputPanel.setLayout(new BoxLayout(outputPanel,BoxLayout.Y_AXIS));
	JLabel outputLabel = new JLabel("Output");
	outputLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
	outputPanel.add(outputLabel);
	outputText.setAlignmentX(Component.LEFT_ALIGNMENT);
	outputPanel.add(outputText);
	
	add(board,BorderLayout.CENTER);
	add(leftSide,BorderLayout.WEST);
	add(rightSide,BorderLayout.EAST);
	add(outputPanel,BorderLayout.SOUTH);
	
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setVisible(true);
    }
    
    public static void main(String args[]){
	new eApp();
    }
}
