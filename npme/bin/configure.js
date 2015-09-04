#!/usr/bin/env node

var fs = require('fs'),
  Interview = require('ndm').Interview,
  interview = new Interview(),
  License = require('npme').License,
  license = new License();

license.interview(function(err) {
  fs.writeFileSync('.license.json', JSON.stringify(license.license, null, 2), 'utf-8');
  interview.run(function() {
    console.log('you should now run: `docker build -t npme .`');
  });
});

process.on('uncaughtException', function(err) {
  console.log(err.message);
});
