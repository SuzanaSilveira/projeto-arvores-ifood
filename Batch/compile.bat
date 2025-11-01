@echo off
echo ==================================================
echo    COMPILANDO PROJETO ARVORES iFOOD
echo ==================================================
echo.

if not exist bin mkdir bin

echo 📦 Compilando classes...
javac -cp "lib/*" -d bin src/*.java

if %errorlevel% equ 0 (
    echo ✅ Compilacao concluida com sucesso!
    echo.
    echo Arquivos compilados na pasta 'bin'
) else (
    echo ❌ Erro na compilacao!
)

echo.
pause