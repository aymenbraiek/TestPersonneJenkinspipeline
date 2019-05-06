properties(
    [
        [$class: 'BuildDiscarderProperty', strategy:
          [$class: 'LogRotator', artifactDaysToKeepStr: '14', artifactNumToKeepStr: '5', daysToKeepStr: '30', numToKeepStr: '60']],
        pipelineTriggers(
          [
              pollSCM('H/15 * * * *'),
              cron('@daily'),
          ]
        )
    ]
)

pipeline {

  agent any
    stages {
        stage ('Build Servlet Project') {
            steps {
                /*For windows machine */
               bat  'mvn clean package'

                /*For Mac & Linux machine */
               // sh  'mvn clean package'
            }

            post{
                success{
                    echo 'Now Archiving ....'

                  archiveArtifacts artifacts :'target/*.war'
                }
            }
        }
        stage('SonarQube Analysis') {
       
        withSonarQubeEnv('sonar-6') { 
          bat "${mvnHome}/bin/mvn sonar:sonar"
        }
    }
     stage('Email Notification'){
      mail bcc: '', body: '''Hi Welcome to jenkins email alerts
      Thanks
      Hari''', cc: '', from: '', replyTo: '', subject: 'Jenkins Job', to: 'aymen.braiek@biat.com.tn'
   }

        stage ('Deploy Build in Staging Area'){
            steps{

                build(job : 'DeployTestPersonne' ,propagate:  false)

            }
        }


    }
    }