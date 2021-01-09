package com.neural;

import com.math.*;

public class eTanh extends eLayer{

    eMatrix tmp_in;
    eMatrix tmp_out;
    eMatrix tmp_err;
    
    public eMatrix forward(eMatrix inp){
	tmp_in = inp;
	tmp_out = inp.applyFunc((x) -> (1.0 - Math.exp(-x))/(1.0 + Math.exp(-x)));
	return tmp_out;
    }

    public eMatrix backward(eMatrix err){
	tmp_err = err;
	return tmp_out.applyFunc((x) -> (1 - x*x)/2.0).times(err);
    }

    public void update(double learning_rate){

    }

}
