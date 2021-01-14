package com.neural;

import com.math.*;

public class eLinear extends eLayer{

    eMatrix W;
    eMatrix b;
    
    eMatrix tmp_in;
    eMatrix tmp_out;
    eMatrix tmp_err;
    
    public eLinear(int num_inp, int num_out){
	this.W = new eMatrix(num_inp,num_out);
	this.b = new eMatrix(1,num_out);
	this.W.random();
	this.b.random();
	this.W = this.W.applyFunc((x)->2*x - 1);
	this.b = this.b.applyFunc((x)->2*x - 1);
    }
    public eMatrix forward(eMatrix inp){
	tmp_in = inp;
	tmp_out = inp.dot(this.W).add(this.b);
	return tmp_out;
    }
    public eMatrix backward(eMatrix err){
	tmp_err = err;
	return err.dot(this.W.transpose());
    }
    public void update(double learning_rate){
	eMatrix delta = tmp_in.transpose().dot(tmp_err);
	this.W = this.W.diff(delta.times(learning_rate));
	this.b = this.b.diff(tmp_err.sum(0).times(learning_rate));
    }

    public String toString(){
	return "W:\n" + this.W + "\nb:\n" + this.b;
    }
}


