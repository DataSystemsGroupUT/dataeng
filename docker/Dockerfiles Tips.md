# Tips for efficient Dockerfiles

We will see how to:

* Reduce the number of layers.

* Leverage the build cache so that builds can be faster.

* Embed unit testing in the build process.

---

## Using image and viewing history

The `history` command lists all the layers composing an image.

For each layer, it shows its creation time, size, and creation command.

When an image was built with a Dockerfile, each layer corresponds to
a line of the Dockerfile.

```bash
$ docker history figlet
IMAGE         CREATED            CREATED BY                     SIZE
f9e8f1642759  About an hour ago  /bin/sh -c apt-get install fi  1.627 MB
7257c37726a1  About an hour ago  /bin/sh -c apt-get update      21.58 MB
07c86167cdc4  4 days ago         /bin/sh -c #(nop) CMD ["/bin   0 B
<missing>     4 days ago         /bin/sh -c sed -i 's/^#\s*\(   1.895 kB
<missing>     4 days ago         /bin/sh -c echo '#!/bin/sh'    194.5 kB
<missing>     4 days ago         /bin/sh -c #(nop) ADD file:b   187.8 MB
```

---



## Why `sh -c`?

* On UNIX, to start a new program, we need two system calls:

  - `fork()`, to create a new child process;

  - `execve()`, to replace the new child process with the program to run.

* Conceptually, `execve()` works like this:

  `execve(program, [list, of, arguments])`

* When we run a command, e.g. `ls -l /tmp`, something needs to parse the command.

  (i.e. split the program and its arguments into a list.)

* The shell is usually doing that.

  (It also takes care of expanding environment variables and special things like `~`.)

---



## Why `sh -c`?

* When we do `RUN ls -l /tmp`, the Docker builder needs to parse the command.

* Instead of implementing its own parser, it outsources the job to the shell.

* That's why we see `sh -c ls -l /tmp` in that case.

* But we can also do the parsing jobs ourselves.

* This means passing `RUN` a list of arguments.

* This is called the *exec syntax*.

---

## Shell syntax vs exec syntax

Dockerfile commands that execute something can have two forms:

* plain string, or *shell syntax*:
  <br/>`RUN apt-get install figlet`

* JSON list, or *exec syntax*:
  <br/>`RUN ["apt-get", "install", "figlet"]`

We are going to change our Dockerfile to see how it affects the resulting image.

---

## Using exec syntax in our Dockerfile

Let's change our Dockerfile as follows!

```dockerfile
FROM ubuntu
RUN apt-get update
RUN ["apt-get", "install", "figlet"]
```

Then build the new Dockerfile.

```bash
$ docker build -t figlet .
```

---

## History with exec syntax

Compare the new history:

```bash
$ docker history figlet
IMAGE         CREATED            CREATED BY                     SIZE
27954bb5faaf  10 seconds ago     apt-get install figlet         1.627 MB
7257c37726a1  About an hour ago  /bin/sh -c apt-get update      21.58 MB
07c86167cdc4  4 days ago         /bin/sh -c #(nop) CMD ["/bin   0 B
<missing>     4 days ago         /bin/sh -c sed -i 's/^#\s*\(   1.895 kB
<missing>     4 days ago         /bin/sh -c echo '#!/bin/sh'    194.5 kB
<missing>     4 days ago         /bin/sh -c #(nop) ADD file:b   187.8 MB
```

* Exec syntax specifies an *exact* command to execute.

* Shell syntax specifies a command to be wrapped within `/bin/sh -c "..."`.

---

## When to use exec syntax and shell syntax

* shell syntax:

  * is easier to write
  * interpolates environment variables and other shell expressions
  * creates an extra process (`/bin/sh -c ...`) to parse the string
  * requires `/bin/sh` to exist in the container

* exec syntax:

  * is harder to write (and read!)
  * passes all arguments without extra processing
  * doesn't create an extra process
  * doesn't require `/bin/sh` to exist in the container

---

## Pro-tip: the `exec` shell built-in

POSIX shells have a built-in command named `exec`.

`exec` should be followed by a program and its arguments.

From a user perspective:

- it looks like the shell exits right away after the command execution,

- in fact, the shell exits just *before* command execution;

- or rather, the shell gets *replaced* by the command.

---

## Example using `exec`

```dockerfile
CMD exec figlet -f script hello
```

In this example, `sh -c` will still be used, but
`figlet` will be PID 1 in the container.

The shell gets replaced by `figlet` when `figlet` starts execution.

This allows to run processes as PID 1 without using JSON.

---

## Reducing the number of layers

* Each line in a `Dockerfile` creates a new layer.

* Build your `Dockerfile` to take advantage of Docker's caching system.

* Combine commands by using `&&` to continue commands and `\` to wrap lines.

Note: it is frequent to build a Dockerfile line by line:

```dockerfile
RUN apt-get install thisthing
RUN apt-get install andthatthing andthatotherone
RUN apt-get install somemorestuff
```

And then refactor it trivially before shipping:

```dockerfile
RUN apt-get install thisthing andthatthing andthatotherone somemorestuff
```

---

## Avoid re-installing dependencies at each build

* Classic Dockerfile problem:

  "each time I change a line of code, all my dependencies are re-installed!"

* Solution: `COPY` dependency lists (`package.json`, `requirements.txt`, etc.)
  by themselves to avoid reinstalling unchanged dependencies every time.

---

## Example "bad" `Dockerfile`

The dependencies are reinstalled every time, because the build system does not know if `requirements.txt` has been updated.

```bash
FROM python
WORKDIR /src
COPY . .
RUN pip install -qr requirements.txt
EXPOSE 5000
CMD ["python", "app.py"]
```

---

## Fixed `Dockerfile`

Adding the dependencies as a separate step means that Docker can cache more efficiently and only install them when `requirements.txt` changes.

```bash
FROM python
COPY requirements.txt /tmp/requirements.txt
RUN pip install -qr /tmp/requirements.txt
WORKDIR /src
COPY . .
EXPOSE 5000
CMD ["python", "app.py"]
```

---

## Be careful with `chown`, `chmod`, `mv`

* Layers cannot store efficiently changes in permissions or ownership.

* Layers cannot represent efficiently when a file is moved either.

* As a result, operations like `chown`, `chown`, `mv` can be expensive.

* For instance, in the Dockerfile snippet below, each `RUN` line
  creates a layer with an entire copy of `some-file`.

  ```dockerfile
  COPY some-file .
  RUN chown www-data:www-data some-file
  RUN chmod 644 some-file
  RUN mv some-file /var/www
  ```

* How can we avoid that?

---

## Put files on the right place

* Instead of using `mv`, directly put files at the right place.

* When extracting archives (tar, zip...), merge operations in a single layer.

  Example:

  ```dockerfile
    ...
    RUN wget http://.../foo.tar.gz \
     && tar -zxf foo.tar.gz \
     && mv foo/fooctl /usr/local/bin \
     && rm -rf foo
  ...
  ```

---

## Use `COPY --chown`

* The Dockerfile instruction `COPY` can take a `--chown` parameter.

  Examples:

  ```dockerfile
  ...
  COPY --chown=1000 some-file .
  COPY --chown=1000:1000 some-file .
  COPY --chown=www-data:www-data some-file .
  ```

* The `--chown` flag can specify a user, or a user:group pair.

* The user and group can be specified as names or numbers.

* When using names, the names must exist in `/etc/passwd` or `/etc/group`.

  *(In the container, not on the host!)*

---

## Set correct permissions locally

* Instead of using `chmod`, set the right file permissions locally.

* When files are copied with `COPY`, permissions are preserved.

---

## Embedding unit tests in the build process

```dockerfile
FROM <baseimage>
RUN <install dependencies>
COPY <code>
RUN <build code>
RUN <install test dependencies>
COPY <test data sets and fixtures>
RUN <unit tests>
FROM <baseimage>
RUN <install dependencies>
COPY <code>
RUN <build code>
CMD, EXPOSE ...
```

* The build fails as soon as an instruction fails
* If `RUN <unit tests>` fails, the build doesn't produce an image
* If it succeeds, it produces a clean image (without test libraries and data)

---

# Dockerfile examples

There are a number of tips, tricks, and techniques that we can use in Dockerfiles.

But sometimes, we have to use different (and even opposed) practices depending on:

- the complexity of our project,

- the programming language or framework that we are using,

- the stage of our project (early MVP vs. super-stable production),

- whether we're building a final image or a base for further images,

- etc.

We are going to show a few examples using very different techniques.

---

## When to optimize an image

When authoring official images, it is a good idea to reduce as much as possible:

- the number of layers,

- the size of the final image.

This is often done at the expense of build time and convenience for the image maintainer;
but when an image is downloaded millions of time, saving even a few seconds of pull time
can be worth it.

.small[
```dockerfile
RUN apt-get update && apt-get install -y libpng12-dev libjpeg-dev && rm -rf /var/lib/apt/lists/* \
	&& docker-php-ext-configure gd --with-png-dir=/usr --with-jpeg-dir=/usr \
	&& docker-php-ext-install gd
...
RUN curl -o wordpress.tar.gz -SL https://wordpress.org/wordpress-${WORDPRESS_UPSTREAM_VERSION}.tar.gz \
	&& echo "$WORDPRESS_SHA1 *wordpress.tar.gz" | sha1sum -c - \
	&& tar -xzf wordpress.tar.gz -C /usr/src/ \
	&& rm wordpress.tar.gz \
	&& chown -R www-data:www-data /usr/src/wordpress
```
]

(Source: [Wordpress official image](https://github.com/docker-library/wordpress/blob/618490d4bdff6c5774b84b717979bfe3d6ba8ad1/apache/Dockerfile))

---

## When to *not* optimize an image

Sometimes, it is better to prioritize *maintainer convenience*.

In particular, if:

- the image changes a lot,

- the image has very few users (e.g. only 1, the maintainer!),

- the image is built and run on the same machine,

- the image is built and run on machines with a very fast link ...

In these cases, just keep things simple!

(Next slide: a Dockerfile that can be used to preview a Jekyll / github pages site.)

---

```dockerfile
FROM debian:sid

RUN apt-get update -q
RUN apt-get install -yq build-essential make
RUN apt-get install -yq zlib1g-dev
RUN apt-get install -yq ruby ruby-dev
RUN apt-get install -yq python-pygments
RUN apt-get install -yq nodejs
RUN apt-get install -yq cmake
RUN gem install --no-rdoc --no-ri github-pages

COPY . /blog
WORKDIR /blog

VOLUME /blog/_site

EXPOSE 4000
CMD ["jekyll", "serve", "--host", "0.0.0.0", "--incremental"]
```

---

## Multi-dimensional versioning systems

Images can have a tag, indicating the version of the image.

But sometimes, there are multiple important components, and we need to indicate the versions
for all of them.

This can be done with environment variables:

```dockerfile
ENV PIP=9.0.3 \
    ZC_BUILDOUT=2.11.2 \
    SETUPTOOLS=38.7.0 \
    PLONE_MAJOR=5.1 \
    PLONE_VERSION=5.1.0 \
    PLONE_MD5=76dc6cfc1c749d763c32fff3a9870d8d
```

(Source: [Plone official image](https://github.com/plone/plone.docker/blob/master/5.1/5.1.0/alpine/Dockerfile))

---

## Entrypoints and wrappers

It is very common to define a custom entrypoint.

That entrypoint will generally be a script, performing any combination of:

- pre-flights checks (if a required dependency is not available, display
  a nice error message early instead of an obscure one in a deep log file),

- generation or validation of configuration files,

- dropping privileges (with e.g. `su` or `gosu`, sometimes combined with `chown`),

- and more.

---

## A typical entrypoint script

```dockerfile
 #!/bin/sh
 set -e
 
 # first arg is '-f' or '--some-option'
 # or first arg is 'something.conf'
 if [ "${1#-}" != "$1" ] || [ "${1%.conf}" != "$1" ]; then
 	set -- redis-server "$@"
 fi
 
 # allow the container to be started with '--user'
 if [ "$1" = 'redis-server' -a "$(id -u)" = '0' ]; then
 	chown -R redis .
 	exec su-exec redis "$0" "$@"
 fi
 
 exec "$@"
```

(Source: [Redis official image](https://github.com/docker-library/redis/blob/d24f2be82673ccef6957210cc985e392ebdc65e4/4.0/alpine/docker-entrypoint.sh))

---

## Factoring information

To facilitate maintenance (and avoid human errors), avoid to repeat information like:

- version numbers,

- remote asset URLs (e.g. source tarballs) ...

Instead, use environment variables.

.small[
```dockerfile
ENV NODE_VERSION 10.2.1
...
RUN ...
    && curl -fsSLO --compressed "https://nodejs.org/dist/v$NODE_VERSION/node-v$NODE_VERSION.tar.xz" \
    && curl -fsSLO --compressed "https://nodejs.org/dist/v$NODE_VERSION/SHASUMS256.txt.asc" \
    && gpg --batch --decrypt --output SHASUMS256.txt SHASUMS256.txt.asc \
    && grep " node-v$NODE_VERSION.tar.xz\$" SHASUMS256.txt | sha256sum -c - \
    && tar -xf "node-v$NODE_VERSION.tar.xz" \
    && cd "node-v$NODE_VERSION" \
...
```
]

(Source: [Nodejs official image](https://github.com/nodejs/docker-node/blob/master/10/alpine/Dockerfile))

---

## Overrides

In theory, development and production images should be the same.

In practice, we often need to enable specific behaviors in development (e.g. debug statements).

One way to reconcile both needs is to use Compose to enable these behaviors.

Let's look at the [trainingwheels](https://github.com/jpetazzo/trainingwheels) demo app for an example.

---

## Production image

This Dockerfile builds an image leveraging gunicorn:

```dockerfile
FROM python
RUN pip install flask
RUN pip install gunicorn
RUN pip install redis
COPY . /src
WORKDIR /src
CMD gunicorn --bind 0.0.0.0:5000 --workers 10 counter:app
EXPOSE 5000
```

(Source: [trainingwheels Dockerfile](https://github.com/jpetazzo/trainingwheels/blob/master/www/Dockerfile))

---

## Development Compose file

This Compose file uses the same image, but with a few overrides for development:

- the Flask development server is used (overriding `CMD`),

- the `DEBUG` environment variable is set,

- a volume is used to provide a faster local development workflow.

.small[
```yaml
services:
  www:
    build: www
    ports:
      - 8000:5000
    user: nobody
    environment:
      DEBUG: 1
    command: python counter.py
    volumes:
      - ./www:/src
```
]

(Source: [trainingwheels Compose file](https://github.com/jpetazzo/trainingwheels/blob/master/docker-compose.yml))

---

## How to know which best practices are better?

- The main goal of containers is to make our lives easier.

- In this chapter, we showed many ways to write Dockerfiles.

- These Dockerfiles use sometimes diametrally opposed techniques.

- Yet, they were the "right" ones *for a specific situation.*

- It's OK (and even encouraged) to start simple and evolve as needed.

- Feel free to review this chapter later (after writing a few Dockerfiles) for inspiration!





# `CMD` and `ENTRYPOINT`

![Container entry doors](entrypoint.jpg)

---

## Objectives

In this lesson, we will learn about two important
Dockerfile commands:

`CMD` and `ENTRYPOINT`.

These commands allow us to set the default command
to run in a container.

---

## Defining a default command

When people run our container, we want to greet them with a nice hello message, and using a custom font.

For that, we will execute:

```bash
figlet -f script hello
```

* `-f script` tells figlet to use a fancy font.

* `hello` is the message that we want it to display.

---

## Adding `CMD` to our Dockerfile

Our new Dockerfile will look like this:

```dockerfile
FROM ubuntu
RUN apt-get update
RUN ["apt-get", "install", "figlet"]
CMD figlet -f script hello
```

* `CMD` defines a default command to run when none is given.

* It can appear at any point in the file.

* Each `CMD` will replace and override the previous one.

* As a result, while you can have multiple `CMD` lines, it is useless.

---

## Build and test our image

Let's build it:

```bash
$ docker build -t figlet .
...
Successfully built 042dff3b4a8d
Successfully tagged figlet:latest
```

And run it:

```bash
$ docker run figlet
 _          _   _       
| |        | | | |      
| |     _  | | | |  __  
|/ \   |/  |/  |/  /  \_
|   |_/|__/|__/|__/\__/ 
```

---

## Overriding `CMD`

If we want to get a shell into our container (instead of running
`figlet`), we just have to specify a different program to run:

```bash
$ docker run -it figlet bash
root@7ac86a641116:/# 
```

* We specified `bash`.

* It replaced the value of `CMD`.

---

## Using `ENTRYPOINT`

We want to be able to specify a different message on the command line,
while retaining `figlet` and some default parameters.

In other words, we would like to be able to do this:

```bash
$ docker run figlet salut
           _            
          | |           
 ,   __,  | |       _|_ 
/ \_/  |  |/  |   |  |  
 \/ \_/|_/|__/ \_/|_/|_/
```


We will use the `ENTRYPOINT` verb in Dockerfile.

---

## Adding `ENTRYPOINT` to our Dockerfile

Our new Dockerfile will look like this:

```dockerfile
FROM ubuntu
RUN apt-get update
RUN ["apt-get", "install", "figlet"]
ENTRYPOINT ["figlet", "-f", "script"]
```

* `ENTRYPOINT` defines a base command (and its parameters) for the container.

* The command line arguments are appended to those parameters.

* Like `CMD`, `ENTRYPOINT` can appear anywhere, and replaces the previous value.

Why did we use JSON syntax for our `ENTRYPOINT`?

---

## Implications of JSON vs string syntax

* When CMD or ENTRYPOINT use string syntax, they get wrapped in `sh -c`.

* To avoid this wrapping, we can use JSON syntax.

What if we used `ENTRYPOINT` with string syntax?

```bash
$ docker run figlet salut
```

This would run the following command in the `figlet` image:

```bash
sh -c "figlet -f script" salut
```

---

## Build and test our image

Let's build it:

```bash
$ docker build -t figlet .
...
Successfully built 36f588918d73
Successfully tagged figlet:latest
```

And run it:

```bash
$ docker run figlet salut
           _            
          | |           
 ,   __,  | |       _|_ 
/ \_/  |  |/  |   |  |  
 \/ \_/|_/|__/ \_/|_/|_/
```

---

## Using `CMD` and `ENTRYPOINT` together

What if we want to define a default message for our container?

Then we will use `ENTRYPOINT` and `CMD` together.

* `ENTRYPOINT` will define the base command for our container.

* `CMD` will define the default parameter(s) for this command.

* They *both* have to use JSON syntax.

---

## `CMD` and `ENTRYPOINT` together

Our new Dockerfile will look like this:

```dockerfile
FROM ubuntu
RUN apt-get update
RUN ["apt-get", "install", "figlet"]
ENTRYPOINT ["figlet", "-f", "script"]
CMD ["hello world"]
```

* `ENTRYPOINT` defines a base command (and its parameters) for the container.

* If we don't specify extra command-line arguments when starting the container,
  the value of `CMD` is appended.

* Otherwise, our extra command-line arguments are used instead of `CMD`.

---

## Build and test our image

Let's build it:

```bash
$ docker build -t myfiglet .
...
Successfully built 6e0b6a048a07
Successfully tagged myfiglet:latest
```

Run it without parameters:

```bash
$ docker run myfiglet
 _          _   _                             _        
| |        | | | |                           | |    |  
| |     _  | | | |  __             __   ,_   | |  __|  
|/ \   |/  |/  |/  /  \_  |  |  |_/  \_/  |  |/  /  |  
|   |_/|__/|__/|__/\__/    \/ \/  \__/    |_/|__/\_/|_/
```

---

## Overriding the image default parameters

Now let's pass extra arguments to the image.

```bash
$ docker run myfiglet hola mundo
 _           _                                               
| |         | |                                      |       
| |     __  | |  __,     _  _  _           _  _    __|   __  
|/ \   /  \_|/  /  |    / |/ |/ |  |   |  / |/ |  /  |  /  \_
|   |_/\__/ |__/\_/|_/    |  |  |_/ \_/|_/  |  |_/\_/|_/\__/ 
```

We overrode `CMD` but still used `ENTRYPOINT`.

---

## Overriding `ENTRYPOINT`

What if we want to run a shell in our container?

We cannot just do `docker run myfiglet bash` because
that would just tell figlet to display the word "bash."

We use the `--entrypoint` parameter:

```bash
$ docker run -it --entrypoint bash myfiglet
root@6027e44e2955:/# 
```

???

:EN:- CMD and ENTRYPOINT
:FR:- CMD et ENTRYPOINT
§

