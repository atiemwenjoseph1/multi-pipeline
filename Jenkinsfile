pipeline {
    agent any
    stages {
        stage('Cloning Repo') {
            steps {
                git 'https://github.com/atiemwenjoseph1/multi-pipeline.git'
            }
        }
        stage('Checking File') {
            steps {
                sh 'ls'
                sh 'cat Jenkinsfile'
                sh 'pwd'
            }
        }
        stage('Terraform Provision') {
            steps {
                sh """
                    terraform init
                    terraform apply --auto-approve
                """
            }
        }
        stage('Time the kill?') {
            steps {
                input message: 'Do you want to Destroy Terraform Resources?'
            }
        }
        stage('Destoyed and Checking statefile') {
            steps {
                sh 'terraform destroy --auto-approve'
                sh 'cat terraform.tfstate'
            }
        }
    }
}
