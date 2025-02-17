This test verifies that a user can successfully log in to SauceDemo using valid credentials. 

The test checks if the user is redirected to the inventory page. The test passes if the inventory page is visible.

To run this test, you need to make sure that:
-	Python is installed
-	Robot Framework & SeleniumLibrary are installed with pip:
        (pip install robotframework)
        (pip install robotframework-seleniumlibrary)
-   Chromedriver is installed (and updated if needed)

To execute the test you can run robot with : login_unit_test.robot

Comments:
No code arguments or comments are given in the robot files since this is messing up the execution of the code in jenkins. All of the comments are placed here. 
This code uses xpaths to determine what button to click or what element to wait on to appear. The xpaths are found with the dev tool in Chrome. 
This code opens an incognito version of chrome. The reason for this is a pop-up with information about "a not safe password that is used" was blocking the test on normal chrome. 
The code creates a path to the screenshot folder, and a screenshot is saved. This screenshot is saved with a timestamp as a name for testing documentation. 
The screenshot is taken at the end of the test.

The expected result is that the user lands on the inventory page, confirming a successful login. 
Any other result is an unexpected result.