FROM mongo:4.4.6

ADD mongo-init.js /docker-entrypoint-initdb.d/
RUN chmod 0755 /docker-entrypoint-initdb.d/mongo-init.js

EXPOSE 27017
EXPOSE 28017
