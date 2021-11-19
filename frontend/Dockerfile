FROM node:14.17.2
WORKDIR ./

COPY package.json .

ADD . .
RUN npm install
# RUN npm install webpack@2.5.1
CMD ["npm", "run", "serve"]
