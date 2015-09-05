#!/bin/bash

docker run --name mattermost --restart=always -d --publish 172.16.51.244:80:80 -v /data/mattermost:/var/lib/mysql mattermost/platform
