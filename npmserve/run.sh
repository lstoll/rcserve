#!/bin/bash

docker run -d -p 5984:5984 -v /data/couchdb/log:/usr/local/var/log/couchdb -v /data/couchdb/data:/usr/local/var/lib/couchdb --name npmserve klaemo/couchdb:latest
