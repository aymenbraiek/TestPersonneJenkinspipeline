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
                def pom = readMavenPom file: 'pom.xml'
                    print pom.version
                    archiveArtifacts artifacts :'target*//*.jar'
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