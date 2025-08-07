@tag
Feature: Error validation
I want to use this template for my Feature file


	@ErrorValidations 
	Scenario Outline: Negative test of logging
	
		Given I landed on Ecommerce page
		When I logged in with username <name> and password <password>
		Then "Incorrect email or password." message is displayed

		Examples:
		|name					| password		|
		|test@exp.com			|Pass@12345		|