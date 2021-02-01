FROM node:15-alpine
RUN mkdir /app
WORKDIR /app
COPY latest.json .
RUN npm -g install http-server
CMD http-server -a 0.0.0.0 -p 9000
