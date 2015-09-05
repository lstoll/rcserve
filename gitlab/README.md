gitlab is jank and always needs email confirmation.

attach, edit: /opt/gitlab/embedded/service/gitlab-rails/app/models/user.rb

add


    $before_save :skip_confirmation!, :on => :create
