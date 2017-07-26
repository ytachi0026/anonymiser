//test.js


fs = require('fs');

var jsonfile = require('jsonfile')
 
var file = '/Users/mikeellinas/Desktop/PEACH/PEACH-anonymiser/nodeTest/data.json'
var obj = {name: 'JP'}


var parser = require('xml2json');
var sampleCOSD='/Users/mikeellinas/Desktop/PEACH/PEACH-anonymiser/nodeTest/sampleCOSD.xml';
var sampleCWT='/Users/mikeellinas/Desktop/NCWTMDSNCWTMDS_XMLExample.xml';

fs.readFile( sampleCOSD, function(err, data) {
    var json = parser.toJson(data);
    console.log("to json ->", json);
    jsonfile.writeFile(file, json, function (err) {
  		console.error(err)
	})
 });


 
