### PreLab

#### 1. Install Redis on Windows
- Redis is a cross-platform DB, We can install it on Linux, or Windows, ..etc.
- There are two ways to install Redis under Windows
    - Download the latest Redis .msi file from https://github.com/MSOpenTech/redis/r... and install it. 
    
    - You can choose either from these sources
        - https://github.com/microsoftarchive/redis/releases or
        - https://github.com/rgl/redis/downloads

- Personally I prepared the first option
- Download Redis-x64-2.8.2104.zip
- Extract the zip to prepared directory
- run redis-server.exe
- then run redis-cli.exe
- For more info follow this setup-video tutourial (https://www.youtube.com/watch?v=188Fy-oCw4w)


#### 2. Linux and Debian 

- Even quicker and dirtier instructions for Debian-based Linux distributions are as follows:
    - download Redis from http://redis.io/download 
    - extract, run make && sudo make install
    - Then run sudo python -m easy_install redis hiredis (hiredis is an optional performance-improving C library).

#### 3. Install the Python Package ("<a href='https://pypi.org/project/redis/'>redis</a>") to connecto to Redis 
- use th command ```pip install redis``` in your command line.


#### 4. Accessing Redis from Command Line:
- Add the Redis installation "/home" and "/bin" directories to the enviroment variables.
- start Redis server in one command window(CMD, poweshell, ..etc)using the command ```redis-server```.
- In anoher command window, start your Redis Client using the command ```redis-cli```
- Now you have the Redis Client Shell connected to the default <b>db0</b> DB. 
