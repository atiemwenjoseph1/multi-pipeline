pipeline {
    agent any

    stages {
        stage('Clone Terraform Repo') {
            steps {
                echo 'Hello World'
                git branch: 'main', url: 'https://github.com/atiemwenjoseph1/multi-pipeline.git'
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
