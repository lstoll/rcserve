#!/bin/bash

docker run -d --name=site-nginx --restart=always -v $(pwd)/site/nginx.conf:/etc/nginx/nginx.conf:ro -v /data/site:/data/site -p 172.16.51.245:80:80 -p 172.16.51.245:443:443 nginx
