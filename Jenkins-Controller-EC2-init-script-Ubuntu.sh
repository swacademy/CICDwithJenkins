#!/bin/bash

sudo apt-get update -y

# Java 17 Installation
sudo wget -O - https://apt.corretto.aws/corretto.key | sudo gpg --dearmor -o /usr/share/keyrings/corretto-keyring.gpg && \
	echo "deb [signed-by=/usr/share/keyrings/corretto-keyring.gpg] https://apt.corretto.aws stable main" | sudo tee /etc/apt/sources.list.d/corretto.list
sudo apt-get update -y ; sudo apt-get install -y java-17-amazon-corretto-jdk

# Docker Installation
sudo apt-get install ca-certificates curl gnupg lsb-release apt-transport-https -y
sudo mkdir -p /etc/apt/keyrings
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

sudo apt-get update -y
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-compose-plugin -y

sudo systemctl start docker
sudo usermod -aG docker ubuntu
sudo docker version

sudo docker container run -d \
    --name=mysqldb \
    --restart=always \
    -e MYSQL_ROOT_PASSWORD=education \
    -e MYSQL_DATABASE=guestbook \
    -p 3306:3306 \
    yu3papa/mysql_hangul:2.0

# Jupyter Installation 
sudo apt-get -y install python3-pip
sudo pip3 install --upgrade pip
sudo pip3 install jupyterlab
mkdir /home/ubuntu/notebook
cd /
 
sudo nohup jupyter lab --allow-root --ip=0.0.0.0 --ServerApp.password='argon2:$argon2id$v=19$m=10240,t=10,p=8$xkydTO+sORmMAM3II4/tIw$LGAynvTWYUjD8UxaPgBPbxU6TTXczUMnx1fdcnAGW5I' --ServerApp.token='' &