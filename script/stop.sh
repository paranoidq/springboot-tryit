#!/usr/bin/env bash

PID=`ps -ef | grep xxx.jar | grep -v grep | awk '{print $2}' `
if [[ -z "$PID" ]]; then
    echo Application is alread stopped
else
    echo kill ${PID}
fi