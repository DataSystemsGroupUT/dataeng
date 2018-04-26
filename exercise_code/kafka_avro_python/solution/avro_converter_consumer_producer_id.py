#!/usr/bin/python

import requests
import base64
import json
import sys
import re

# Read in the Avro files
shakespeare_key_solution = open("shakespeare_key_solution.avsc", 'rU').read()
shakespeare_value_solution = open("shakespeare_value_solution.avsc", 'rU').read()

# Base URL for the schema registry
schemabaseurl = "http://schemaregistry1:8081/subjects"

def registerOrGetSchemaId(topicAndSchemaName, schema):
	headers = {
		"Content-Type" : "application/vnd.schemaregistry.v1+json"
		}

	payload = {
		"schema": schema
		}

	r = requests.post(schemabaseurl + "/" + topicAndSchemaName + "/versions",
		data=json.dumps(payload), headers=headers)

	if r.status_code != 200:
		print "Status Code: " + str(r.status_code)
		print r.text

	return r.json()["id"]

# Figure out the key and value schema IDs to use with the producer
keyID = registerOrGetSchemaId("shakespeare_avro_topic-key", shakespeare_key_solution)
valueID = registerOrGetSchemaId("shakespeare_avro_topic-value", shakespeare_value_solution)

print "Key ID is " + str(keyID) + " and value ID is " + str(valueID)

# Base URL for interacting with REST server
baseurl = "http://kafkarest1:8082/consumers/group11"

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
r = requests.get(base_uri + "/topics/shakespeare_topic", headers=headers, timeout=20)

if r.status_code != 200:
	print "Status Code: " + str(r.status_code)
	print r.text
	sys.exit("Error thrown while getting message")

shakespeareWorkToYearWritten = {
	"Hamlet": 1600,
	"Julius Caesar": 1599,
	"Macbeth": 1605,
	"Merchant of Venice": 1596,
	"Othello": 1604,
	"Romeo and Juliet": 1594
}

producerurl = "http://kafkarest1:8082/topics/shakespeare_avro_topic"

headers = {
	"Content-Type" : "application/vnd.kafka.avro.v1+json"
		}

payload = {
			"key_schema_id": keyID,
			"value_schema_id": valueID,
			"records":[]}

# Output all messages
for message in r.json():
	# Producer
	key = base64.b64decode(message["key"])
	value = base64.b64decode(message["value"])

	# Batch and output messages in a loop
	p = re.compile(ur'^\s*(\d*)\s*(.*)$')
	searchObj = re.search(p, value)

	if searchObj:
		payload["records"].append(
			{
				"key": {"work": key, "year":  shakespeareWorkToYearWritten[key]},
				"value": {"line_number": int(searchObj.group(1)), "line": searchObj.group(2).strip()}
			})

		# Send the message after some messages are queued
		if len(payload["records"]) > 1:
			r = requests.post(producerurl, data=json.dumps(payload), headers=headers)

			if r.status_code != 200:
				print "Status Code: " + str(r.status_code)
				print r.text
				break

			# Clear list after sending
			payload["records"] = []
	else:
		print "Match not found:" + value + ":"

# Send any remaining messages
if len(payload["records"]) > 0:
		r = requests.post(producerurl, data=json.dumps(payload), headers=headers)

		if r.status_code != 200:
			print "Status Code: " + str(r.status_code)
			print r.text

# Delete the consumer now that we've sent the messages
headers = {
	"Accept" : "application/vnd.kafka.v1+json"
		}

r = requests.delete(base_uri, headers=headers)

if r.status_code != 204:
	print "Status Code: " + str(r.status_code)
	print r.text
	sys.exit("Error thrown while getting message")

print "Deleted consumer instance"
