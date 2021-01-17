package com.math.*;

public class eTensor{

    public eMatrix[] data;
    
    public eTensor(int num_matrices){
	data = new eMatrix[num_matrices];
    }
    
    public eTensor conv(eTensor T, int padding, int stride){
	eTensor out = new eTensor(this.num_matrices * T.num_matrices);
	for(int i = 0;i < T.num_matrices;i++)
	    for(int j = 0;j < this.num_matrices;j++)
		out.data[i*this.num_matrices + j] = this.data[i].conv(T.data[j]);
	return out;
    }
    
}
