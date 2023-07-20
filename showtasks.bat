call .\runcrud
@rem Dont forget to remove "go to end from runcrud"

if "%ERRORLEVEL%" == "0" goto startBrowser
echo.
echo Compilation error
goto fail

:fail
echo error occurred ! Initialization failed
echo.
goto end

:startBrowser
start chrome http://localhost:8080/crud/v1/tasks

:end
echo END