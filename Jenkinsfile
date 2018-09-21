pipeline {
       agent any


       stages {
          stage('Build') {
             steps {
                sh 'gradle clean compileJava'
                sh './gradlew clean assemble'
             }
          }
          stage('Deploy'){
                     steps{
                         sh 'cf push account -p ./build/libs/account-0.0.1-SNAPSHOT.jar'
                     }
          }
       }
   }