run the following command to execute the tests 

mvn clean test

after maven execution, the test report will be generated within

\target\surefire-reports\

The test suite structured using Page Object Model pattern

TODO
All the input/ output files should be read from directories 
move hardcoded values - browser type, url, directories/file name to configuration files

The site stopped working as I used many times during the development. So, I couldn't proceed with checking random mileage details.
further refactor required in fileutil by reusing the file reader code.