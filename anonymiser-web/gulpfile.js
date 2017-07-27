const gulp = require('gulp');
const webpack = require('gulp-webpack');
const webpackDevServer = require('webpack-dev-server');

/* Copy dependencies for web development*/
gulp.task('dev', () => {
  gulp.src('node_modules/bootstrap/dist/css/bootstrap.min.css')
    .pipe(gulp.dest('./static/css'));
  gulp.src('node_modules/bootstrap/dist/js/bootstrap.min.js')
    .pipe(gulp.dest('./static/js'));
  gulp.src('node_modules/jquery/dist/jquery.min.js')
    .pipe(gulp.dest('./static/js'));
});

gulp.task('dependencies', () => {
  gulp.src('node_modules/bootstrap/dist/css/bootstrap.min.css')
    .pipe(gulp.dest('./dist/css'));
  gulp.src('./static/css/styles.css')
    .pipe(gulp.dest('./dist/css'));
  gulp.src('./static/css/ie10-viewport-bug-workaround.css ')
    .pipe(gulp.dest('./dist/css'));
  gulp.src('node_modules/jquery/dist/jquery.min.js')
    .pipe(gulp.dest('./dist/js'));
  gulp.src('node_modules/bootstrap/dist/js/bootstrap.min.js')
    .pipe(gulp.dest('./dist/js'));
  gulp.src('./static/images/peach-logo.png')
    .pipe(gulp.dest('./dist/images'));
  gulp.src('./index.html')
    .pipe(gulp.dest('./dist'));
});

gulp.task('build', ['dependencies'], () => {
  console.log('Building the project.');
  return gulp.src('src/app.js')
    .pipe(webpack(require('./webpack.config.js')))
    .pipe(gulp.dest('dist/js'));
});
