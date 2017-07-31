const crypto = require('crypto');
const encoding = 'hex';
const algorithmHash = 'sha256';

export const hash256Information = (target) => {
  let hash = crypto.createHash(algorithmHash);
  hash.update(target);
  return hash.digest(encoding);
};
