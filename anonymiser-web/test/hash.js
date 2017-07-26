const crypto = require('crypto');
const hash = crypto.createHash('sha256');
const encoding = 'hex';

export const hashInformation = (target) => {
  hash.update(target);
  return hash.digest(encoding);
};
