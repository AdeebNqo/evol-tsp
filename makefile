*.class: src/*.java
	javac src/*.java  && mv src/*.class lib
run:
	appletviewer file:"$(shell pwd)/EA-TSP.htm"
