# With some delay (like 3 seconds), you will request a list with personal information from a database. The response of this request is the list with all the information.
# A unit tests is also done after a 3 seconds wait to verify if the correct response is given.

import unittest
import requests
import time

class TestGetDelayedUserList(unittest.TestCase):

    def setUp(self):
        self.url = "https://reqres.in/api/users?page=2"

    def test_get_user_list(self):
        # 3 seconds of wait is simulated before the tests are done. Otherwise the tests will fail because they are to quick.
        time.sleep(3)

        response = requests.get(self.url)

        # Check if the statuscode is 200, otherwise trow an error message.
        self.assertEqual(response.status_code, 200, "Failed to fetch user list!")

        # Check if the data in the response body is a list, otherwise trow and error message
        self.assertIsInstance(response.json()["data"], list, "Response 'data' is not a list!")

        # Check if the lenght of the names in list of the response is greater than 0, otherwise trow an error message.
        self.assertGreater(len(response.json()["data"]), 0, "User list is empty!")

if __name__ == "__main__":
    unittest.main()