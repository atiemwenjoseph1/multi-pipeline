pipeline {
    agent any

    stages {
        stage('Clone Terraform Repo') {
            steps {
                echo 'Hello World'
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'github', url: 'https://github.com/saha-rajdeep/devopscourse.git']]])
        }}
        stage('Terraform Provision') {
            steps {
                sh """
                    terraform init
                    terraform plan
                    terraform apply --auto-approve
                """
                sh 'pwd'
            }
        }
        stage('Time to Kill?') {
            steps {
                input message: 'Destroy terraform resources?'
            }
        }
        stage('Destroying AWS Resources') {
            steps {
                sh 'cd terraform; terraform destroy --auto-approve'
            }
        }
    }
}