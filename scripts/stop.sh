#!/bin/bash

ROOT_PATH="/home/ubuntu"

cd $ROOT_PATH

LATEST_JAR=$(ls -t | grep jar -m 1)
JAR="$ROOT_PATH/$LATEST_JAR"

APP_LOG="$ROOT_PATH/logs/application.log"
ERROR_LOG="$ROOT_PATH/logs/error.log"
START_LOG="$ROOT_PATH/logs/start.log"

SERVICE_PID=$(pgrep -f $JAR) # 실행중인 Spring 서버의 PID

if [ -z "$SERVICE_PID" ]; then
  echo "Not Found" >> $STOP_LOG
else
  echo "Service Terminated" >> $STOP_LOG
  kill "$SERVICE_PID"
fi
