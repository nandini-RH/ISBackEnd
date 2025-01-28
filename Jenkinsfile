pipeline {
    agent any
    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/nandini-RH/ISBackEnd.git'
            }
        }
        stage('Build Backend') {
            steps {
                sh '''
                # Build the project and create the WAR file
                mvn clean package
                '''
            }
        }
        stage('Deploy to Tomcat') {
            steps {
                sh '''
                # Define variables
                WAR_FILE=target/demo-0.0.1-SNAPSHOT.war
                TOMCAT_PATH=/opt/tomcat/webapps

                # Copy the WAR file to the Tomcat webapps directory
                cp $WAR_FILE $TOMCAT_PATH

                # Restart the Tomcat server
                systemctl restart tomcat
                '''
            }
        }
    }
    post {
        success {
            echo 'Deployment completed successfully!'
        }
        failure {
            echo 'Deployment failed. Check logs for details.'
        }
    }
}
