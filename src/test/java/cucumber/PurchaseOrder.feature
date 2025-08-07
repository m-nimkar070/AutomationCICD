@tag
Feature: Purchase the order from Ecommerce website
I want to use this template for my Feature file

	Background:
	Given I landed on Ecommerce page

	@Regression 
	Scenario Outline: Positive test of submitting the order
	
		Given I logged in with username <name> and password <password>
		When I add product <productName> into cart
		And  Checkout <productName> and submit the order
		Then "Thankyou for the order." message displayed on the confirmation page

		Examples:
		|name					| password		|productName	|
		|testinguser@gmail.com	|Pass@123		|ZARA COAT 3	|