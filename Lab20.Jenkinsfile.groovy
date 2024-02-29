pipeline {
    agent any
    stages {
        stage("Email Notification Test") {
            steps {
                echo "email test"
            }
            post {
                always {
                    emailext (attachLog: true, body: '성공적으로 Build 완료했습니다.', compressLog: true
                    , recipientProviders: [buildUser()], subject: 'This is a Test mail.', to: 'devtrainer.naver.com@gmail.com')
                }
            }
        }
    }
}
