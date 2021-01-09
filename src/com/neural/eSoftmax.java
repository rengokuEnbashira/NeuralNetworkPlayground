package com.neural;

import com.math.*;

public class eSoftmax extends eLayer{

    eMatrix tmp_in;
    eMatrix tmp_out;
    eMatrix tmp_err;
    
    public eMatrix forward(eMatrix inp){
	eMatrix tmp = inp.applyFunc((x)->Math.exp(x));
	eMatrix acc = tmp.sum(0);
	tmp_in = inp;
	tmp_out = tmp.div(acc);
	return tmp_out;
    }

    public eMatrix backward(eMatrix err){
	tmp_err = err;
	return tmp_out.applyFunc((x)->x - x*x).times(err);
    }

    public void update(double learning_rate){

    }
}
