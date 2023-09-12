#!/bin/bash

HOME_ROOT="/home/ubuntu"

STOP_LOG="$HOME_ROOT/logs/stop.log"

SERVICE_PID=$(pgrep -f jar) # 실행중인 Spring 서버의 PID

if [ -z "$SERVICE_PID" ]; then
  echo "Not Found" >> $STOP_LOG
else
  echo "Service Terminated" >> $STOP_LOG
  kill "$SERVICE_PID"
fi
