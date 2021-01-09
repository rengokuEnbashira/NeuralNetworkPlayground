package com.neural;

import com.math.*;

public abstract class eNeuralNetwork{
    public abstract eMatrix forward(eMatrix inp);
    public abstract eMatrix backward(eMatrix err);
    public abstract void update(double learning_rate);
    public abstract void train(eMatrix inp, eMatrix target, eLoss loss, double learning_rate, int maxIt);
}
