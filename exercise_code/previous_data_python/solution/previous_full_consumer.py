#!/usr/bin/python

import requests
import base64
import json
import sys

# Base URL for interacting with REST server
baseurl = "http://kafkarest1:8082/consumers/group1"

# Create the consumer instance
print "Creating consumer instance"

payload = {
	"format": "binary",
	"auto.offset.reset": "smallest"
	}

headers = {
"Content-Type" : "application/vnd.kafka.v1+json"
	}

r = requests.post(baseurl, data=json.dumps(payload), headers=headers)

if r.status_code != 200:
	print "Status Code: " + str(r.status_code)
	print r.text
	sys.exit("Error thrown while creating consumer")

base_uri = r.json()["base_uri"]

print "Base URI:" + base_uri

# Get the message(s) from the consumer
headers = {
	"Accept" : "application/vnd.kafka.binary.v1+json"
		}

# Request messages for the instance on the topic
r = requests.get(base_uri + "/topics/hello_world_topic", headers=headers, timeout=20)

if r.status_code != 200:
	print "Status Code: " + str(r.status_code)
	print r.text
	sys.exit("Error thrown while getting message")

# Output all messages
for message in r.json():
	if message["key"] is not None:
		print "Message Key:" + base64.b64decode(message["key"])
	
	print "Message Value:" + base64.b64decode(message["value"]) 

# Delete the consumer now that we've sent the messages
headers = {
	"Accept" : "application/vnd.kafka.v1+json"
		}

r = requests.delete(base_uri, headers=headers)

if r.status_code != 204:
	print "Status Code: " + str(r.status_code)
	print r.text
	sys.exit("Error thrown while getting message")
