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
               // sh  'mvn  -B -DskipTests clean package'
            }

            post{
                success{
                    echo 'Now Archiving ....'

                  archiveArtifacts artifacts :'target/*.war'
                }
            }
        }
        
        stage('SonarQube Analysis') {
          steps{
       
        withSonarQubeEnv('sonar-6') { 
          bat "mvn sonar:sonar"
        }
    }
     }
	 stage("Quality Gate"){
	 steps{
    timeout(time: 1, unit: 'HOURS') { // Just in case something goes wrong, pipeline will be killed after a timeout
    def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
    if (qg.status != 'OK') {
        error "Pipeline aborted due to quality gate failure: ${qg.status}"
    }
   }}}

    stage ('Deploy Build in Staging Area'){
            steps{

                build(job : 'DeployTestPersonne' ,propagate:  false)

            }
        }


    }
    }