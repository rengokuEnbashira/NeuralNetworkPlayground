import numpy as np
import matplotlib.pyplot as plt 
from scipy.signal import convolve2d

SIGMOID = 0
TANH = 1
RELU = 2

class eLinear:
    def __init__(self,ni,no):
        self.ni = ni
        self.no = no
        self.W = np.random.random((ni,no))
        self.b = np.random.random(no)
    def forward(self,inp):
        self.tmp_in = inp
        self.tmp_out = inp.dot(self.W) + self.b
        return self.tmp_out
    def backward(self,err):
        self.tmp_err = err
        return err.dot(self.W.T)
    def update(self,learning_rate):
        delta = self.tmp_in.T.dot(self.tmp_err)
        self.W -= learning_rate*delta
        self.b -= learning_rate*self.tmp_err.sum(0)

class eSigmoid:
    def forward(self,inp):
        self.tmp_in = inp
        self.tmp_out = 1/(1 + np.exp(-inp))
        return self.tmp_out
    def backward(self,err):
        self.tmp_err = err
        return self.tmp_out*(1 - self.tmp_out)*err
    def update(self,learning_rate):
        return None

class eTanh:
    def forward(self,inp):
        self.tmp_in = inp
        self.tmp_out = (1 - np.exp(-inp))/(1 + np.exp(-inp))
        return self.tmp_out
    def backward(self,err):
        self.tmp_err = err
        return ((1 - self.tmp_out**2)/2.0)*err
    def update(self,learning_rate):
        return None

class eReLU:
    def forward(self,inp):
        self.tmp_in = inp
        self.tmp_out = inp*0.01
        self.tmp_out[np.where(inp>0)] = inp[np.where(inp>0)]
        return self.tmp_out
    def backward(self,err):
        self.tmp_err = err
        tmp = np.ones(self.tmp_out.shape)*0.01
        tmp[np.where(self.tmp_out > 0)] = 1
        return tmp*err
    def update(self,learning_rate):
        return None

class eSoftmax:
    def forward(self,inp):
        self.tmp_in = inp
        tmp = np.exp(inp)
        self.tmp_out = tmp/tmp.sum(1).reshape(len(tmp),1)
        return self.tmp_out
    def backward(self,err):
        self.tmp_err = err
        tmp = err*self.tmp_out
        return tmp - self.tmp_out*tmp.sum(1).reshape(len(tmp),1)
    def update(self,learning_rate):
        return None

conv = lambda i,f : convolve2d(i,f)[len(f)-1:-len(f)+1,len(f)-1:-len(f)+1]
class eConv:
    def __init__(self,num_filters,filter_size):
        self.num_filters = num_filters
        self.filters = np.random.random((num_filters,filter_size[0],filter_size[1]))
    def forward(self,inp):
        self.tmp_in = inp
        self.tmp_out = []
        for I in inp:
            for F in self.filters:
                self.tmp_out.append(conv(I,F))
        self.tmp_out = np.array(self.tmp_out)
        return self.tmp_out
    def backward(self,err):
        self.tmp_err = err
        fn,fr,fc = self.filters.shape
        em,er,ec = err.shape
        n = em//fn
        tmp = np.zeros((n,er+fr-1,ec+fc-1))
        for i,e in enumerate(err):
            tmp[i//fn] += convolve2d(np.flip(self.filters[i%fn]),e)
        return tmp
    def update(self,learning_rate):
        for i,e in enumerate(self.tmp_err):
            delta = conv(self.tmp_in[i//self.num_filters],e)
            self.filters[i%self.num_filters] -= learning_rate*delta

class eMaxPooling:
    def __init__(self,pool_size=(2,2)):
        self.pool_size = pool_size
    def forward(self,inp):
        self.tmp_in = inp
        n,ir,ic = inp.shape
        pr,pc = self.pool_size
        r,c = ir//pr,ic//pc
        self.tmp_ = np.zeros((n,ir,ic))
        self.tmp_out = np.zeros((n,r,c))
        for idx,i in enumerate(inp):
            for j in range(r):
                for k in range(c):
                    ti = i[pr*j:pr*(j+1),pc*k:pc*(k+1)]
                    tt = self.tmp_[idx,pr*j:pr*(j+1),pc*k:pc*(k+1)]
                    maxi = np.max(ti)
                    max_idx = np.where(ti == maxi)
                    self.tmp_out[idx][j][k] = maxi
                    tt[max_idx] = 1
        return self.tmp_out
    def backward(self,err):
        pr,pc = self.pool_size
        _,er,ec = err.shape
        for i,e in enumerate(err):
            for j in range(er):
                for k in range(ec): 
                    self.tmp_[i,pr*j:pr*(j+1),pc*k:pc*(k+1)] *= e[j][k]
        return self.tmp_
    def update(self,learning_rate):
        pass

class eMeanSquareLoss:
    def loss(self,output,target):
        self.tmp_err = output - target
        return np.sum(self.tmp_err*self.tmp_err)/2.0
    def grad_loss(self):
        return self.tmp_err

class eCrossEntropyLoss:
    def loss(self,output,target):
        self.tmp_out = output
        self.tmp_target = target
        return (-target*np.log(output)).sum()
    def grad_loss(self):
        return -self.tmp_target/self.tmp_out

class eSequential:
    def __init__(self):
        self.layers = []
    def addLayer(self,layer):
        self.layers.append(layer)
    def forward(self,inp):
        tmp = inp
        for layer in self.layers:
            tmp = layer.forward(tmp)
        return tmp
    def backward(self,err):
        tmp = err
        for layer in reversed(self.layers):
            tmp = layer.backward(tmp)
        return tmp
    def update(self,learning_rate):
        for layer in self.layers:
            layer.update(learning_rate)
    def train(self,in_set,out_set,loss,learning_rate=1,maxIt=1000,showLoss=False):
        tmp = []
        for i in range(maxIt):
            out = self.forward(in_set)
            tmp.append(loss.loss(out,out_set))
            err = loss.grad_loss()
            self.backward(loss.grad_loss())
            self.update(learning_rate)
        if showLoss:
            plt.plot(np.array(tmp))
            plt.title("Loss evolution")
            plt.xlabel("ephocs")
            plt.ylabel("loss")
            plt.grid()
            plt.show()
        return tmp

class eMLPClassifier:
    def __init__(self,num_neurons,act_fun=SIGMOID):
        self.layers = []
        for i,j in zip(num_neurons[:-1],num_neurons[1:]):
            self.layers.append(eLinear(i,j))
            if act_fun == TANH:
                self.layers.append(eTanh())
            elif act_fun == RELU:
                self.layers.append(eReLU())
            else:
                self.layers.append(eSigmoid())
        self.layers.append(eSoftmax())
    def forward(self,inp):
        tmp = inp
        for layer in self.layers:
            tmp = layer.forward(tmp)
        return tmp
    def backward(self,err):
        tmp = err
        for layer in reversed(self.layers):
            tmp = layer.backward(tmp)
        return tmp
    def update(self,learning_rate):
        for layer in self.layers:
            layer.update(learning_rate)
    def train(self,in_set,out_set,learning_rate=1,maxIt=1000,showLoss=False):
        tmp = []
        loss = eCrossEntropyLoss();
        for i in range(maxIt):
            out = self.forward(in_set)
            tmp.append(loss.loss(out,out_set))
            err = loss.grad_loss()
            self.backward(loss.grad_loss())
            self.update(learning_rate)
        if showLoss:
            plt.plot(np.array(tmp))
            plt.title("Loss evolution")
            plt.xlabel("ephocs")
            plt.ylabel("loss")
            plt.grid()
            plt.show()
        return tmp

def generate_data(N):
    x_train = np.random.random([N,2])*2 - 1
    x = x_train[:,0]
    y = x_train[:,1]
    m = np.random.random() - 0.5
    b = np.random.random() - 0.5
    t = (y>(m*x - b)).astype(np.int)
    y_train = np.zeros([N,2])
    y_train[(range(N),t)] = 1
    return x_train,y_train

if __name__ == "__main__":
    N = 20
    x_train, y_train = generate_data(N)
     
    # seq = eSequential()
    # seq.addLayer(eLinear(2,5))
    # seq.addLayer(eReLU())
    # seq.addLayer(eLinear(5,2))
    # seq.addLayer(eReLU())
    # seq.addLayer(eSoftmax())
    # seq.train(x_train,y_train,eCrossEntropyLoss(),learning_rate=0.1,maxIt=100,showLoss=True)
    # net = eMLPClassifier([2,5,2],act_fun=RELU)
    # net.train(x_train,y_train,learning_rate=0.1,maxIt=500,showLoss=True)
    # print(y_train)
    # print(net.forward(x_train))
    # x = np.random.random([10,32,32])
    # c = eConv(3,(4,4))
    # d = c.forward(x)
    # e = c.backward(d)
    # c.update(0.5)
    # print(x.shape)
    # print(d.shape)
    # print(e.shape)
    x = np.random.random([3,4,4])
    p = eMaxPooling()
    d = p.forward(x)
    y = np.random.random(d.shape)
    e = p.backward(y)
    print(x)
    print(y)
    print(e)
