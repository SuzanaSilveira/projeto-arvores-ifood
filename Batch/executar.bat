@echo off
ECHO ==========================================
ECHO EXECUTANDO ANALISE ARVORES IFOOD
ECHO ==========================================

REM ** Navega para a pasta pai, onde 'bin' e 'data' estão.
cd ..

REM ** Adiciona -Xss2m para aumentar o tamanho da pilha para recursões profundas (StackOverflowError).
java -cp bin -Xss2m src.AnaliseArvores

PAUSE