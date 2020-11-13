@echo off
call mvn clean package
call docker build -t org.example/courseassignment-multipleuser-formauth-ldapidentitystore .
call docker rm -f courseassignment-multipleuser-formauth-ldapidentitystore
call docker run -d -p 9080:9080 -p 9443:9443 --name courseassignment-multipleuser-formauth-ldapidentitystore org.example/courseassignment-multipleuser-formauth-ldapidentitystore