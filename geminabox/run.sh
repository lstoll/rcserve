#!/bin/bash

docker run -d --name geminabox  --restart=always -v /data/rubygems:/data/rubygems -p 172.16.51.240:80:8989 geminabox
