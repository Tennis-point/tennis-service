pipeline {

    tools {
        dockerTool "docke-jenkins"
    }

    agent any

    stages {

        stage('Docker node test') {
              steps {
                // Steps run in node:7-alpine docker container on docker agent
                sh 'echo "here"'
              }
            }

        stage('build') {
            steps {
                sh 'echo "Hello World"'
            }
        }
        stage('test') {
            steps {
                sh 'echo "Fail!"; exit 1'
            }
        }
    }
}