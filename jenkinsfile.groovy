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

        stage('Install Dependencies') {
            steps {
                script {
                    sh '''
                    python3 -m venv ${VENV_DIR}
                    . ${VENV_DIR}/bin/activate
                    python --version
                    pip --version
                    pip install --upgrade pip
                    pip install --upgrade robotframework robotframework-seleniumlibrary
                    robot --version
                    '''
                }
            }
        }

        stage('Run UI Tests') {
            steps {
                script {
                    sh '''
                    python -m robot -d Assignments_Leander_van_Vliet/UITests/BuyAShirtEndToEndTest/BuyAShirtEndToEndTest.robot
                    deactivate                   
                     '''
                }
            }
        }
    }
}