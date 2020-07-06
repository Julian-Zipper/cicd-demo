pipeline {
    agent any
    triggers {
        pollSCM '* * * * *'
    }
    stages {
        stage ('Pre-check') {
            steps {
                sh './gradlew -v'
            }
        }
        stage('Build') {
            steps {
                sh './gradlew assemble'
            }
        }
        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }
    }
}