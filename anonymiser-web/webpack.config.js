const path = require('path');
const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');

console.log(path.resolve(__dirname, 'dist/js'));
module.exports = {
  entry: './src/app.js',
  devtool: 'inline-source-map',
  output: {
    filename: 'bundle.js',
    path: path.resolve(__dirname, 'dist/js'),
    libraryTarget: 'var',
    library: 'EntryPoint',
  },
  module: {
    loaders: [{
      test: /\.js$/,
      exclude: /(node_modules|bower_components)/,
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
  plugins: [
      new webpack.HotModuleReplacementPlugin(),
    ],
};
