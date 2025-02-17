# This request will delete a user called Leander van Vliet in the database. It will return a 204, but the user is still in the database since this is a dummy API

import requests

url = "https://reqres.in/api/users/2"
response = requests.delete(url)

print(response.status_code)  # It should return a 204, the user is deleted.