#!/bin/bash
# Aktualisiere Text2Me
git pull
# Generiere JAR File
ant -q -f ChatServer/build.xml -Dnb.internal.action.name=build jar
# Starte JAR File
java -jar ChatServer/dist/ChatServer.jar
