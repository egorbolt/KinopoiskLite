set JAVA_HOME="D:\Applications\Android\Android Studio\jre"
.\gradlew createDebugCoverageReport 
.\gradlew sonarqube -Dsonar.projectKey=KL -Dsonar.projectName="Kinopoisk Lite" -Dsonar.host.url=http://84.237.50.237:9000 -Dsonar.login=eb67dfe88f699ce908db2c43a7836d59d4504c24 -Dsonar.coverage.jacoco.xmlReportPaths=".\build\reports\coverage\debug\report.xml"