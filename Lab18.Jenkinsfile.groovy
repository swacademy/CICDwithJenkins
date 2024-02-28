pipeline {
    agent any
    stages {
        stage("Slack Notification") {
            steps {
                slackSend(tokenCredentialId: 'slack-token'
                        , channel: '#designteam'
                        , color: 'good'
                        , message: 'designteam 채널 메세지')

                slackSend(tokenCredentialId: 'slack-token'
                        , channel: '#랜덤'
                        , color: 'warning'
                        , message: '랜덤 채널 메세지')

                slackSend(tokenCredentialId: 'slack-token'
                        , channel: '#방명록-프로젝트'
                        , color: 'danger'
                        , message: '방명록-프로젝트 채널 메세지')
            }
        }
    }
}