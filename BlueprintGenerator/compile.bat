set WORKING_DIRECTORY=%cd%
cd C:\Program Files\Java\jdk1.8.0_25\bin
jar.exe cfmv %WORKING_DIRECTORY%\manifest.mf %WORKING_DIRECTORY%.jar %WORKING_DIRECTORY%
pause