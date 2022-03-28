FROM node:15.6.0-alpine3.10

EXPOSE 9001 
COPY . /usr/app/Icecream-frontend
WORKDIR /usr/app/Icecream-frontend

RUN npm install

ENTRYPOINT npm start

