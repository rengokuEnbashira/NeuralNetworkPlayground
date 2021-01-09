package com.neural;

import com.math.*;

public abstract class eLoss{
    public abstract double loss(eMatrix out, eMatrix target);
    public abstract eMatrix grad_loss();
}
