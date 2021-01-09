package com.neural;

import com.math.*;

public class eCrossEntropyLoss extends eLoss{

    eMatrix tmp_out;
    eMatrix tmp_target;
    
    public double loss(eMatrix out, eMatrix target){
	tmp_out = out;
	tmp_target = target;
	return out.applyFunc((x)->Math.log(x)).times(target).times(-1).sum();
    }

    public eMatrix grad_loss(){
	return tmp_target.div(tmp_out).times(-1);
    }
}
