install:
	mvn install -Dmaven.test.skip=true
package:
	mvn package -Dmaven.test.skip=true
clean:
	mvn clean
deploy:
	mvn javadoc:jar source:jar deploy -Dmaven.test.skip=true
