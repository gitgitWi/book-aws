#! /bin/bash

REPOSITORY=/home/ec2-user/app/deploy
PROJECT_NAME=book-aws

echo "> Copy Build Files"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> Check Current Application PIDs"

CURRENT_PID=$(pgrep -fl book-aws | grep jar | awk '{print $1}')

echo "> Current Application PID : $CURRENT_PID"

if [ -z "$CURRENT_PID" ] ; then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."

else
    echo "> kill -15 $CURRENT_PID"
    kill -15 $CURRENT_PID
    sleep 5
fi

echo "> Deploy New Application"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR NAME : JAR_NAME"

echo "> Add Execution Authority to $JAR_NAME"

chmod +x $JAR_NAME

echo "> START $JAR_NAME"

nohup java -jar \
        -Dspring.config.location=classpath:/application.properties,classpath:application-real.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
        -Dspring.profiles.active=real \
        $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
