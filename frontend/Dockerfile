FROM node:15-alpine
RUN mkdir -p /app
WORKDIR /app
COPY . /app
RUN npm install
ENTRYPOINT ["npm", "run", "prod"]

