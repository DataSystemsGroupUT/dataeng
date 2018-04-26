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
  fh.write(offset_curr)
  fh.close()


while (True):

  # Read current offset
  fh = open(offsets_filename, 'r')
  offset_curr = fh.read().rstrip('\n')
  fh.close()

  # Get 1 message at specified offset
  r = requests.get( "http://localhost:8082/topics/hello_world_topic/partitions/0/messages?offset=%s&count=1" % (offset_curr), timeout=20)

  if r.status_code != 200:
    #print "Status Code: " + str(r.status_code)
    #print r.text
    sys.exit("No more messages!")

  # Decode and output key/value
  for message in r.json():
    if message["key"] is not None:
      print "Message Key:" + base64.b64decode(message["key"])
    print "Message Value:" + base64.b64decode(message["value"])
  
  # Increment offset
  offset_curr = int(offset_curr) + 1

  # Save offset 
  fh = open(offsets_filename, 'w')
  fh.write(str(offset_curr))
  fh.close()

