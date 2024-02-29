#!/bin/bash

sudo apt-get update -y

# Java 17 Installation
sudo wget -O - https://apt.corretto.aws/corretto.key | sudo gpg --dearmor -o /usr/share/keyrings/corretto-keyring.gpg && \
	echo "deb [signed-by=/usr/share/keyrings/corretto-keyring.gpg] https://apt.corretto.aws stable main" | sudo tee /etc/apt/sources.list.d/corretto.list
sudo apt-get update -y ; sudo apt-get install -y java-17-amazon-corretto-jdk

# git Installation
sudo apt-get update -y
sudo apt-get install -y git

# JMeter Installation
cd /home/ubuntu
wget https://dlcdn.apache.org//jmeter/binaries/apache-jmeter-5.6.3.tgz
tar xfz apache-jmeter-5.6.3.tgz 
ln -s apache-jmeter-5.6.3 jmeter 