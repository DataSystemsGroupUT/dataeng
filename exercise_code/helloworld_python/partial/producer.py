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
			# TODO: Create a key and a value
		}]}

	# Send the message

	# TODO: send the request

	if r.status_code != 200:
		print "Status Code: " + str(r.status_code)
		print r.text
