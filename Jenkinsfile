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
                # Install dependencies
                mvn clean install
                '''
            }
        }
        stage('Run Backend') {
            steps {
                sh '''
                # Start the server (assuming the .jar file is named target/demo-0.0.1-SNAPSHOT.jar)
                nohup java -jar target/demo-0.0.1-SNAPSHOT.jar > backend.log 2>&1 &
                '''
            }
        }
    }
}
