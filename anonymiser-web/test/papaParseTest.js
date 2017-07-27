const papa = require('papaparse');
const path = require('path');

const csvFilePath = path.join(path.resolve(__dirname, 'sample'), 'cwt.csv');

papa.parse(csvFilePath, {
  header: true,
  complete: (results) => {
    console.log('Finished: ', results.data);
  },
});
