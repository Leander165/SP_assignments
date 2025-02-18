*** Keywords ***
Open SauceDemo
    Open Browser    ${URL}    ${BROWSER}    options=add_argument("--incognito")    
    Maximize Browser Window

Login To SauceDemo
    Input Text      id=user-name    ${USERNAME}
    Input Text      id=password    ${PASSWORD}
    Click Button    xpath=//*[@id="login-button"]
    Wait Until Page Contains Element    xpath=//*[@id="react-burger-menu-btn"]    timeout=5s

Close Browser And Take Screenshot
    Close Browser

Logout from SauceDemo
    Click Button    xpath=//*[@id="react-burger-menu-btn"]
    Wait Until Element Is Visible    xpath=//*[@id="logout_sidebar_link"]    timeout=5s
    sleep    1s    
    Click Element    xpath=//*[@id="logout_sidebar_link"]
    Wait Until Page Contains Element    xpath=//*[@id="login-button"]   timeout=5s

Set Screenshot Folder
    ${TEST_NAME}=    Set Variable    ${SUITE NAME}  
    ${TEST_NAME}=    Evaluate    "${TEST_NAME}".replace(".robot", "")  
    ${SCREENSHOT_PATH}=    Set Variable    Assignments_Leander_van_Vliet/UITests/${TEST_NAME}/screenshots/ 
    Set Global Variable    ${SCREENSHOT_PATH}

Add Shirt and Go to Cart
    Click Button    xpath=//*[@id="add-to-cart-sauce-labs-bolt-t-shirt"]
    Sleep    1s
    Click Element    xpath=//a[@class='shopping_cart_link']/span
    Wait Until Page Contains Element    xpath=//*[@id="item_1_title_link"]/div    timeout=5s

Checkout Cart
    Click Button    xpath=//*[@id="checkout"]
    Wait Until Page Contains Element    xpath=//*[@id="continue"]    timeout=5s
    Input Text      id=first-name    ${FIRSTNAME}
    Input Text      id=last-name    ${LASTNAME}
    Input Text      id=postal-code    ${ZIPCODE}
    Click Button    xpath=//*[@id="continue"]

Finish the checkout
    Wait Until Page Contains Element    xpath=//*[@id="finish"]  timeout=5s
    Click Button    xpath=//*[@id="finish"]
    Wait Until Page Contains Element    xpath=//*[@id="back-to-products"]    timeout=5s



Take Screenshot
    ${timestamp}=    Get Current Date    result_format=%Y%m%d_%H%M%S
    ${screenshot_name}=    Set Variable    ${SCREENSHOT_PATH}screenshot_${timestamp}.png
    Capture Page Screenshot    ${screenshot_name}