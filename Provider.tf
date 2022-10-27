terraform {
  required_providers {
    aws = {
      source = "registry.terraform.io/hashicorp/aws"
      version = "4.36.1"
    }
  }
}

provider "aws" {
    region = "eu-west-2"

}
