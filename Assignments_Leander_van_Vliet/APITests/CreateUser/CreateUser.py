# This request will add a user called Leander van Vliet in the database. Also it will print the response and the status code after execution.
# A unit test will be executed to make sure the response I correct.

import unittest
import requests

class TestCreateUser(unittest.TestCase):

    def setUp(self):
        """Setup test variables"""
        self.url = "https://reqres.in/api/users"
        self.data = {
            "name": "Leander van Vliet",
            "job": "IwouldLikeToWorkAtSpriteCloud"
        }

    def test_create_user(self):
        response = requests.post(self.url, json=self.data)

        # Check if response code is 201
        self.assertEqual(response.status_code, 201, "User was not created successfully!")

        # Check if response json equals the name and job
        response_json = response.json()
        self.assertEqual(response_json.get("name"), self.data["name"], "Name does not match!")
        self.assertEqual(response_json.get("job"), self.data["job"], "Job does not match!")

        # Check if an ID and a "createdAt" are in the response
        self.assertIn("id", response_json, "ID is missing in response!")
        self.assertIn("createdAt", response_json, "CreatedAt timestamp is missing!")

if __name__ == "__main__":
    unittest.main()