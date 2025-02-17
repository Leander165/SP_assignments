*** Settings ***
Library    SeleniumLibrary
Library    DateTime
Library    OperatingSystem
Resource   UITests/resources/keywords.robot
Resource   UITests/resources/variables.robot

*** Test Cases ***
Logging out from SauceDemo
    Set Screenshot Folder
    Open SauceDemo
    Login To SauceDemo
    Logout From SauceDemo
    Take Screenshot
    Close Browser
