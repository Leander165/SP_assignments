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

        stage('Run UI Tests') {
            steps {
                script {
                    try {
                        sh 'robot -d results Assignments_Leander_van_Vliet/UITests/'
                    } catch (Exception e) {
                        echo "UI Tests failed, check results!"
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }

        stage('Check UI Test Results') {
            steps {
                script {
                    def reportExists = fileExists 'results/report.html'
                    if (!reportExists) {
                        error("UI Test report not found!")
                    } else {
                        echo "UI Test report found."
                    }
                }
            }
        }

        stage('Run API Tests') {
            steps {
                script {
                    try {
                        sh 'python3 -m unittest discover -s APITests'
                    } catch (Exception e) {
                        echo "⚠️ API Tests failed, check logs!"
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }

        stage('Check API Test Results') {
            steps {
                script {
                    def outputExists = fileExists 'APITests/output.xml'
                    if (!outputExists) {
                        error("API Test output not found!")
                    } else {
                        echo "API Test output found."
                    }
                }
            }
        }

        stage('Publish Test Results') {
            steps {
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'results',
                    reportFiles: 'report.html',
                    reportName: 'UI Test Report'
                ])
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'results/*.html, APITests/output.xml', fingerprint: true
            echo "Pipeline finished!"
        }
        failure {
            echo "Build failed. Check logs."
        }
    }
}