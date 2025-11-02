@echo off
echo ==================================================
echo     COMPILANDO PROJETO ARVORES iFOOD
echo ==================================================
echo.

REM --- 1. Cria a pasta 'bin' se ela não existir ---
if not exist bin mkdir bin

echo Compilando classes...
echo.

REM --- 2. Compila os arquivos de forma robusta sem usar a pasta Lib ---
REM O FOR resolve o problema do curinga *.java em ambientes como PowerShell.
cd src

FOR %%f IN (*.java) DO (
    javac -d ..\bin %%f
)

REM --- 3. Volta para a pasta 'Batch' ---
cd ..\Batch

echo.
echo Compilacao concluida (verifique os erros acima).
echo Arquivos compilados na pasta 'bin'
echo.

pause