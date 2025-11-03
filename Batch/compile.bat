@echo off
ECHO ==================================================
ECHO       COMPILANDO PROJETO ARVORES iFOOD
ECHO ==================================================

REM --- 1. Limpeza de classes antigas (mantida para garantir a versão 17) ---
del ..\bin\src\*.class 2>nul
del ..\bin\*.class 2>nul
if not exist ..\bin mkdir ..\bin

echo Compilando classes para compatibilidade com Java 17 (versao 61.0)...

REM --- 2. Compilação com Target 17 E Encoding UTF-8 (soluciona o erro de caractere) ---
javac -d ..\bin -source 17 -target 17 -encoding UTF-8 ..\src\*.java

echo.
echo Compilacao concluida.
echo Arquivos compilados na pasta 'bin'
echo.

pause