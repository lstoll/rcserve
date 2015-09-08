FROM node

RUN npm install -g registry-static

CMD ["registry-static", "-d", "npm.camp", "-o", "/data/npm", "-t", "/data/npm_tmp"]
