#!/usr/bin/env bash
BASE_DIR=$(dirname $(readlink -e $0))/..

SERVER_CONFIG=$BASE_DIR/config/server.properties
LOG4J_CONFIG=$BASE_DIR/config/log4j.xml
LOG_PATH=$BASE_DIR/logs
LOG_LEVEL=info
JAVA_HEAP_MAX=-Xmx2024m

MAIN_JAR=target/empty-project-1.0.jar
LIB=lib
CLASS_PATH=$JAVA_HOME/lib/*:$LIB/*:$MAIN_JAR

echo $BASE_DIR
echo $LOG4J_CONFIG
echo $SERVER_CONFIG

while (true)
do
java -Dapp.conf=$SERVER_CONFIG \
     -Dlog4j.configuration=$LOG4J_CONFIG \
     -Dlogging.path=$LOG_PATH \
     -Dlogging.level.root=$LOG_LEVEL \
     $JAVA_HEAP_MAX \
     -jar $MAIN_JAR
echo "sleeping 10 seconds before continuous...."
sleep 10s
done