# RailsCamp Server

2016 Edition!

First, start with an ubuntu 16.04 daily iso. Install that to a VM. make sure you
have a `/data/` disk. Make sure you can log in. Good start. Set passwordless sudo

	%admin ALL=(ALL) NOPASSWD: ALL
	%sudo ALL=(ALL) NOPASSWD: ALL

Get python on it

	sudo apt-get -y install python python-simplejson

Apps
* https://github.com/aspic/g-wiki
* https://github.com/gogits/gogs
* http://www.mattermost.org/


Things to make available
* https://github.com/mattermost/desktop


Run ansible

    ansible-playbook -i 172.16.51.250, site.yml

First time might need to

	ansible-playbook -i 172.16.51.250, site.yml --ask-become-pass

## Updating gems

    sudo su - geminabox
    cd mirrorer
    bundle exec rake mirror:update

## Mattermost setup

    sudo su - mattermost
    cd mattermost
    vim config/config.json
    <change "EnableTeamCreation" to true.>
    ./bin/platform -create_team -team_name="railscamp" -email="admin@railscamp.net"
    ./bin/platform -create_user -team_name="railscamp" -email="admin@railscamp.net" -password="mypassword"
    ./bin/platform -assign_role -team_name="railscamp" -email="admin@railscamp.net" -role="system_admin"
    vim config/config.json
    <change "EnableTeamCreation" to false.>
    exit
    sudo service mattermost restart

Go to https://chat.railscamp.net/

## SSL Certs
I bought railscamp.net again, and with the magic of letsencrypt we can have real SSL certs. yay!

make sure to `bundle` in the `sslcerts/` dir. Gen keys for r53, use IAM like

```
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "route53:ChangeResourceRecordSets"
            ],
            "Resource": "arn:aws:route53:::hostedzone/Z1R52O69UE27J7"
        },
        {
            "Effect": "Allow",
            "Action": [
                "route53:GetChange"
            ],
            "Resource": "arn:aws:route53:::change/*"
        },
        {
            "Effect": "Allow",
            "Action": [
                "route53:ListHostedZones",
                "route53:ListHostedZonesByName"
            ],
            "Resource": "*"
        }
    ]
}
```


The run this mad command

    AWS_ACCESS_KEY_ID=XXXX AWS_SECRET_ACCESS_KEY=XXXX bundle exec ./letsencrypt.sh -c -k ./route53-hook.rb -t dns-01

### Legacy:
A bunch of stuff janked together to get offline love at RC-us-west

* host - this sets up the host to run stuff below
* gemmirror - Runs [rubygems mirror](https://github.com/rubygems/rubygems-mirror)
* npmserve - CouchDB
* npmsetup - interacts with the couchdb to mirror
