@echo off
echo ==================================================
echo    EXECUTANDO ANALISE ARVORES iFOOD
echo ==================================================
echo.

if not exist bin (
    echo ❌ Pasta 'bin' nao encontrada!
    echo 📦 Execute compile.bat primeiro!
    pause
    exit /b 1
)

echo 🚀 Executando analise...
java -cp "bin;lib/*" AnaliseArvores

echo.
pause