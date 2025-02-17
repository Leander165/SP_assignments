pipeline {
    agent any

    environment {
        ROBOT_OUTPUT_DIR = 'results'
        VENV_DIR = 'venv'
    }

    stages {
        stage('Checkout Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/Leander165/SP_assignments.git'
            }
        }
    }
}