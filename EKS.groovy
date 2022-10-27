pipeline {
    agent any

    stages {
        stage('Clone Terraform Repo') {
            steps {
                echo 'Hello World'
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/aws-ia/terraform-aws-eks-blueprints.git']]])
                        }}
        stage('Terraform provision') {
            steps {
                sh """
                    terraform init
                    export AWS_REGION=eu-west-2
                    terraform plan
                    terraform apply --auto-approve
                """
            }
        }
        stage('License to Kill?') {
            steps {
                input message: 'Destroy terraform resources?'
            }
        }
        stage('Destroying AWS Resources') {
            steps {
                sh 'terraform destroy --auto-approve'
                
            }
        }
    }
}