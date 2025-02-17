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
                    source ${VENV_DIR}/bin/activate
                    python --version
                    pip --version
                    pip install --upgrade pip
                    pip install --upgrade robotframework robotframework-seleniumlibrary
                    robot --version
                    deactivate
                    '''
                }
            }
        }

        stage('Run UI Tests') {
            steps {
                script {
                    sh '''
                    source ${VENV_DIR}/bin/activate
                    python -m robot -d Assignments_Leander_van_Vliet/UITests/BuyAShirtEndToEndTest/BuyAShirtEndToEndTest.robot
                    deactivate                   
                     '''
                }
            }
        }
    }
}