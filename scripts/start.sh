#!/bin/bash

HOME_ROOT="/home/ubuntu"

source $HOME_ROOT/.bash_profile

PROJECT_ROOT="/home/ubuntu/deploy"
JAR_DIRECTORY="$PROJECT_ROOT/build/libs"

cd $JAR_DIRECTORY

LATEST_JAR=$(ls -t | grep -v "plain" | grep jar -m 1)
JAR="$JAR_DIRECTORY/$LATEST_JAR"

APP_LOG="$HOME_ROOT/logs/application.log"
ERROR_LOG="$HOME_ROOT/logs/error.log"
START_LOG="$HOME_ROOT/logs/start.log"

NOW=$(date +%c)

echo "> 현재 구동중인 profile 확인"
CURRENT_PROFILE=$(curl -s http://localhost/profile)
echo "> $CURRENT_PROFILE"
if [ $CURRENT_PROFILE == prod1 ]
then
	IDLE_PROFILE=prod2
	IDLE_PORT=8081
elif [ $CURRENT_PROFILE == prod2 ]
then
	IDLE_PROFILE=prod1
	IDLE_PORT=8080
else
	echo "> 일치하는 Profile이 없습니다. Profile: $CURRENT_PROFILE"
	echo "> prod1을 할당합니다. IDLE_PROFILE: prod1"
	IDLE_PROFILE=prod1
	IDLE_PORT=8080
fi


echo "[$NOW] > $JAR 실행" >> $START_LOG
nohup java -jar $JAR --jasypt.encryptor.password=$JASYPT_PASSWORD --spring.profiles.active=$IDLE_PROFILE > $APP_LOG 2> $ERROR_LOG &

SERVICE_PID=$(pgrep -f $JAR)
echo "[$NOW] > 서비스 PID: $SERVICE_PID" >> $START_LOG

echo "> $IDLE_PROFILE 10초 후 Health check 시작"
echo "> curl -s http://localhost:$IDLE_PORT/actuator/health "
sleep 10
for retry_count in {1..10}
do
	response=$(curl -s http://localhost:$IDLE_PORT/actuator/health)
	up_count=$(echo $response | grep 'UP' | wc -l)
	if [ $up_count -ge 1 ]
	then
		echo "> Health check 성공"
		break
	else
		echo "> Health check의 응답을 알 수 없거나 혹은 status가 UP이 아닙니다."
		echo "> Health check: ${response}"
	fi
	if [ $retry_count -eq 10 ]
	then
		echo "> Health check 실패. "
		echo "> Nginx에 연결하지 않고 배포를 종료합니다."
		exit 1
	fi
	echo "> Health check 연결 실패. 재시도..."
	sleep 10
done
echo "> 스위칭을 시도합니다..."
sleep 10
sh /home/ubuntu/deploy/scripts/switch.sh
