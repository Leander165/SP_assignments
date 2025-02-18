# This request will login with a user called eve.holt. You will recieve a token to login with in the console as a response, also it will print the status code after execution. 
# An unit test is used to confirm that a 200 message and a token is recieved in the response.


import unittest
import requests

class TestUserLogin(unittest.TestCase):

    def setUp(self):
        self.url = "https://reqres.in/api/login"
        self.valid_data = {
            "email": "eve.holt@reqres.in",
            "password": "cityslicka"
        }

    def test_successful_login(self):
        response = requests.post(self.url, json=self.valid_data)

        # Check if the response code is 200, otherwise print an error message
        self.assertEqual(response.status_code, 200, "Login failed with valid credentials!")

        # Check if the response has a "token", otherwise print an error message
        self.assertIn("token", response.json(), "No token received after login!")

if __name__ == "__main__":
    unittest.main()