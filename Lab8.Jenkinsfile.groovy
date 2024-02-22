pipeline {
    agent none
    
    stages {
        stage('Checkout') {
            agent any
            steps {
                git branch: 'main', url:'https://github.com/expertman/guestbook.git'
            }
        }
        stage('Build') {
            agent {
                docker {
                    image 'maven:3.8.4-openjdk-11-slim'
                } 
            }
            steps {
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
    }
}
