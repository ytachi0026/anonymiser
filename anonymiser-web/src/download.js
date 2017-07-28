const csvContentType = 'text/csv';

export const downloadCSVFile = (filename, content) => {
  let a = document.createElement('a');

  let blob = new Blob([content], {
    'type': csvContentType,
  });
  a.setAttribute('href', window.URL.createObjectURL(blob));
  a.setAttribute('download', filename);
  document.body.appendChild(a);
  a.click();
  document.body.removeChild(a);
};
