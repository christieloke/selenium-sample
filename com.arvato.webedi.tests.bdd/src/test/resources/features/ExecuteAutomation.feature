Feature: Execute Automation
  Scenario: Simple Login
    Given open 'http://executeautomation.com/demosite/Login.html'
    And type 'abc' into 'username.textbox'
    And type 'def' into 'password.textbox'
    And click 'login.button'
    And get screenshot