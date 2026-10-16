#!/bin/sh
# Bootstrap Gradle Wrapper resmi versi 8.9 jika binary wrapper belum ada.
# Saat project pertama diunduh, jalankan ./gradlew --version sebelum Android Studio Sync.
set -eu
APP_HOME=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
WRAPPER_JAR="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"
WRAPPER_URL="https://raw.githubusercontent.com/gradle/gradle/v8.9.0/gradle/wrapper/gradle-wrapper.jar"
if [ ! -s "$WRAPPER_JAR" ]; then
    echo "Mengunduh Gradle wrapper resmi 8.9 ..."
    if command -v curl >/dev/null 2>&1; then
        curl -fL --retry 2 "$WRAPPER_URL" -o "$WRAPPER_JAR"
    elif command -v wget >/dev/null 2>&1; then
        wget "$WRAPPER_URL" -O "$WRAPPER_JAR"
    else
        echo "Error: dibutuhkan curl atau wget untuk mengunduh gradle-wrapper.jar" >&2
        exit 1
    fi
fi
if [ -n "${JAVA_HOME:-}" ]; then
    JAVA="$JAVA_HOME/bin/java"
else
    JAVA=java
fi
exec "$JAVA" -Dorg.gradle.appname=gradlew -classpath "$WRAPPER_JAR" org.gradle.wrapper.GradleWrapperMain "$@"
