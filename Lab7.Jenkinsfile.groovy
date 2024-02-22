pipeline {
    agent any
        stages {
            
            stage('Checkout') {
                steps {
                    git branch: 'main', url:'https://github.com/expertman/guestbook.git'
                }
            }
            
            stage('Build') {
                steps {
                    sh "./mvnw -Dmaven.test.failure.ignore=true clean package"
                }
            
            post {
                success {
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
