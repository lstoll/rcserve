#\ -s puma
require "geminabox"

Geminabox.data = "/data/gems"
Geminabox.rubygems_proxy = true
Geminabox.allow_remote_failure = true
run Geminabox::Server
