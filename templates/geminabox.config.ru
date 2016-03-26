#\ -s puma
require "geminabox"

Geminabox.data = "/srv/geminabox/data"
Geminabox.rubygems_proxy = true
Geminabox.allow_remote_failure = true
run Geminabox::Server
