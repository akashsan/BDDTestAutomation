Feature: Login
Description: This feature is to test the login functionality

Scenario: Login with Invalid Password Redirects to Unsuccessful Login
Given a logged out user on the Login page
When the user provides a valid email with an invalid password and selects log in
Then the user shall be redirected to the unsuccessful login page