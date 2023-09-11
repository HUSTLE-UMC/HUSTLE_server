#!/bin/bash

ROOT_PATH="/home/ubuntu"

cd $ROOT_PATH

LATEST_JAR=$(ls -t | grep jar -m 1)
JAR="$ROOT_PATH/$LATEST_JAR"

APP_LOG="$ROOT_PATH/logs/application.log"
ERROR_LOG="$ROOT_PATH/logs/error.log"
START_LOG="$ROOT_PATH/logs/start.log"

NOW=$(date +%c)

echo "[$NOW] > $JAR 실행" >> $START_LOG
nohup java -jar $JAR --jasypt.encryptor.password='secret_key' --spring.profiles.active=prod > $APP_LOG 2> $ERROR_LOG &

SERVICE_PID=$(pgrep -f $JAR)
echo "[$NOW] > 서비스 PID: $SERVICE_PID" >> $START_LOG
