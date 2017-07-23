const path = require('path');
const webpack = require('webpack');

module.exports = {
  entry: './src/app.js',
  output: {
    filename: 'bundle.js',
    path: path.resolve(__dirname, 'dist/js'),
    libraryTarget: 'var',
    library: 'EntryPoint',
  },
  devServer: {
    contentBase: './dist',
    port: 3000,
  },
  module: {
    loaders: [{
      test: /\.js$/,
      loader: 'babel-loader',
      query: {
        presets: ['es2015'],
      },
    },
  ],
  },
  stats: {
    colors: true,
  },
  devtool: 'source-map',
};
