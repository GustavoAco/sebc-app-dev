#!/bin/sh

echo "Starting flume ..."
flume-ng agent -n a1 -f job.config

