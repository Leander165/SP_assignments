This test verifies that a user can successfully log out after logging in to SauceDemo using valid credentials. 

The test checks if the user is redirected to the login page after logging out. The test passes if the login page is visible.

To run this test, you need to make sure that:
-	Python is installed
-	Robot Framework & SeleniumLibrary are installed with pip:
        (pip install robotframework)
        (pip install robotframework-seleniumlibrary)
-   Chromedriver is installed (and updated if needed)

To execute the test you can run robot with : logout_unit_test.robot

Comments:
The resource path is set to the path that is used by the jenkins pipeline. If you want to run the test locally, use the other resource path that is in the code with a #.
No code arguments or comments are given in the robot files since this is messing up the execution of the code in jenkins. All of the comments are placed here. 
This code uses xpaths to determine what button to click or what element to wait on to appear. The xpaths are found with the dev tool in Chrome. 
This code opens an incognito version of chrome. The reason for this is a pop-up with information about "a not safe password that is used" was blocking the test on normal chrome. 
An one second sleep is added to the Logout from SauceDemo keyword. This is to prevent the Robot Framework from executing the test faster than the website can load.
The code creates a path to the screenshot folder, and a screenshot is saved. This screenshot is saved with a timestamp as a name for testing documentation. 
The screenshot is taken at the end of the test.

The expected result is that the user lands back on the login page, confirming a successful logout. 
Any other result is an unexpected result.