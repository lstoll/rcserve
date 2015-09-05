FROM ruby:2.2

RUN gem install geminabox puma

ADD config.ru /root/config.ru

WORKDIR /root

EXPOSE 8989
VOLUME /data/rubygems
CMD ["puma", "--port", "8989", "config.ru"]
