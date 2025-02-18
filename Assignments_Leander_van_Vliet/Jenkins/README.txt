All of the tests in the APITests and UITests are configured in a Jenkins pipeline. 

At the beginning of the pipeline script we see:

Pipeline { agent any }, this will make sure that any available agent (or virtual node that is available) will pick the job to run it. 

triggers cron(07 * * * ), this will make sure that the pipeline will run every morning on 07:00 am. Just before the workday starts, so we can check if everything works.

This pipeline contains a few stages:

Checkout repository
    This is the first stage. It will make sure that the repository is cloned to the Jenkins workspace.
    This is needed to make sure that Jenkins can access to files in the repo and exectute the tests!


Install Dependencies

    This stage will set up an virtual environment with the needed dependencies installed. 
    For the tests we need python and robotframework. Also for the API tests, we need a installable library called requests

Run UI Tests
    In this stage we are setting up the virtual environment with all the dependencies and run the robot files to do the UI testing. 
    In Jenkins you can see if the tests are executed successfully. If any test will fail, the pipeline will fail!

Run API Tests
    In this stage we are setting up the virtual environment again with all the dependencies and run the python unit test files to do the API testing. 
    In Jenkins you can see if the tests are executed successfully. If any test will fail, the pipeline will fail again!

Post section
    In this post section, an email will be send to the OPS user if the pipeline result is FAILURE. 
    If the pipeline is SUCCESS only the build result will be printed.
    We do this to make sure that a failed test will be noticed by the OPS person.

