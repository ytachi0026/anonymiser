const gulp = require('gulp');
const webpack = require('gulp-webpack');

/* Copy dependencies for web development*/
gulp.task('dependencies', () => {
  gulp.src('node_modules/bootstrap/dist/css/bootstrap.min.css')
    .pipe(gulp.dest('./static/css'));
  gulp.src('node_modules/bootstrap/dist/js/bootstrap.min.js')
    .pipe(gulp.dest('./static/js'));
  gulp.src('node_modules/jquery/dist/jquery.min.js')
    .pipe(gulp.dest('./static/js'));
});

gulp.task('build', ['dependencies'], () => {
  return gulp.src('src/entry.js')
    .pipe(webpack(require('./webpack.config.js')))
    .pipe(gulp.dest('dist/'));
});
