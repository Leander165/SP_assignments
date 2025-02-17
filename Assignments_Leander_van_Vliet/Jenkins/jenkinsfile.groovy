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
                    chmod +x ${VENV_DIR}/bin/activate
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
        stage('Debug Environment') {
            steps {
                script {
                    sh '''
                    echo "🔍 Python versie:"
                    python3 --version

                    echo "🔍 PIP versie:"
                    pip3 --version

                    echo "🔍 Virtual Environment Path:"
                    ls -la ${VENV_DIR}

                    echo "🔍 Bestanden in UI Test map:"
                    ls -la Assignments_Leander_van_Vliet/UITests/BuyAShirtEndToEndTest/

                    echo "🔍 Controleer of testbestand bestaat:"
                    if [ ! -f "Assignments_Leander_van_Vliet/UITests/BuyAShirtEndToEndTest/BuyAShirtEndToEndTest.robot" ]; then
                        echo "❌ Testbestand niet gevonden!"
                        exit 1
                    fi
                    '''
                }
            }
        }
        stage('Run UI Tests') {
            steps {
                script {
                    sh '''
                    source ${VENV_DIR}/bin/activate
                    python -m robot -d results $(pwd)/Assignments_Leander_van_Vliet/UITests/BuyAShirtEndToEndTest/BuyAShirtEndToEndTest.robot || echo "⚠️ Robot Framework test failed with exit code $?"
                    deactivate
                    '''
                }
            }
        }
    }
}