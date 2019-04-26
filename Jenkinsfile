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

        stage ('Deploy Build in Staging Area'){
            steps{

                build job : 'DeployTestPersonne'

            }
        }


    }
    }