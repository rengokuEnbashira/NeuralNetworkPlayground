package com.neural;

import com.math.*;

public class eSigmoid extends eLayer{

    eMatrix tmp_inp;
    eMatrix tmp_out;
    eMatrix tmp_err;
    
    public eMatrix forward(eMatrix inp){
	tmp_inp = inp;
	tmp_out = inp.applyFunc((x)-> 1.0/(1.0 + Math.exp(-x))); 
	return tmp_out;
    }
    public eMatrix backward(eMatrix err){
	tmp_err = err;
	return tmp_out.times(tmp_out.times(-1).add(1)).times(err);
    }
    public void update(double learning_rate){
	
    }
}
