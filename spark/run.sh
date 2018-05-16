#!/bin/sh

SPARK_CLASS=SPARK
SPARK_BUILD_DIR=spark
SPARK_JAR=spark_2.10-0.1.jar
SPARK_BASE=/user/admin/input1505

DEPLOY_MODE=cluster
MASTER=yarn

spark-submit --deploy-mode ${DEPLOY_MODE} --master ${MASTER} --executor-memory 128M --num-executors 2 --executor-cores 1 --class ${SPARK_CLASS} ${SPARK_BUILD_DIR}/${SP$



