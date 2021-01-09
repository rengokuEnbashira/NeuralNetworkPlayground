package com.test;

import com.math.*;
import com.neural.*;

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
	for(int i = 0;i<maxIt;i++){
	    out = forward(inp);
	    l = loss.loss(out,target);
	    System.out.println("loss: " + l);
	    err = loss.grad_loss();
	    backward(err);
	    update(learning_rate);
	}
    }
}


public class eTest{
    public static void main(String args[]){
	eMatrix in_set = new eMatrix(4,2);
	eMatrix out_set = new eMatrix(4,2);
	MyNN net = new MyNN();
	//eLoss loss = new eMeanSquareLoss();
	eLoss loss = new eCrossEntropyLoss();
	
	in_set.data[0][0] = 0;
	in_set.data[0][1] = 0;
	in_set.data[1][0] = 0;
	in_set.data[1][1] = 1;
	in_set.data[2][0] = 1;
	in_set.data[2][1] = 0;
	in_set.data[3][0] = 1;
	in_set.data[3][1] = 1;

	out_set.data[0][0] = 1;
	out_set.data[0][1] = 0;
	out_set.data[1][0] = 0;
	out_set.data[1][1] = 1;
	out_set.data[2][0] = 0;
	out_set.data[2][1] = 1;
	out_set.data[3][0] = 1;
	out_set.data[3][1] = 0;

	net.train(in_set,out_set,loss,0.01,1000);
	System.out.println(net.forward(in_set));
    }
}
