pipeline {
    environment {
        dockerImage = ''
    }
    agent any

    tools {
        nodejs "node"
        maven "mvn"
        jdk "openjdk-11"
        dockerTool "docker"
    }

    stages {
        stage('Cloning Git repository') {
            steps {
                  git branch: 'master', url: 'https://github.com/rooq37/EnrollmentSystem'
            }
        }

        stage('Build backend') {
            steps {
                sh '(cd backend;mvn clean install -DskipTests -U)'
            }
        }

        stage('Build frontend') {
            steps {
                sh '(cd frontend; npm install)'
            }
        }

        stage('Build backend docker image') {
            steps {
                script {
                    dockerImageBackend = docker.build('kwozniak218/psi_backend' + ":$BUILD_NUMBER", "-f ./deployment/backend/Dockerfile .")
                }
            }
        }

        stage('Build frontend docker image') {
            steps {
                script {
                    dockerImageFrontend = docker.build('kwozniak218/psi_frontend' + ":$BUILD_NUMBER", "-f ./deployment/frontend/Dockerfile .")
                }
            }
        }

        stage('Deploy backend image to dockerhub') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
                        dockerImageBackend.push("${env.BUILD_NUMBER}")
                        dockerImageBackend.push('latest')
                    }
                }
            }
        }

        stage('Deploy frontend image to dockerhub') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
                        dockerImageFrontend.push("${env.BUILD_NUMBER}")
                        dockerImageFrontend.push('latest')
                    }
                }
            }
        }
        
        stage('Deploy backend to kubernetes') {
            steps {
                script {
                    sh '(cd ./deployment/backend; kubectl apply -f backend.yml)'
                }
            }
        }

        stage('Deploy frontend to kubernetes') {
            steps {
                script {
                    sh '(cd ./deployment/frontend; kubectl apply -f frontend.yml)'
                }
            }
        }
    }
}
