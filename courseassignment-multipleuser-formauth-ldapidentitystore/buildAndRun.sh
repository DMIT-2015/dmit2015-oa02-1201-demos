#!/bin/sh
if [ $(docker ps -a -f name=courseassignment-multipleuser-formauth-ldapidentitystore | grep -w courseassignment-multipleuser-formauth-ldapidentitystore | wc -l) -eq 1 ]; then
  docker rm -f courseassignment-multipleuser-formauth-ldapidentitystore
fi
mvn clean package && docker build -t org.example/courseassignment-multipleuser-formauth-ldapidentitystore .
docker run -d -p 9080:9080 -p 9443:9443 --name courseassignment-multipleuser-formauth-ldapidentitystore org.example/courseassignment-multipleuser-formauth-ldapidentitystore
