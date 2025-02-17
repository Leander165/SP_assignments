*** Settings ***
Library    SeleniumLibrary
Library    DateTime
Library    OperatingSystem
Resource   UITests/resources/keywords.robot
Resource   UITests/resources/variables.robot

*** Test Cases ***
Buy a Shirt on SauceDemo
    Set Screenshot Folder
    Open SauceDemo
    Login To SauceDemo
    Add Shirt and Go to Cart
    Checkout Cart
    Take Screenshot
    Finish the checkout
    Close Browser
