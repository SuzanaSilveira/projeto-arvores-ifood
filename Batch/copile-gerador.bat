@echo off
chcp 65001 > nul
javac -d bin -encoding UTF-8 src/GeradorCSV.java
java -cp bin GeradorCSV
pause

