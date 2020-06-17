
if not DEFINED IS_MINIMIZED set IS_MINIMIZED=1 && start "" /min "%~dpnx0" %* && exit
    @echo off
    SETLOCAL
    set "jre=%~dp0jre\bin\java.exe"
    set "jar=%~dp0installer\installer-win.jar"
    CALL %jre% -splash:assets/splash.png -jar %jar%
    EXIT /B %ERRORLEVEL%
exit