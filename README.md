# My-Day

## Description
This application allows a user to log their day with a rating and a short message. The goal is to provide a way to reflect on the past and the present.

## Features
* User can log in using their Google Account
* User can log their day with an optional short message and rating.
* User can view past ratings

## Installation Instructions for Local Hosting
### Pre-Requisites
* Maven. Maven is a project managment tool. More info and download here: https://maven.apache.org/
* Java 1.8. Find most recent version here: https://www.oracle.com/technetwork/java/javaee/downloads/jdk8-downloads-2133151.html.
* MySQL Server. Download can be found  here: https://dev.mysql.com/downloads/mysql/.
* Google API credentials. This allows users to log in with their gmail. Additionally, we do not have to store passwords. Download here: https://developers.google.com/identity/protocols/OpenIDConnect.

### Recomendations
* MySQL Workbench. This tool will allow one to alter table later if necessary.
* Create app-env file to store MySQL and Google credentials. link for example: https://hackernoon.com/how-to-use-environment-variables-keep-your-secret-keys-safe-secure-8b1a7877d69c.

### How to run
* Clone the project
* Enter SQL and Google API credentials in `app/src/main/resources/appliction.yml`. This will allow the application to store ratings and allow users to long in.
* While in the `app` folder run the command `mvn clean install` followed by `mvn spring-boot:run`. This will run the application.
* To access the website use `localhost:8080` (sometimes the port may be different).
