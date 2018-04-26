#!/usr/bin/python

import requests
import base64
import json

url = "http://kafkarest1:8082/topics/hello_world_topic"

headers = {
	"Content-Type" : "application/vnd.kafka.binary.v1+json"
		}

# Output messages in a loop
for i in range(0,5):
	payload = {"records":
		[{
			"key":base64.b64encode("firstkey"),
			"value":base64.b64encode("firstvalue" + str(i))
		}]}

	# Send the message
	r = requests.post(url, data=json.dumps(payload), headers=headers)

	if r.status_code != 200:
		print "Status Code: " + str(r.status_code)
		print r.text
