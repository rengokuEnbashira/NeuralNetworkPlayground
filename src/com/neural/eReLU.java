package com.neural;

import com.math.*;

public class eReLU extends eLayer{

    public eMatrix tmp_in;
    public eMatrix tmp_out;
    public eMatrix tmp_err;

    public eMatrix forward(eMatrix inp){
	tmp_in = inp;
	tmp_out = inp.applyFunc((x)->x>0?x:0.001*x);
	return tmp_out;
    }

    public eMatrix backward(eMatrix err){
	tmp_err = err;
	return tmp_out.applyFunc((x)->x>0?1:0.001).times(err);
    }

    public void update(double learning_rate){
	
    }
}
