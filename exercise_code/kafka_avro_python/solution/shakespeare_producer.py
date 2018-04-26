#!/usr/bin/python

import requests
import base64
import json
import sys
import os

if len(sys.argv) != 2:
	sys.exit("Shakespeare file must be specified.")

url = "http://kafkarest1:8082/topics/shakespeare_topic"

headers = {
	"Content-Type" : "application/vnd.kafka.binary.v1+json"
		}

def produceFile(filename):
	payload = {"records":[]}
	
	# Parse the key from the filename
	key = base64.b64encode(os.path.splitext(os.path.basename(filename))[0])

	# Open input file and send to topic
	with open(filename) as f:
		for line in f:
			payload["records"].append(
			{
				"key": key,
				"value": base64.b64encode(line.strip())
			})
			
			# Send the message after some messages are queued
			if len(payload["records"]) > 250:
				r = requests.post(url, data=json.dumps(payload), headers=headers)

				if r.status_code != 200:
					print "Status Code: " + str(r.status_code)
					print r.text
					
				# Clear list after sending
				payload["records"] = []
					
	if len(payload["records"]) > 0:
		r = requests.post(url, data=json.dumps(payload), headers=headers)

		if r.status_code != 200:
			print "Status Code: " + str(r.status_code)
			print r.text

filename = sys.argv[1]

if os.path.isfile(filename):
	produceFile(filename)
else:
	for subdir, dirs, files in os.walk(filename):
		for file in files:
			if file.endswith(".txt"):
				produceFile(os.path.join(subdir, file))

