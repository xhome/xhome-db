install:package
	mvn install -Dmaven.test.skip=true
package:
	mvn package -Dmaven.test.skip=true
clean:
	mvn clean
deploy:package
	mvn deploy -Dmaven.test.skip=true
