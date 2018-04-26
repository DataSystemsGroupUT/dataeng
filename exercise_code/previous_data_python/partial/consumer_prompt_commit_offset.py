#!/usr/bin/python

import requests
import base64
import json
import sys


def query_yes_no(question, default="yes"):
	valid = {"yes": True, "y": True, "ye": True,
			 "no": False, "n": False}
	if default is None:
		prompt = " [y/n] "
	elif default == "yes":
		prompt = " [Y/n] "
	elif default == "no":
		prompt = " [y/N] "
	else:
		raise ValueError("invalid default answer: '%s'" % default)

	while True:
		sys.stdout.write(question + prompt)
		choice = raw_input().lower()
		if default is not None and choice == '':
			return valid[default]
		elif choice in valid:
			return valid[choice]
		else:
			sys.stdout.write("Please respond with 'yes' or 'no' "
							 "(or 'y' or 'n').\n")



# Base URL for interacting with REST server
baseurl = "http://kafkarest1:8082/consumers/group1"

# Create the consumer instance
print "Creating consumer instance"

payload = {
	"format": "binary",
	"auto.offset.reset": "smallest",
        # Note that the REST API default to auto commit is false, unlike in Java where the default is true
	# TODO: explicitly define key "auto.commit.enable" set to false
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


if query_yes_no("Commit the current offset?"):

	# Commit the offsets
	# TODO: post a commit for the offsets and return the response into variable 'r'

	if r.status_code != 200:
		print "Status Code: " + str(r.status_code)
		print r.text
		sys.exit("Error thrown while committing")

	print "*** Committed offset"
else:
	print "*** Not committed offset"



# Delete the consumer now that we've sent the messages
headers = {
	"Accept" : "application/vnd.kafka.v1+json"
		}

r = requests.delete(base_uri, headers=headers)

if r.status_code != 204:
	print "Status Code: " + str(r.status_code)
	print r.text
	sys.exit("Error thrown while getting message")

print "Consumer deleted"

