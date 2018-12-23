# ZK Spring Boot Example Application

Sample application integrating ZK with Spring Boot.

## Technologies

- Docker (Compose 3.5)
- Java 11
- Spring Boot 2.1.0 (Undertow 2)
- Apache 2.4.37 with SSL and HTTP/2
- MariaDB 10.3
- JPA/Hibernate 5.3
- ZK 8.6
- Bootstrap 4.1.3

## Usage

1. Run `docker-compose up` from the root folder, you should end up with 3 running containers :
   - _zk-spring-boot-example_apache_ : Shibboleth-ready Apache
   - _zk-spring-boot-example_app_ : Application server (Undertow) with SSL (https), JPDA (debug) and JRebel (instant deploy) configured
   - _zk-spring-boot-example_mariadb_ : MariaDB database
2. Build the project to create the JAR file
2. Reach http://localhost/zk-spring-boot-example

## Licence

[General Public License (GPL) v3](https://www.gnu.org/licenses/gpl-3.0.en.html)

This program is free software: you can redistribute it and/or modify it under the terms of the GNU
General Public License as published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
General Public License for more details.
    
You should have received a copy of the GNU General Public License along with this program.  If not,
see <http://www.gnu.org/licenses/>.