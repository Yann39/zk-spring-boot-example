FROM centos:centos7

LABEL authors="Yann39 <rockyracer@mailfence.com>"

# get service name argument from compose file
ARG APP_SERVICE_NAME

# install Apache with all required tools to setup SSL and HTTP/2
RUN cd ~ && mkdir sources && cd sources \
    && yum -y update \
    && yum -y install wget \
    && yum -y install perl \
    && yum -y install zlib-devel \
    && yum -y install pcre-devel \
    && yum -y install gcc \
    && yum -y install gcc-c++ \
    && yum -y install libxml2-devel \
    && yum -y install openssl \
    && yum -y install openssl-devel \
    && yum -y install expat-devel \
    && yum -y install automake \
    && yum -y install make \
    && yum -y install cmake \
    && yum -y install autoconf \
    && yum -y install libtool \
    && wget https://www.apache.org/dist/httpd/httpd-2.4.37.tar.gz \
    && wget https://github.com/nghttp2/nghttp2/releases/download/v1.34.0/nghttp2-1.34.0.tar.gz \
    && wget https://www.openssl.org/source/openssl-1.1.1.tar.gz \
    && wget http://mirrors.whoishostingthis.com/apache/apr/apr-1.6.5.tar.gz \
    && wget http://mirrors.whoishostingthis.com/apache/apr/apr-util-1.6.1.tar.gz \
    && wget https://github.com/google/brotli/archive/master.tar.gz \
    && tar -zxvf apr-1.6.5.tar.gz \
    && cd apr-1.6.5 && ./configure && make && make install && cd .. \
    && tar -zxvf apr-util-1.6.1.tar.gz \
    && cd apr-util-1.6.1 && ./configure --with-apr=/usr/local/apr && make && make install && cd .. \
    && tar -zxvf openssl-1.1.1.tar.gz \
    && cd openssl-1.1.1 && ./config shared zlib-dynamic --prefix=/usr/local/ssl && make && make install && cd .. \
    && tar -zxvf nghttp2-1.34.0.tar.gz \
    && cd nghttp2-1.34.0 && ./configure && make && make install && cd .. \
    && tar -zxvf master.tar.gz \
    && cd brotli-master && mkdir out && cd out && ../configure-cmake && make && make test && make install && cd ../.. \
    && tar -zxvf httpd-2.4.37.tar.gz \
    && cd httpd-2.4.37 && cp -r ../apr-1.6.5 srclib/apr && cp -r ../apr-util-1.6.1 srclib/apr-util && ./configure --with-ssl=/usr/local/ssl --with-pcre=/usr/bin/pcre-config --enable-unique-id --enable-ssl --enable-so --with-included-apr --enable-http2 --enable-brotli --with-brotli=/usr/local/brotli && make && make install && cd .. \
    && yum -y clean all

# copy Apache files to container
COPY httpd-files/envvars /usr/local/apache2/bin
COPY httpd-files/httpd.conf /usr/local/apache2/conf

# copy start script to container
COPY start-apache.sh /usr/local/bin/

# add execute privileges on start script
RUN chmod +x /usr/local/bin/start-apache.sh

# replace the service name in the Apache configuration
RUN sed -i 's/localhost/'$APP_SERVICE_NAME'/g' /usr/local/apache2/conf/httpd.conf

# generate self-signed certificate
RUN openssl req -x509 -nodes -days 1 -newkey rsa:2048 -subj /CN=* -keyout /usr/local/apache2/conf/server.key -out /usr/local/apache2/conf/server.crt

# start Apache
CMD ["start-apache.sh"]
