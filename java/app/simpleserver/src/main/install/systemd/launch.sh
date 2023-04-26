#!/bin/bash

JAVA_HOME=/usr/lib/jvm/java-17
WORKDIR=/usr/local/ocpp-engine
JAVA_OPTIONS=" -Xms256m -Xmx512m -server "
APP_OPTIONS=" ${WORKDIR}/config/log.properties ${WORKDIR}/config/parameters.toml ${WORKDIR}/config/connector.toml"

cd $WORKDIR
"${JAVA_HOME}/bin/java" $JAVA_OPTIONS -jar $WORKDIR/simpleserver_deploy.jar $APP_OPTIONS
