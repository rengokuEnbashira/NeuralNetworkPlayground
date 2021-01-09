package com.neural;

import com.math.*;

public class eSoftmax extends eLayer{

    eMatrix tmp_in;
    eMatrix tmp_out;
    eMatrix tmp_err;
    
    public eMatrix forward(eMatrix inp){
	eMatrix tmp = inp.applyFunc((x)->Math.exp(x));
	eMatrix acc = tmp.sum(1);
	tmp_in = inp;
	tmp_out = tmp.div(acc);
	return tmp_out;
    }

    public eMatrix backward(eMatrix err){
	eMatrix tmp = err.times(tmp_out);
	tmp_err = err;
	return tmp.diff(tmp_out.times(tmp.sum(1)));
    }

    public void update(double learning_rate){

    }
}
