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
                println getE2EDir(params.PROJECT_NAME)
                sh "echo '${getE2EDir(params.PROJECT_NAME)}'"
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

    Map<String, String> testByProjects = [
            "eden-dispatcher"        : "tech.viacom.events.e2e.*",
            "eden-collector"         : "tech.viacom.events.e2e.*",
            "eden-abe"               : "tech.viacom.events.e2e.*",
            "eden-my5-adobe"         : "tech.viacom.events.e2e.adobe.my5.*",
            "eden-adobe-playplexplus": "tech.viacom.events.e2e.adobe.playplex.*",
            "eden-braze"             : "tech.viacom.events.e2e.braze.*",
            "eden-adjust"            : "tech.viacom.events.e2e.adjust.*",
            "eden-pluto"             : "tech.viacom.events.e2e.pluto.*",
            "eden-google"            : "tech.viacom.events.e2e.google.*",
            "eden-facebook"          : "tech.viacom.events.e2e.facebook.*",
            "eden-snapchat"          : "tech.viacom.events.e2e.snapchat.*",
            "eden-tiktok"            : "tech.viacom.events.e2e.tiktok.*"
    ]

    return testByProjects[projectName] ?: "tech.viacom.events.e2e.noop.*"
}
