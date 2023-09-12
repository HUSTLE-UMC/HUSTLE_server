#!/bin/bash

HOME_ROOT="/home/ubuntu"
PROJECT_ROOT="/home/ubuntu/deploy"
JAR_DIRECTORY="$PROJECT_ROOT/build/libs"

cd $JAR_DIRECTORY

LATEST_JAR=$(ls -t | grep jar -m 1)
JAR="$ROOT_PATH/$LATEST_JAR"

APP_LOG="$HOME_ROOT/logs/application.log"
ERROR_LOG="$HOME_ROOT/logs/error.log"
STOP_LOG="$HOME_ROOT/logs/stop.log"

SERVICE_PID=$(pgrep -f $JAR) # 실행중인 Spring 서버의 PID

if [ -z "$SERVICE_PID" ]; then
  echo "Not Found" >> $STOP_LOG
else
  echo "Service Terminated" >> $STOP_LOG
  kill "$SERVICE_PID"
fi
