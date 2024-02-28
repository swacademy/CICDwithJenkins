import java.text.SimpleDateFormat

def TODAY = (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date())

pipeline {
    agent any
    environment {
        strDockerTag = "${TODAY}_${BUILD_ID}"
        strDockerImage ="devtrainer/cicd_guestbook:${strDockerTag}"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url:'https://github.com/expertman/guestbook.git'
            }
        }
        stage('Build') {
            steps {
                sh './mvnw clean package'
            }
        }
        stage('Unit Test') {
            steps {
                sh './mvnw test'
            }
            
            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps{
                echo 'SonarQube Analysis'
                /*
                withSonarQubeEnv('SonarQube-Server'){
                    sh '''
                        ./mvnw sonar:sonar \
                        -Dsonar.projectKey=guestbook \
                        -Dsonar.host.url=http://43.201.49.180:9000 \
                        -Dsonar.login=d7c0156ac596ee750c5583b68e6b051e87d61eb1
                    '''
                }
                */
            }
        }
        stage('SonarQube Quality Gate'){
            steps{
                echo 'SonarQube Quality Gate'
                /*
                timeout(time: 1, unit: 'MINUTES') {
                    script{
                        def qg = waitForQualityGate()
                        if(qg.status != 'OK') {
                            echo "NOT OK Status: ${qg.status}"
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        } else{
                            echo "OK Status: ${qg.status}"
                        }
                    }
                }
                */
            }
        }
        stage('Docker Image Build') {
            steps {
                script {
                    //oDockImage = docker.build(strDockerImage)
                    oDockImage = docker.build(strDockerImage, "--build-arg VERSION=${strDockerTag} -f Dockerfile .")
                }
            }
        }
        stage('Docker Image Push') {
            steps {
                script {
                    docker.withRegistry('', 'DockerHub_Credential') {
                        oDockImage.push()
                    }
                }
            }
        }
    }
}