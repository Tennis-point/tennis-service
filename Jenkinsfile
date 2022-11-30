pipeline {

    tools {
        dockerTool "docke-jenkins"
    }

    parameters {
            string(name: 'PROJECT_NAME', defaultValue: 'collector', description: 'Name of the project to run e2e for, by default eden-collector is selected for which all the e2e tests will be executed.')
        }
    agent any

    stages {

        stage('Docker node test') {
              steps {
                // Steps run in node:7-alpine docker container on docker agent
                sh 'echo "here "'
                println "params=" + params.PROJECT_NAME
              }
            }

        stage('build') {
            steps {
                sh 'echo "Hello World"'
                if (params.PROJECT_NAME == 'eden-collector') {
                    println 'inside the if !'
                }
                println getE2EDir(params.PROJECT_NAME)
            }
        }
        stage('test') {
            steps {
                sh 'echo "Fail!"'
            }
        }
    }
}

private String getE2EDir(String projectName) {
    String folderName = ""
    if (projectName.contains('adobe-my5')) {
        folderName = projectName.tokenize("-")[1] + "." + projectName.tokenize("-")[2];
    } else if (projectName.contains('adobe-playplexplus')) {
        folderName = projectName.tokenize("-")[1] + ".playplex";
    } else {
        folderName = projectName.tokenize("-")[1];
    }
    e2eDir = "tech.viacom.events.e2e.${folderName}.*";
    return e2eDir
}