package com.neural;

import com.math.*;

public class eMeanSquareLoss extends eLoss{

    eMatrix tmp_out;
    eMatrix tmp_target;
    
    public double loss(eMatrix out, eMatrix target){
	eMatrix err = out.diff(target);
	tmp_out = out;
	tmp_target = target;
	return err.times(err).sum()/2.0;
    }
    public eMatrix grad_loss(){
	return tmp_out.diff(tmp_target);
    }
}
