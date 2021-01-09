package com.math;

public class eMatrix{
    
    public int rows,cols;
    public double data[][];
    
    public eMatrix(int rows,int cols){
	this.rows = rows;
	this.cols = cols;
	this.data = new double[rows][cols];	
    }
    public String toString(){
	String s = "";
	for(int i = 0;i<this.rows;i++){
	    for(int j = 0;j<this.cols;j++)
		s += this.data[i][j] + " ";
	    s += "\n";
	}
	return s;
    }
    public void zeros(){
	for(int i = 0;i<this.rows;i++){
	    for(int j = 0;j<this.cols;j++)
		this.data[i][j] = 0;
	}
    }
    public void ones(){
	for(int i = 0;i<this.rows;i++){
	    for(int j = 0;j<this.cols;j++)
		this.data[i][j] = 0;
	}
    }
    public void random(){
	for(int i = 0;i<this.rows;i++){
	    for(int j = 0;j<this.cols;j++)
		this.data[i][j] = Math.random();
	}
    }
    public void eye(){
	this.zeros();
	for(int i = 0;i<this.rows;i++)
	    this.data[i][i] = 1;
    }
    public eMatrix add(eMatrix M){
	eMatrix out = new eMatrix(this.rows,this.cols);
	if(M.cols == 1){
	    for(int i = 0;i<this.rows;i++){
		for(int j = 0;j<this.cols;j++)
		    out.data[i][j] = this.data[i][j] + M.data[i][0];
	    }
	}
	else if(M.rows == 1){
	    for(int i = 0;i<this.rows;i++){
		for(int j = 0;j<this.cols;j++)
		    out.data[i][j] = this.data[i][j] + M.data[0][j];
	    }
	}
	else{
	    for(int i = 0;i<this.rows;i++){
		for(int j = 0;j<this.cols;j++)
		    out.data[i][j] = this.data[i][j] + M.data[i][j];
	    }
	}
	return out;
    }
    public eMatrix add(double num){
	eMatrix out = new eMatrix(this.rows,this.cols);
	for(int i = 0;i<this.rows;i++){
	    for(int j = 0;j<this.cols;j++)
		out.data[i][j] = this.data[i][j] + num;
	}
	return out;
    }
    public eMatrix diff(eMatrix M){
	eMatrix out = new eMatrix(this.rows,this.cols);
	for(int i = 0;i<this.rows;i++){
	    for(int j = 0;j<this.cols;j++)
		out.data[i][j] = this.data[i][j] - M.data[i][j];
	}
	return out;
    }
    public eMatrix diff(double num){
	eMatrix out = new eMatrix(this.rows,this.cols);
	for(int i = 0;i<this.rows;i++){
	    for(int j = 0;j<this.cols;j++)
		out.data[i][j] = this.data[i][j] - num;
	}
	return out;
    }
    public eMatrix times(eMatrix M){
	eMatrix out = new eMatrix(this.rows,this.cols);
	if(M.rows == 1){
	    for(int i = 0;i<this.rows;i++){
		for(int j = 0;j<this.cols;j++)
		    out.data[i][j] = this.data[i][j] * M.data[0][j];
	    }
	}
	else if(M.cols == 1){
	    for(int i = 0;i<this.rows;i++){
		for(int j = 0;j<this.cols;j++)
		    out.data[i][j] = this.data[i][j] * M.data[i][0];
	    }
	}
	else{
	    for(int i = 0;i<this.rows;i++){
		for(int j = 0;j<this.cols;j++)
		    out.data[i][j] = this.data[i][j] * M.data[i][j];
	    }
	}
	return out;
    }
    public eMatrix times(double num){
	eMatrix out = new eMatrix(this.rows,this.cols);
	for(int i = 0;i<this.rows;i++){
	    for(int j = 0;j<this.cols;j++)
		out.data[i][j] = this.data[i][j] * num;
	}
	return out;
    }
    public eMatrix div(eMatrix M){
	eMatrix out = new eMatrix(this.rows,this.cols);
	if(M.rows == 1){
	    for(int i = 0;i<this.rows;i++){
		for(int j = 0;j<this.cols;j++)
		    out.data[i][j] = this.data[i][j] / M.data[0][j];
	    }
	}
	else if(M.cols == 1){
	    for(int i = 0;i<this.rows;i++){
		for(int j = 0;j<this.cols;j++)
		    out.data[i][j] = this.data[i][j] / M.data[i][0];
	    }
	}
	else{
	    for(int i = 0;i<this.rows;i++){
		for(int j = 0;j<this.cols;j++)
		    out.data[i][j] = this.data[i][j] / M.data[i][j];
	    }
	}
	return out;
    }
    public eMatrix div(double num){
	eMatrix out = new eMatrix(this.rows,this.cols);
	for(int i = 0;i<this.rows;i++){
	    for(int j = 0;j<this.cols;j++)
		out.data[i][j] = this.data[i][j] / num;
	}
	return out;
    }
    public eMatrix dot(eMatrix M){
	eMatrix out = new eMatrix(this.rows,M.cols);
	double s;
	for(int i = 0;i<this.rows;i++){
	    for(int j = 0;j<M.cols;j++){
		s = 0;
		for(int k = 0;k<this.cols;k++)
		    s += this.data[i][k]*M.data[k][j];
		out.data[i][j] = s;
	    }
	}
	return out;
    }
    public eMatrix applyFunc(eFunction funf){
	eMatrix out = new eMatrix(this.rows,this.cols);
	for(int i = 0;i<this.rows;i++){
	    for(int j = 0;j<this.cols;j++)
		out.data[i][j] = funf.op(this.data[i][j]);
	}
	return out;
    }
    public eMatrix transpose(){
	eMatrix out = new eMatrix(this.cols,this.rows);
	for(int i = 0;i<this.rows;i++)
	    for(int j = 0;j<this.cols;j++)
		out.data[j][i] = this.data[i][j];
	return out;
    }
    public double sum(){
	double s = 0;
	for(int i = 0;i<this.rows;i++)
	    for(int j = 0;j<this.cols;j++)
		s += this.data[i][j];
	return s;
    }
    public eMatrix sum(int axis){
	eMatrix out = null;
	if(axis == 0){
	    out = new eMatrix(1,this.cols);
	    out.zeros();
	    for(int i = 0;i<this.rows;i++)
		for(int j = 0;j<this.cols;j++)
		    out.data[0][j] += this.data[i][j];
	}
	else if(axis == 1){
	    out = new eMatrix(this.rows,1);
	    out.zeros();
	    for(int i = 0;i<this.rows;i++)
		for(int j = 0;j<this.cols;j++)
		    out.data[i][0] += this.data[i][j];
	}
	return out;
    }
}
