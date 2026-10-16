@echo off
setlocal
set "APP_HOME=%~dp0"
set "WRAPPER_JAR=%APP_HOME%gradle\wrapper\gradle-wrapper.jar"
if not exist "%WRAPPER_JAR%" (
    echo Mengunduh Gradle wrapper resmi 8.9 ...
    powershell -NoProfile -ExecutionPolicy Bypass -Command "Invoke-WebRequest -Uri 'https://raw.githubusercontent.com/gradle/gradle/v8.9.0/gradle/wrapper/gradle-wrapper.jar' -OutFile '%WRAPPER_JAR%'"
    if errorlevel 1 (
        echo Gagal mengunduh Gradle wrapper. Periksa koneksi internet.
        exit /b 1
    )
)
if defined JAVA_HOME (
    set "JAVA_EXE=%JAVA_HOME%\bin\java.exe"
) else (
    set "JAVA_EXE=java.exe"
)
"%JAVA_EXE%" -Dorg.gradle.appname=gradlew -classpath "%WRAPPER_JAR%" org.gradle.wrapper.GradleWrapperMain %*
exit /b %ERRORLEVEL%
