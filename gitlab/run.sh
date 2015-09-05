#!/bin/bash

sudo docker run --detach \
    --publish 172.16.51.242:443:443 --publish 172.16.51.242:80:80 --publish 172.16.51.242:22:22 \
    --name gitlab \
    --restart always \
    --volume /data/gitlab/config:/etc/gitlab \
    --volume /data/gitlab/logs:/var/log/gitlab \
    --volume /data/gitlab/data:/var/opt/gitlab \
    gitlab/gitlab-ce:latest
