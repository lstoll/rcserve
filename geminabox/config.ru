require "rubygems"
require "geminabox"

Geminabox.data = "/data/rubygems"
Geminabox.build_legacy = false
Geminabox.rubygems_proxy = false
Geminabox.allow_remote_failure = true

run Geminabox::Server
