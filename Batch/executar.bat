@echo off
ECHO ==========================================
ECHO EXECUTANDO ANALISE ARVORES IFOOD
ECHO ==========================================

REM ** O comando 'java' deve ser capaz de ser executado a partir do PATH do Windows.
REM ** O '-cp ..\bin' define o caminho para a pasta 'bin' (um nível acima) como Classpath.
REM ** 'AnaliseArvores' é o nome da classe principal.

java -cp ..\bin AnaliseArvores

PAUSE