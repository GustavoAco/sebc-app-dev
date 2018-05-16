#!/bin/sh

export SPARK_DIST_CLASSPATH=$(hadoop classpath)

SPARK_CLASS=SPARK
SPARK_BUILD_DIR=sparkml
SPARK_JAR=spark_2.10-0.1.jar
SPARK_BASE=../admin/input
ITERS=100

DEPLOY_MODE=cluster
MASTER=yarn

spark2-submit --deploy-mode ${DEPLOY_MODE} --master ${MASTER} --executor-memory 1G --num-executors 2 --executor-cores 1 --class ${SPARK_CLASS} ${SPARK_BUILD_DIR}/${SPARK_JAR} ${SPARK_BASE} ${ITERS}
