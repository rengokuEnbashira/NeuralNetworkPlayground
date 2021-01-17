testMatrix:
	javac -sourcepath src/ -d build/ src/com/test/eTestMatrix.java
	java -classpath build/ com.test.eTestMatrix
testNN:
	javac -sourcepath src/ -d build/ src/com/test/eTestNeuralNetwork.java
	java -classpath build/ com.test.eTestNeuralNetwork
clean:
	rm -r build/com
lib:
	javac -sourcepath src/ -d build/ src/com/math/*.java src/com/neural/*.java
	jar cf eNeuralNetwork.jar -C build/ .
