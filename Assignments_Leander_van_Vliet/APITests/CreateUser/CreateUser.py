# This request will add a user called Leander van Vliet in the database. Also it will print the response and the status code after execution. 

import requests

url = "https://reqres.in/api/users"
data = {
    "name": "Leander van Vliet",
    "job": "IwouldLikeToWorkAtSpriteCloud"
}

response = requests.post(url, json=data)

print(response.status_code)  # should return a 201
print(response.json())  # The respons will show that the user is created with a certain timestamp