#!/usr/bin/env ruby

require 'aws-sdk'
require 'pry'
require 'awesome_print'
require 'domainatrix'

# ------------------------------------------------------------------------------
#   Credentials
# ------------------------------------------------------------------------------

# pick up AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY by default from environment

Aws.config.update({
  region: 'us-east-1',
})

# ------------------------------------------------------------------------------

def setup_dns(domain, fqdn, txt_challenge)
  route53 = Aws::Route53::Client.new()
  # ap route53.list_hosted_zones_by_name({dns_name: "#{fqdn}."}).hosted_zones[0]
  hosted_zone = route53.list_hosted_zones_by_name({dns_name: "#{fqdn}."}).hosted_zones[0]

  changes = []

  changes << {
    action: "UPSERT",
    resource_record_set: {
      name: "_acme-challenge.#{domain}.",
      type: "TXT",
      ttl: 60,
      resource_records: [
        value: "\"#{txt_challenge}\"",
      ],
    },
  }

  resp = route53.change_resource_record_sets({
    hosted_zone_id: hosted_zone.id,
    change_batch: {
      changes: changes,
    },
  })

  ap resp

  sleep 20
end

# ------------------------------------------------------------------------------

def delete_dns(domain, fqdn, txt_challenge)
  route53 = Aws::Route53::Client.new()
  hosted_zone = route53.list_hosted_zones_by_name({dns_name: "#{fqdn}."}).hosted_zones[0]

  changes = []

  changes << {
    action: "DELETE",
    resource_record_set: {
      name: "_acme-challenge.#{domain}.",
      type: "TXT",
      ttl: 60,
      resource_records: [
        value: "\"#{txt_challenge}\"",
      ],
    },
  }

  resp = route53.change_resource_record_sets({
    hosted_zone_id: hosted_zone.id,
    change_batch: {
      changes: changes,
    },
  })

  ap resp

  sleep 5
end

# ------------------------------------------------------------------------------

if __FILE__ == $0
  puts "-------------------->"
  hook_stage = ARGV[0]
  domain = ARGV[1]
  txt_challenge = ARGV[3]

  url = Domainatrix.parse("#{domain}")
  fqdn = "#{url.domain}.#{url.public_suffix}"

  puts "   Domain: #{domain}"
  puts "     Root: #{fqdn}"
  puts "    Stage: #{hook_stage}"

  if hook_stage == "deploy_challenge"
    puts "Challenge: #{txt_challenge}" unless hook_stage == "deploy_cert"
    setup_dns(domain, fqdn, txt_challenge)
  elsif hook_stage == "clean_challenge"
    delete_dns(domain, fqdn, txt_challenge)
  elsif hook_stage == "deploy_cert"
    puts "    Certs: #{txt_challenge}" if hook_stage == "deploy_cert"
  end

  puts "--------------------<"

end