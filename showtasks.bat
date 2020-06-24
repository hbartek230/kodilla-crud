call runcrud
if "%ERRORLEVEL%" == "0" goto openbrowser
echo RUNCRUD has errors - breaking work
goto end

:openbrowser
start chrome http://localhost:8080/crud/v1/task/getTasks
goto end

:end
echo.
echo Work is finished.