FROM ruby:2.2

RUN gem install geminabox thin

ADD config.ru /root/config.ru

WORKDIR /root

EXPOSE 8989
VOLUME /data/rubygems
CMD ["thin", "start", "p", "8989"]
