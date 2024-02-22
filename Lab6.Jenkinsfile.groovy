pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                // Get some code from a GitHub repository
                git (branch: 'main'
                    , url:'https://github.com/expertman/guestbook.git')
            }
        }
    }
}
