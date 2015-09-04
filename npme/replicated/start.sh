#!/bin/bash
chown -R couchdb:couchdb /etc/npme/couchdb
chown -R npme:npme /etc/npme/data

ndm generate --uid=root --gid=root --platform=initd && service redis-server start | service nginx start | couchdb | npme restart

cron

/etc/npme/bin/install-couch-app.sh

tail -f ./logs/*
