pipeline {
    /*
     *agent {
     *   docker {
     *       image 'maven:3.8.1-adoptopenjdk-11'
     *       args '-v /root/.m2:/root/.m2'
     *  }
     *}
     */
    agent any

    stages {
        stage('UpdateFrontend') {
            steps {
                sh './jenkins/scripts/updateFrontend.sh'
            }
        }
        stage('Restart') {
            steps {
                sh 'systemctl restart sbvadmin'
            }
        }
    }
}