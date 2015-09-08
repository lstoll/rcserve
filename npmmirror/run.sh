#!/bin/bash

docker run --rm -i -v /data/npm:/data/npm -v /data/npm_tmp:/data/npm_tmp npmmirror registry-static -d npm.camp -o /data/npm -t /data/npm_tmp %@
