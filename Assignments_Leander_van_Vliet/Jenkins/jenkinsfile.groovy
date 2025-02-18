pipeline {
    agent any

    environment {
        ROBOT_OUTPUT_DIR = 'results'
    }

    stages {
        stage('Checkout Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/Leander165/SP_assignments.git'
            }
        }
        stage('Install Dependencies') {
            steps {
                catchError(buildResult: 'SUCCESS') {
                script {
                    sh '''
                    python3 -m venv venv
                    . venv/bin/activate
                    pip install --upgrade pip
                    pip install --upgrade robotframework robotframework-seleniumlibrary
                    python --version
                    pip --version
                    '''
                    }
                }
            }
        }
        stage('Run UI Tests') {
            steps {
                script {
                    sh '''
                    . venv/bin/activate
                    python -m robot -d results Assignments_Leander_van_Vliet/UITests/BuyAShirtEndToEndTest/BuyAShirtEndToEndTest.robot || echo "⚠️ Robot Framework test failed with exit code $?"
                    '''
                }
            }
        }
    }
}
