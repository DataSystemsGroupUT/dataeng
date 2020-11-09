Hello there, brave one!

In case you'd like to do this **without** docker, here's how, for any debian distro(if you're using arch or something else then I'm more-than-sure that you're savvy enough to figure all this by yourself)

The course was is structured in a way such that in case you'd like to not use docker, it's not gonna take THAT much effort to get everything up and running.

# Airflow

0. `sudo apt-get install python3-dev`
1. Install all the necessary python packages from the `requirements.txt` file
2. ```
   sudo apt-get install -y --no-install-recommends \
        freetds-bin \
        krb5-user \
        ldap-utils \
        libffi6 \
        libsasl2-2 \
        libsasl2-modules \
        libssl1.1 \
        locales  \
        lsb-release \
        sasl2-bin \
        sqlite3 \
        unixodbc
   ```
3. http://airflow.apache.org/docs/stable/start.html <- initdb, web server and scheduler

# Postgres

https://www.postgresql.org/docs/9.3/install-short.html