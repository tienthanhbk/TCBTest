#!/usr/bin/env bash
BASE_DIR=$(dirname $(readlink -e $0))/..

LOG4J_CONFIG=$BASE_DIR/config/log4j.xml
LOG_PATH=$BASE_DIR/logs
LOG_LEVEL=info
JAVA_HEAP_MAX=-Xmx1024m

MAIN_JAR=target/pool-project-1.0.jar

echo $BASE_DIR
echo $LOG4J_CONFIG
echo $SERVER_CONFIG

while (true)
do
java -Dlog4j.configuration=$LOG4J_CONFIG \
     -Dlogging.path=$LOG_PATH \
     -Dlogging.level.root=$LOG_LEVEL \
     $JAVA_HEAP_MAX \
     -jar $MAIN_JAR
echo "sleeping 10 seconds before continuous...."
sleep 10s
done