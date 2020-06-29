pipeline {
    agent any
    triggers {
        pollSCM '* * * * *'
    }
    stages {
        stage ('Pre-check') {
            steps {
                sh './mvnw -v'
            }
        }
        stage('Compile') {
            steps {
                sh './mvnw validate'
                sh './mvnw compile'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }
        stage('Build') {
            steps {
                sh './mvnw package'
            }
        }
    }
}