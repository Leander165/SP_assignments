# With some delay (like 3 seconds), you will request a list with personal information from a database. The response of this request is the list with all the information.
import requests

url = "https://reqres.in/api/users?page=2"
response = requests.get(url)

print(response.status_code)  # Should return a 200 code
print(response.json())  # Returns the API response