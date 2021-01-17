package com.test;

import com.math.*;

public class eTestMatrix{
    public static void main(String args[]){
	eMatrix m1 = new eMatrix(3,3);
	eMatrix m2 = new eMatrix(3,3);
	eMatrix f1 = new eMatrix(2,2);
	
	m1.random();
	m2.random();
	System.out.println("m1: \n" + m1);
	System.out.println("m2: \n" + m2);
	System.out.println("m1 + m2: \n" + (m1.add(m2)));
	System.out.println("m1 - m2: \n" + (m1.diff(m2)));
	System.out.println("m1 * m2: \n" + (m1.times(m2)));
	System.out.println("m1 / m2: \n" + (m1.div(m2)));
	System.out.println("m1.dot(m2): \n" + (m1.dot(m2)));

	System.out.println("m1 + 1: \n" + m1.add(1));
	System.out.println("m2 * 0.5: \n" + m2.times(0.5));

	System.out.println("transpose of m1: \n" + m1.transpose());
	System.out.println("sum of all elements of m2: \n" + m2.sum());
	System.out.println("sum of rows of m1: \n" + m1.sum(0));
	System.out.println("sum of cols of m2: \n" + m2.sum(1));

	System.out.println("exp(m1): \n" + m1.applyFunc((x)->Math.exp(x)));

	f1.eye();
	System.out.println("f1: \n" + f1);
	System.out.println("m1.conv(f1): \n" + m1.conv(f1,0,1));
    }
}
