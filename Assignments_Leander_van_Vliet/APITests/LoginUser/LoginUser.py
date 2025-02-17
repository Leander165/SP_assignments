# This request will login with a user called eve.holt. You will recieve a token to login with in the console as a response, also it will print the status code after execution. 


import requests

url = "https://reqres.in/api/login"
data = {
    "email": "eve.holt@reqres.in",
    "password": "cityslicka"
}

response = requests.post(url, json=data)

print("Status Code:", response.status_code)
print("Response:", response.json())