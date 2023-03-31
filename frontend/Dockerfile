# base image
FROM node:16-alpine

# set working directory
WORKDIR /app

# copy dependencies file
COPY package.json .

# install dependencies
RUN npm install

# copy app files
COPY . .

# start app
CMD ["npm", "start"]

# expose port
EXPOSE 3001
