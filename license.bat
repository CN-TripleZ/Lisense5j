@echo off
rem set JAVA_HOME
rem JAVA_HOME=


set JAVA_RUN=java

if not "%JAVA_HOME%" == "" set JAVA_RUN="%JAVA_HOME%/bin/java"

set MAIN_CLASS=com.license.client.License5j
set CLASSPATH="build/classes;lib/lic.jar;classes;bin;"

""%JAVA_RUN%"" -cp %CLASSPATH% %MAIN_CLASS%
pause