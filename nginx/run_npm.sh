#!/bin/bash

docker run -d --name=npm-nginx --restart=always -v $(pwd)/npm/nginx.conf:/etc/nginx/nginx.conf:ro -v /data/npm:/data/npm -p 172.16.51.241:80:80 -p 172.16.51.241:443:443 nginx
