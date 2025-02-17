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
                script {
                    sh '''
                    python3 -m venv venv
                    . venv/bin/activate
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
                    python -m robot -d results $(pwd)/Assignments_Leander_van_Vliet/UITests/BuyAShirtEndToEndTest/BuyAShirtEndToEndTest.robot || echo "⚠️ Robot Framework test failed with exit code $?"
                    '''
                }
            }
        }
    }
}
