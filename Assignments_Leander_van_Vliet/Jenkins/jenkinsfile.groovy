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
                    python -m robot -d results Assignments_Leander_van_Vliet/UITests/LoginUnitTest/LoginUnitTest.robot 
                    python -m robot -d results Assignments_Leander_van_Vliet/UITests/LogOutUnitTest/LogOutUnitTest.robot 
                    python -m robot -d results Assignments_Leander_van_Vliet/UITests/BuyAShirtEndToEndTest/BuyAShirtEndToEndTest.robot 
                    '''
                }
            }
        }
        stage('Run API Tests') {
            steps {
                script {
                    sh '''
                    . venv/bin/activate
                    python -m  Assignments_Leander_van_Vliet/APITests/CreateUser/CreateUser
                    python -m  Assignments_Leander_van_Vliet/APITests/DeleteUser/DeleteUser
                    python -m  Assignments_Leander_van_Vliet/APITests/GetDelayedUserList/GetDelayedUserList
                    python -m  Assignments_Leander_van_Vliet/APITests/LoginUser/LoginUser                 
                    '''
                }
            }
        }
    }
}
