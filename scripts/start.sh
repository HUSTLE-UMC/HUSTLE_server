#!/bin/bash

HOME_ROOT = "/home/ubuntu"
PROJECT_ROOT="/home/ubuntu/deploy"
JAR_DIRECTORY="$PROJECT_ROOT/build/libs"

cd $JAR_DIRECTORY

LATEST_JAR=$(ls -t | grep jar -m 1)
JAR="$JAR_DIRECTORY/$LATEST_JAR"

APP_LOG="$HOME_ROOT/logs/application.log"
ERROR_LOG="$HOME_ROOT/logs/error.log"
START_LOG="$HOME_ROOT/logs/start.log"

NOW=$(date +%c)

echo "[$NOW] > $JAR 실행" >> $START_LOG
nohup java -jar $JAR --jasypt.encryptor.password='secret_key' --spring.profiles.active=prod > $APP_LOG 2> $ERROR_LOG &

SERVICE_PID=$(pgrep -f $JAR)
echo "[$NOW] > 서비스 PID: $SERVICE_PID" >> $START_LOG
