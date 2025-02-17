This test verifies that a user can successfully buy a shirt on SauceDemo.

The test checks if the user can successfully order a shirt at the webshop and a screenshot of the order is added. The test passes when the back to products key after checkout is visible

To run this test, you need to make sure that:
-	Python is installed
-	Robot Framework & SeleniumLibrary are installed with pip:
        (pip install robotframework)
        (pip install robotframework-seleniumlibrary)
-   Chromedriver is installed (and updated if needed)

To execute the test you can run robot with : BuyAShirtEndToEndTest.robot

Comments:
No code arguments or comments are given in the robot files since this is messing up the execution of the code in jenkins. All of the comments are placed here. 
This code uses xpaths to determine what button to click or what element to wait on to appear. The xpaths are found with the dev tool in Chrome. 
This code opens an incognito version of chrome. The reason for this is a pop-up with information about "a not safe password that is used" was blocking the test on normal chrome. 
The code creates a path to the screenshot folder, and a screenshot is saved. This screenshot is saved with a timestamp as a name for testing documentation. 
The screenshot is taken at the end of the test.
An one second sleep is added to the Logout from SauceDemo keyword. This is to prevent the Robot Framework from executing the test faster than the website can load.
To make sure the robot clicks on the shopping cart logo in the right corner: We added     Click Element    xpath=//a[@class='shopping_cart_link']/span. This is done because the "button" could not be found by robot, so we used an element instead.
An one second sleep is also added after adding the t-shirt, and clicking on the shopping cart element. This is also to prevent the Robot from executing the test faster than the website can load.

The expected result is that the user lands on the "order overview" page , confirming a successful order. This is confirmed by the screenshot.
Any other result is an unexpected result.