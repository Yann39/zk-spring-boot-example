#!/bin/sh

# Wait until MariaDB is ready
while ! mysqladmin ping -h"zk-spring-boot-example-mariadb" -P"3306" --silent; do
    echo "Waiting for MariaDB to be up..."
    sleep 2
done