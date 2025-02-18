pipeline {
    agent any

    triggers {
        cron('0 7 * * *') 
    }

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
                        pip install --upgrade robotframework robotframework-seleniumlibrary requests
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
                    robot -d results Assignments_Leander_van_Vliet/UITests/LoginUnitTest/LoginUnitTest.robot
                    robot -d results Assignments_Leander_van_Vliet/UITests/LogOutUnitTest/LogOutUnitTest.robot
                    robot -d results Assignments_Leander_van_Vliet/UITests/BuyAShirtEndToEndTest/BuyAShirtEndToEndTest.robot
                    '''
                }
            }
        }

        stage('Run API Tests') {
            steps {
                script {
                    sh '''
                    . venv/bin/activate
                    python -m unittest discover -s Assignments_Leander_van_Vliet/APITests -p "*.py"
                    '''
                }
            }
        }
    }

    post {
        failure {
            mail to: 'jouw-email@example.com',
                 subject: "Jenkins Build Failed",
                 body: "Build #${currentBuild.number} failed. Check logs at: ${env.BUILD_URL}"
        }
    }
}