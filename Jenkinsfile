pipeline {
2
    agent any
3
    stages {
4
        stage('Clone Repository') {
5
            steps {
6
                git url: 'https://github.com/vaibhavdhonde/interviewBackend'
7
            }
8
        }
9
        stage('Build Backend') {
10
            steps {
11
                sh '''
12
                # Install dependencies
13
                mvn clean install
14
                '''
15
            }
16
        }
17
        stage('Run Backend') {
18
            steps {
19
                sh '''
20
                # Start the server
21
                java -jar target/*.jar &
22
                '''
23
            }
24
        }
25
    }
26
}





px
