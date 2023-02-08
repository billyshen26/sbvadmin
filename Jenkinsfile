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
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package -P prod'
            }
        }
        stage('Restart') {
            steps {
                sh 'systemctl restart sbvadmin'
            }
        }
    }
}