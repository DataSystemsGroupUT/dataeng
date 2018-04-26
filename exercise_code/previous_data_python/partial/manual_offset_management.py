#!/usr/bin/python

import requests
import base64
import json
import sys
import os


offsets_filename = "offsets.txt"

# Create file used for offset management, if it does not exist
if not os.path.exists(offsets_filename):
  offset_curr = '0'
  fh = open(offsets_filename, 'w')
  # TODO: write the value of offset_curr (initialized to 0) to the file tracking offsets
  fh.close()


while (True):

  # Read current offset
  fh = open(offsets_filename, 'r')
  offset_curr = fh.read().rstrip('\n')
  fh.close()

  # TODO: send a GET request to the REST proxy for topic `hello_world_topic` to retrieve a single message from partition 0 at the offset specified by offset_curr

  if r.status_code != 200:
    #print "Status Code: " + str(r.status_code)
    #print r.text
    sys.exit("No more messages!")

  # Decode and output key/value
  for message in r.json():
    if message["key"] is not None:
      # TODO: decode the Base64 encoded key and print it

    # TODO: decode the Base64 encoded value and print it
  
  # Increment offset
  offset_curr = int(offset_curr) + 1

  # Save offset 
  fh = open(offsets_filename, 'w')
  fh.write(str(offset_curr))
  fh.close()

