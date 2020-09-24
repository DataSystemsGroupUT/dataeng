Make a simple change to logs: sequence of key-value pairs is sorted by key.

Merging segments is simple and efficient, even if the files are bigger than the available memory (mergesort algorithm).

In order to find a particular key in the file, you no longer just need a spare index of the offsets