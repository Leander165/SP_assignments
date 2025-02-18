# This request will delete a user called Leander van Vliet in the database. It will return a 204, but the user is still in the database since this is a dummy API
#Also a unit test is done to verify if the (not having a body with) information that is returned is correct.

import unittest
import requests

class TestDeleteUser(unittest.TestCase):

    def setUp(self):
        self.url = "https://reqres.in/api/users/2"


    def test_delete_user(self):
        response = requests.delete(self.url)

        # Check if the response code is 204, otherwise trow an error message
        self.assertEqual(response.status_code, 204, "User was not deleted successfully!")

        # Check if the response body is empty, otherwise trow an error messgage
        self.assertEqual(response.text, "", "Response body should be empty!")

if __name__ == "__main__":
    unittest.main()