#!/usr/bin/python

import requests
import base64
import json
import sys

# Base URL for interacting with REST server
baseurl = "http://kafkarest1:8082/consumers/group3"

# Create the consumer instance
print "Creating consumer instance"

payload = {
	"format": "avro",
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
	"Accept" : "application/vnd.kafka.avro.v1+json"
		}

# Request messages for the instance on the topic

# TODO: get the messages into a variable called 'r'

if r.status_code != 200:
	print "Status Code: " + str(r.status_code)
	print r.text
	sys.exit("Error thrown while getting message")


# TODO: Loop round the messages

	# TODO: Write out the play name, year, and line of the play
					
# Delete the consumer now that we've sent the messages
headers = {
	"Accept" : "application/vnd.kafka.v1+json"
		}

r = requests.delete(base_uri, headers=headers)

if r.status_code != 204:
	print "Status Code: " + str(r.status_code)
	print r.text
	sys.exit("Error thrown while getting message")
