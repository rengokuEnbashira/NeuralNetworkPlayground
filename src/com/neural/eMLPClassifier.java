package com.neural;

import com.math.*;

public class eMLPClassifier extends eNeuralNetwork{

    eLayer[] layers;

    public static int SIGMOID = 0;
    public static int TANH = 1;
    public static int RELU = 2;

    public eMLPClassifier(int[] num_neurons,int act_fun){
	int N = num_neurons.length - 1;
	layers = new eLayer[2*N + 1];
	for(int i = 0;i<N;i++){
	    layers[2*i] = new eLinear(num_neurons[i],num_neurons[i+1]);
	    if(act_fun == TANH)
		layers[2*i + 1] = new eTanh();
	    else if(act_fun == RELU)
		layers[2*i + 1] = new eReLU();
	    else
		layers[2*i + 1] = new eSigmoid();
	}
	layers[2*N] = new eSoftmax();
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

    public void train(eMatrix inp, eMatrix target, double learning_rate, int maxIt){
	eLoss loss = new eCrossEntropyLoss();
	train(inp,target,loss,learning_rate,maxIt);
    }
}
