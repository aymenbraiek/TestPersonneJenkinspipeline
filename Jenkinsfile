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
                //notification succes
                }
            }
            //post failure notification ki
        }
        
        stage('SonarQube Analysis') {
         steps{
       
       withSonarQubeEnv('sonar-6') {
         // bat "mvn sonar:sonar"
        bat "mvn sonar:sonar \
           -Dsonar.projectKey=jenkins-pipeline \
           -Dsonar.host.url=http://localhost:9000 \
           -Dsonar.login=e946cc99e7b940bb75c5ecbca4c1c9b43771a04b"
       }
    }
       post{
                    success{
                        echo 'quality code verified'

                      archiveArtifacts artifacts :'target/*.war'
                    //notification succes
                    }
                }
     }
	

    stage ('Deploy Build in Staging Area'){
            steps{

              //  build(job : 'DeployTestPersonne' ,propagate:  false)
              echo 'you can deploy your artifact'

            }
        }


    }
    }
