package com.test;

import com.math.*;

public class eDataGenerator{
    public static eMatrix[] generateClassificationPoints(int N){
	eMatrix[] out = new eMatrix[2];
	double m,b,y;

	out[0] = new eMatrix(N,2);
	out[1] = new eMatrix(N,2);

	out[0].random();
	out[0] = out[0].applyFunc((x)->2*x-1);

	m = Math.random() - 0.5;
	b = Math.random() - 0.5;

	for(int i = 0;i < N;i++){
	    y = out[0].data[i][0]*m + b;
	    out[1].data[i][0] = out[0].data[i][1] > y?0:1;
	    out[1].data[i][1] = out[0].data[i][1] > y?1:0;
	}

	return out;
    }
}
