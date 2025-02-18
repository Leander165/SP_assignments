pipeline {
    agent any

environment {
}

triggers {
        cron('0 7 * * *')
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
                    pip3 install --upgrade pip
                    pip3 install --upgrade robotframework robotframework-seleniumlibrary
                    python --version
                    pip3 --version
                    pip3 install requests
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
                    python -m unittest Assignments_Leander_van_Vliet/APITests/CreateUser/CreateUser.py
                    python -m unittest Assignments_Leander_van_Vliet/APITests/DeleteUser/DeleteUser.py
                    python -m unittest Assignments_Leander_van_Vliet/APITests/GetDelayedUserList/GetDelayedUserlist.py
                    python -m unittest Assignments_Leander_van_Vliet/APITests/LoginUser/LoginUser.py
                    '''
                }
            }
        }
    }
    post {
        always {
            script {
                if (currentBuild.result == 'FAILURE') {
                        emailext (
                            to: 'Thisemailisfakebutineedtoputsomething@somethingofcourse.com',
                            subject: 'One of your jenkins tests failed',
                            body: """You need to check your pipeline results in Jenkins to see what went wrong. Kind regards, Jenkins robot""",
                            attachmentsPattern: 'Assignments_Leander_van_Vliet/log.html'
                        )
                    echo "Build result = ${currentBuild.result}"
                   
                }
                else {
                    currentBuild.result = 'SUCCESS'
                    echo "Build result = ${currentBuild.result}, no email will be send"
                   
                }
            }
        }   
    }
}
