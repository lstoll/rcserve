#!/bin/bash

docker run --rm -i -v /data/npm:/data/npm -v /data/npm_tmp:/data/npm_tmp npmmirror
