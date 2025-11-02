@echo off
echo ==================================================
echo     EXECUTANDO ANALISE ARVORES IFOOD
echo ==================================================
echo.

if not exist ..\bin (
    echo ❌ Pasta de binarios (..\bin) nao encontrada!
    echo 📦 Execute compile.bat primeiro!
    pause
    exit /b 1
)

echo 🚀 Executando analise...
REM O Classpath (cp) aponta para o diretorio raiz do codigo compilado (..\bin).
REM A classe principal precisa do nome completo do pacote: src.AnaliseArvores.
java -cp "..\bin" src.AnaliseArvores

echo.
pause