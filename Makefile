test:
	javac -sourcepath src/ -d build/ src/com/test/eTest.java
	java -classpath build/ com.test.eTest
