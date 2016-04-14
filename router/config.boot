interfaces {
    ethernet eth0 {
        duplex auto
        speed auto
    }
    ethernet eth1 {
        duplex auto
        speed auto
    }
    ethernet eth2 {
        address 10.29.64.2/20
        description upstream
        duplex auto
        speed auto
        traffic-policy {
            out UPLOAD-POLICY
        }
    }
    ethernet eth3 {
        duplex auto
        speed auto
    }
    ethernet eth4 {
        address 192.168.0.1/24
        duplex auto
        speed auto
    }
    loopback lo {
    }
    switch switch0 {
        address 172.16.51.254/24
        mtu 1500
        switch-port {
            interface eth0
            interface eth1
            interface eth3
        }
        traffic-policy {
            out DOWNLOAD-POLICY
        }
    }
}
service {
    dhcp-server {
        disabled false
        hostfile-update disable
        shared-network-name railscamp {
            authoritative enable
            description Railscamp
            subnet 172.16.51.0/24 {
                default-router 172.16.51.254
                dns-server 172.16.51.254
                domain-name railscamp.net
                lease 86400
                start 172.16.51.20 {
                    stop 172.16.51.220
                }
                static-mapping chex-laptop {
                    ip-address 172.16.51.245
                    mac-address 60:f8:1d:b9:17:dc
                }
                static-mapping cole-phone {
                    ip-address 172.16.51.243
                    mac-address 00:ee:bd:b4:65:15
                }
                static-mapping lhardy-laptop {
                    ip-address 172.16.51.242
                    mac-address ac:bc:32:c7:f7:5d
                }
                static-mapping lstoll-laptop {
                    ip-address 172.16.51.240
                    mac-address a4:5e:60:f0:29:37
                }
                static-mapping lstoll-phone {
                    ip-address 172.16.51.244
                    mac-address 90:fd:61:df:49:13
                }
                static-mapping sonos {
                    ip-address 172.16.51.241
                    mac-address 5c:aa:fd:0b:72:a4
                }
            }
        }
    }
    dns {
        forwarding {
            cache-size 2000
            listen-on switch0
            name-server 8.8.8.8
            name-server 8.8.4.4
        }
    }
    gui {
        https-port 443
    }
    nat {
        rule 5010 {
            outbound-interface eth2
            type masquerade
        }
    }
    ssh {
        port 22
        protocol-version v2
    }
}
system {
    config-management {
        commit-revisions 50
    }
    gateway-address 10.29.64.1
    host-name ubnt
    login {
        user lstoll {
            authentication {
                encrypted-password $6$g8V3fcC6Qu/FXGL$Iuh0hbpD382lTGl5/UGIPT2C8OBvuGAAihzNx5wGNFxlAIGZ2S2qHQb58rb30awrysbSEhjmOYeo/LeQdMXGl0
            }
            level admin
        }
        user ubnt {
            authentication {
                encrypted-password $1$zKNoUbAo$gomzUbYvgyUMcD436Wo66.
            }
            level admin
        }
    }
    ntp {
        server 0.ubnt.pool.ntp.org {
        }
        server 1.ubnt.pool.ntp.org {
        }
        server 2.ubnt.pool.ntp.org {
        }
        server 3.ubnt.pool.ntp.org {
        }
    }
    static-host-mapping {
        host-name apt.railscamp.net {
            inet 172.16.51.250
        }
        host-name brewbottles.railscamp.net {
            inet 172.16.51.250
        }
        host-name chat.railscamp.net {
            inet 172.16.51.250
        }
        host-name gems.railscamp.net {
            inet 172.16.51.250
        }
        host-name git.railscamp.net {
            inet 172.16.51.250
        }
        host-name npm.railscamp.net {
            inet 172.16.51.250
        }
        host-name photos.railscamp.net {
            inet 172.16.51.250
        }
        host-name railscamp.net {
            inet 172.16.51.250
        }
        host-name rcserve.railscamp.net {
            inet 172.16.51.250
        }
        host-name www.railscamp.net {
            inet 172.16.51.250
        }
    }
    syslog {
        global {
            facility all {
                level notice
            }
            facility protocols {
                level debug
            }
        }
    }
    time-zone UTC
}
traffic-policy {
    shaper DOWNLOAD-POLICY {
        bandwidth 40Mbit
        class 5 {
            bandwidth 50%
            burst 15k
            ceiling 75%
            match server {
                ip {
                    destination {
                        address 172.16.51.250/32
                    }
                }
            }
            priority 2
            queue-type fair-queue
        }
        class 10 {
            bandwidth 25%
            burst 15k
            ceiling 75%
            match cole-phone {
                ip {
                    destination {
                        address 172.16.51.243/32
                    }
                }
            }
            match lhardy-laptop {
                ip {
                    destination {
                        address 172.16.51.242/32
                    }
                }
            }
            match lstoll-laptop {
                ip {
                    destination {
                        address 172.16.51.240/32
                    }
                }
            }
            match sonos {
                ip {
                    destination {
                        address 172.16.51.241/32
                    }
                }
            }
            priority 4
            queue-type fair-queue
        }
        class 50 {
            bandwidth 5%
            burst 2.5k
            match ICMP {
                ip {
                    protocol icmp
                }
            }
            priority 3
            queue-type fair-queue
        }
        class 60 {
            bandwidth 5%
            burst 2.5K
            match ssh {
                ip {
                    destination {
                        port 22
                    }
                    dscp lowdelay
                    protocol tcp
                }
            }
            queue-type fair-queue
        }
        default {
            bandwidth 5%
            burst 15k
            ceiling 75%
            queue-type fair-queue
        }
    }
    shaper UPLOAD-POLICY {
        bandwidth 4Mbit
        class 5 {
            bandwidth 50%
            burst 15k
            ceiling 75%
            match server {
                ip {
                    source {
                        address 172.16.51.250/32
                    }
                }
            }
            priority 2
            queue-type fair-queue
        }
        default {
            bandwidth 5%
            burst 15k
            ceiling 75%
            queue-type fair-queue
        }
    }
}


/* Warning: Do not remove the following line. */
/* === vyatta-config-version: "config-management@1:conntrack@1:cron@1:dhcp-relay@1:dhcp-server@4:firewall@5:ipsec@5:nat@3:qos@1:quagga@2:system@4:ubnt-pptp@1:ubnt-util@1:vrrp@1:webgui@1:webproxy@1:zone-policy@1" === */
/* Release version: v1.8.0.4853089.160219.1607 */
