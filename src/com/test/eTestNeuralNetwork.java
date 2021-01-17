package com.test;

import com.math.*;
import com.neural.*;
import com.graph.*;

class MyNN extends eNeuralNetwork{
    eLayer layers[];
    public MyNN(){
	layers = new eLayer[5];
	layers[0] = new eLinear(2,5);
	layers[1] = new eReLU();
	layers[2] = new eLinear(5,2);
	layers[3] = new eReLU();
	layers[4] = new eSoftmax();
    }
    public eMatrix forward(eMatrix inp){
	eMatrix tmp;
	tmp = inp;
	for(eLayer l : layers)
	    tmp = l.forward(tmp);
	return tmp;
    }
    public eMatrix backward(eMatrix err){
	eMatrix tmp = err;
	for(int i = layers.length-1;i>=0;i--)
	    tmp = layers[i].backward(tmp);
	return tmp;
    }
    public void update(double learning_rate){
	for(eLayer l : layers)
	    l.update(learning_rate);
    }
    public void train(eMatrix inp, eMatrix target, eLoss loss, double learning_rate, int maxIt){
	double l;
	eMatrix out,err;
	double[] loss_log = new double[maxIt];
	for(int i = 0;i<maxIt;i++){
	    out = forward(inp);
	    l = loss.loss(out,target);
	    loss_log[i] = l;
	    System.out.println("loss: " + l);
	    err = loss.grad_loss();
	    backward(err);
	    update(learning_rate);
	}
	ePlot plt = new ePlot();
	plt.plot(loss_log);
	plt.title("Loss evolution");
	plt.xlabel("ephocs");
	plt.ylabel("Loss");
	plt.showPlot();
    }
}

public class eTestNeuralNetwork{
    public static void main(String args[]){
	eMatrix[] train_data;
	MyNN net = new MyNN();
	//eLoss loss = new eMeanSquareLoss();
	eLoss loss = new eCrossEntropyLoss();

	train_data = eDataGenerator.generateClassificationPoints(20);
	
	net.train(train_data[0],train_data[1],loss,0.1,100);
	System.out.println(train_data[1]);
	System.out.println(net.forward(train_data[0]));
    }
}
