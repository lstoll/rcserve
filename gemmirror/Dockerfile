FROM ruby:2.2

RUN gem install rubygems-mirror

ADD mirrorrc /root/.gem/.mirrorrc

CMD ["gem", "mirror"]
