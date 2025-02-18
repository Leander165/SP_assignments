*** Settings ***
Library    SeleniumLibrary
Library    DateTime
Library    OperatingSystem
#Resource   UITests/resources/keywords.robot
#Resource   UITests/resources/variables.robot
Resource   Assignments_Leander_van_Vliet/UITests/resources/keywords.robot
Resource   Assignments_Leander_van_Vliet/UITests/resources/variables.robot

*** Test Cases ***
Login on SauceDemo
    Set Screenshot Folder
    Open SauceDemo
    Login To SauceDemo
    Take Screenshot
    Close Browser
