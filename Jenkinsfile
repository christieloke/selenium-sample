pipeline {
    agent any
	tools {
        maven 'maven 3.6.3'
    }
    stages {
        stage('Build') {
            steps {
				sh 'mvn --version'
                sh 'mvn -Dmaven.test.failure.ignore=true clean verify'
            }
        }
         stage('Report') {
             steps {
             script {
                     allure([
                             includeProperties: false,
                             jdk: '',
                             properties: [],
                             reportBuildPolicy: 'ALWAYS',
                             results: [[path: 'com.arvato.webedi.tests.bdd/target/allure-results']]
                     ])
             }
             }
         }
    }
    post {
        always {
            deleteDir()
        }
    }
} 	