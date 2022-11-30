pipeline {

    agent {
        docker {
          // Set both label and image
          label 'docker'
          image 'node:7-alpine'
          args '--name docker-node' // list any args
        }
      }

    stages {

        stage('Docker node test') {

              steps {
                // Steps run in node:7-alpine docker container on docker agent
                sh 'node --version'
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