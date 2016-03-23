interfaces {
    ethernet eth0 {
        address dhcp
        description wan
        duplex auto
        speed auto
    }
    ethernet eth1 {
        address 172.16.71.1/24
        description server
        duplex auto
        speed auto
    }
    ethernet eth2 {
        address 172.16.72.1/24
        description internet
        duplex auto
        speed auto
    }
    ethernet eth3 {
        address 172.16.73.1/24
        description no-internet
        duplex auto
        speed auto
    }
    ethernet eth4 {
        duplex auto
        speed auto
    }
    loopback lo {
    }
    switch switch0 {
        mtu 1500
    }
}
service {
    gui {
        https-port 443
    }
    ssh {
        port 22
        protocol-version v2
    }
}
system {
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


/* Warning: Do not remove the following line. */
/* === vyatta-config-version: "config-management@1:conntrack@1:cron@1:dhcp-relay@1:dhcp-server@4:firewall@5:ipsec@4:nat@3:qos@1:quagga@2:system@4:ubnt-pptp@1:ubnt-util@1:vrrp@1:webgui@1:webproxy@1:zone-policy@1" === */
/* Release version: v1.6.6.4749363.150224.1217 */
