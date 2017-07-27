const csvContentType = 'text/csv';

export const downloadCSVFile = (filename, content) => {
  let a = document.createElement('a');
  let blob = new Blob([content], {
    'type': csvContentType,
  });
  a.href = window.URL.createObjectURL(blob);
  a.download = filename;
  a.click();
};
