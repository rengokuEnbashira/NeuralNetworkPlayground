package com.neural;

import com.math.*;

public abstract class eLayer{
    public abstract eMatrix forward(eMatrix inp);
    public abstract eMatrix backward(eMatrix err);
    public abstract void update(double learning_rate);
}
