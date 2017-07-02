/**
 * Gruntfile for minify custom javascript-files. 
 * (You may need to run 'npm install' before grunt.
 * 
 */
module.exports = function(grunt) {
  grunt.initConfig({
    uglify: {
      dist: {
        files: {
          'src/patch/tomcat/webapps/ROOT/html/js/aui/aui-form-validator/aui-form-validator-min.js': ['src/patch/tomcat/webapps/ROOT/html/js/aui/aui-form-validator/aui-form-validator.js']
        }
      }
    }
  });

  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.registerTask('default', ['uglify']);
};