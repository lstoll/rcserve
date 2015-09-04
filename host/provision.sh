#!/bin/bash

set -e

apt-get update
apt-get upgrade
apt-get install -y dnsmasq node nodejs npm nodejs-legacy

if [ ! -f /usr/bin/docker ]; then
    curl -sSL https://get.docker.com/ | sh
    sudo usermod -aG docker lstoll
fi
