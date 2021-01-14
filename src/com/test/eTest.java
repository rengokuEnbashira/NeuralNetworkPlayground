package com.test;

import com.math.*;
import com.neural.*;

class MyNN extends eNeuralNetwork{
    eLayer layers[];
    public MyNN(){
	layers = new eLayer[5];
	layers[0] = new eLinear(2,5);
	layers[1] = new eTanh();
	layers[2] = new eLinear(5,2);
	layers[3] = new eSigmoid();
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
	eMatrix in_set = new eMatrix(30,2);
	eMatrix out_set = new eMatrix(30,2);
	MyNN net = new MyNN();
	//eLoss loss = new eMeanSquareLoss();
	eLoss loss = new eCrossEntropyLoss();

        in_set.data[0][0] = 1.028717;
	in_set.data[0][1] = -2.466173;
	in_set.data[1][0] = -0.929598;
	in_set.data[1][1] = 1.805282;
	in_set.data[2][0] = 2.165216;
	in_set.data[2][1] = -1.514596;
	in_set.data[3][0] = -0.269352;
	in_set.data[3][1] = 1.501417;
	in_set.data[4][0] = -0.676631;
	in_set.data[4][1] = 1.479003;
	in_set.data[5][0] = -0.182752;
	in_set.data[5][1] = -2.409519;
	in_set.data[6][0] = -0.801818;
	in_set.data[6][1] = -2.432127;
	in_set.data[7][0] = -2.396139;
	in_set.data[7][1] = 1.371644;
	in_set.data[8][0] = 1.487570;
	in_set.data[8][1] = -1.903534;
	in_set.data[9][0] = -2.086393;
	in_set.data[9][1] = -1.720236;
	in_set.data[10][0] = 2.068445;
	in_set.data[10][1] = 0.285268;
	in_set.data[11][0] = 2.422733;
	in_set.data[11][1] = -2.361712;
	in_set.data[12][0] = 0.437126;
	in_set.data[12][1] = 1.243532;
	in_set.data[13][0] = -2.354239;
	in_set.data[13][1] = 1.398811;
	in_set.data[14][0] = -0.605171;
	in_set.data[14][1] = 1.983456;
	in_set.data[15][0] = 0.807494;
	in_set.data[15][1] = 1.665552;
	in_set.data[16][0] = -0.074597;
	in_set.data[16][1] = 0.641372;
	in_set.data[17][0] = 0.776458;
	in_set.data[17][1] = 0.615870;
	in_set.data[18][0] = -1.073633;
	in_set.data[18][1] = 0.887713;
	in_set.data[19][0] = 1.224789;
	in_set.data[19][1] = 0.370162;
	in_set.data[20][0] = -1.062715;
	in_set.data[20][1] = -0.863915;
	in_set.data[21][0] = -0.740570;
	in_set.data[21][1] = -0.607700;
	in_set.data[22][0] = 2.315386;
	in_set.data[22][1] = -1.769951;
	in_set.data[23][0] = 2.325605;
	in_set.data[23][1] = -0.683391;
	in_set.data[24][0] = 2.406804;
	in_set.data[24][1] = 2.170453;
	in_set.data[25][0] = -0.437537;
	in_set.data[25][1] = -0.296786;
	in_set.data[26][0] = -0.841868;
	in_set.data[26][1] = -1.961566;
	in_set.data[27][0] = -2.315255;
	in_set.data[27][1] = -1.685494;
	in_set.data[28][0] = -2.433194;
	in_set.data[28][1] = -2.011394;
	in_set.data[29][0] = 0.284270;
	in_set.data[29][1] = -0.886838;

	out_set.data[0][0] = 1.000000;
	out_set.data[0][1] = 0.000000;
	out_set.data[1][0] = 0.000000;
	out_set.data[1][1] = 1.000000;
	out_set.data[2][0] = 1.000000;
	out_set.data[2][1] = 0.000000;
	out_set.data[3][0] = 1.000000;
	out_set.data[3][1] = 0.000000;
	out_set.data[4][0] = 0.000000;
	out_set.data[4][1] = 1.000000;
	out_set.data[5][0] = 1.000000;
	out_set.data[5][1] = 0.000000;
	out_set.data[6][0] = 1.000000;
	out_set.data[6][1] = 0.000000;
	out_set.data[7][0] = 0.000000;
	out_set.data[7][1] = 1.000000;
	out_set.data[8][0] = 1.000000;
	out_set.data[8][1] = 0.000000;
	out_set.data[9][0] = 1.000000;
	out_set.data[9][1] = 0.000000;
	out_set.data[10][0] = 1.000000;
	out_set.data[10][1] = 0.000000;
	out_set.data[11][0] = 1.000000;
	out_set.data[11][1] = 0.000000;
	out_set.data[12][0] = 1.000000;
	out_set.data[12][1] = 0.000000;
	out_set.data[13][0] = 0.000000;
	out_set.data[13][1] = 1.000000;
	out_set.data[14][0] = 0.000000;
	out_set.data[14][1] = 1.000000;
	out_set.data[15][0] = 1.000000;
	out_set.data[15][1] = 0.000000;
	out_set.data[16][0] = 1.000000;
	out_set.data[16][1] = 0.000000;
	out_set.data[17][0] = 1.000000;
	out_set.data[17][1] = 0.000000;
	out_set.data[18][0] = 0.000000;
	out_set.data[18][1] = 1.000000;
	out_set.data[19][0] = 1.000000;
	out_set.data[19][1] = 0.000000;
	out_set.data[20][0] = 1.000000;
	out_set.data[20][1] = 0.000000;
	out_set.data[21][0] = 1.000000;
	out_set.data[21][1] = 0.000000;
	out_set.data[22][0] = 1.000000;
	out_set.data[22][1] = 0.000000;
	out_set.data[23][0] = 1.000000;
	out_set.data[23][1] = 0.000000;
	out_set.data[24][0] = 1.000000;
	out_set.data[24][1] = 0.000000;
	out_set.data[25][0] = 1.000000;
	out_set.data[25][1] = 0.000000;
	out_set.data[26][0] = 1.000000;
	out_set.data[26][1] = 0.000000;
	out_set.data[27][0] = 1.000000;
	out_set.data[27][1] = 0.000000;
	out_set.data[28][0] = 1.000000;
	out_set.data[28][1] = 0.000000;
	out_set.data[29][0] = 1.000000;
	out_set.data[29][1] = 0.000000;
	
	net.train(in_set,out_set,loss,0.2,1000);
	System.out.println(net.forward(in_set));
    }
}
