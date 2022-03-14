#!/bin/bash

JAVA_HOME=/usr/lib/jvm/jdk-17
WORKDIR=/usr/local/ocpp-server
JAVA_OPTIONS=" -Xms256m -Xmx512m -server "
APP_OPTIONS=" -c $WORKDIR/install/config/parameters.toml "

cd $WORKDIR
"${JAVA_HOME}/bin/java" $JAVA_OPTIONS -jar javaapp.jar $APP_OPTIONS