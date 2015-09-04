#!/bin/bash

set -e

apt-get update
apt-get upgrade
apt-get install -y dnsmasq

if [ ! -f /usr/bin/docker ]; then
    curl -sSL https://get.docker.com/ | sh
    sudo usermod -aG docker lstoll
fi
