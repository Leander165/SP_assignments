# This request will add a user called Leander van Vliet in the database. Also it will print the response and the status code after execution.
# A unit test will be executed to make sure the response I correct.

import requests

def test_create_user():
    url = "https://reqres.in/api/users"
    data = {"name": "Leander van Vliet", "job": "IwouldLikeToWorkAtSpriteCloud"}

    response = requests.post(url, json=data)
    
    assert response.status_code == 201
    assert "id" in response.json()