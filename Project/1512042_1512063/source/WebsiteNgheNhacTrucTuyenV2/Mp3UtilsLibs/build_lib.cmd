#!/bin/sh

ENTRY_PATH=`readlink -f "$0"`
PROJECT_DIR=Mp3UtilsLibs

#-------------------------------------------------------------------------------
#Java build & deploy
PROJECT_NAME=$PROJECT_DIR
VERSION=1.0.0.0

#common variables

JAVA_LIB_DIR=mp3_common_libs
DEPLOY_DIR=../3rd/3rd-lib/$JAVA_LIB_DIR
DEPLOY_JAR=$PROJECT_NAME"-"$VERSION".jar"

#build
ant -f build.xml clean
ant -f build.xml -Djavac.debug=true -Djar.compress=true -Ddist.jar=dist/$DEPLOY_JAR jar

#deploy to zserver dir
mkdir -p $DEPLOY_DIR
cp -f dist/$DEPLOY_JAR $DEPLOY_DIR



#clean
ant -f build.xml clean

echo "\n"
echo "################### MSG INFO #################################"
echo "Lib $DEPLOY_JAR has just deployed at $DEPLOY_DIR"
echo "##############################################################"
