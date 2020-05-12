#!/bin/bash
mvn clean package
cd target
cp Backend.war /home/konecta/Servidores/wildfly-9.0.0.Final/standalone/deployments/