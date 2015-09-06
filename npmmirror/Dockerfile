FROM node

RUN npm install -g registry-static

CMD ["registry-static", "-d", "npm.local", "-o", "/data/npm", "-t", "/data/npm_tmp"]
