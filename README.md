VideoWorld - Sample App
======
This is a simple self running web application which may be used as a base to try out TDD, Refactoring etc on a live web app.

The app is self running, and self-hosted, without any XML config noise.

#####Running The App
***

* A "clean" run ->
        ```mvn clean spring-boot:run```

* An incremental run ->
```
mvn spring-boot:run
```

Once up, just point the browser to : http://localhost:8080/login

#####Requirements
***

* Java 1.6 (1.7 recommended)
* Maven 3.0.4 (3.2.x recommended)

#####Next Steps
***

* Look at some of the representative tests and add some of the missing tests (which are missing on purpose)
* Build some new features - trying out TDD etc along the way!


