test:
	javac -sourcepath src/ -d build/ src/com/test/eTest.java
	java -classpath build/ com.test.eTest
	rm -r build/com
lib:
	javac -sourcepath src/ -d build/ src/com/math/*.java src/com/neural/*.java
	jar cf eNeuralNetwork.jar -C build/ .
	rm -r build/com
