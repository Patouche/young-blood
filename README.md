Example d'application avec Thymeleaf !
======================================

[![Build Status](https://travis-ci.org/Patouche/young-blood.svg?branch=master)](https://travis-ci.org/Patouche/young-blood)

Le but de cette application est de présenter des slides réaliser en markdown et de les afficher avec Thymeleaf et Reveal.js

## Run the project


```bash
cd project-slides
mvn clean install
java -jar -Dslides.path=$(readlink -f ../slides/) target/thymeleaf-slides-1.0-SNAPSHOT.war
```

The presentation will run on the port 8080 :

* [Demo](http://localhost:8080/)
* [Slides](http://localhost:8080/slides)

To see the HTML templates in a browser, open the following url :

* `./project-slides/src/main/webapp/WEB-INF/thymeleaf/demo-detail-th.html`
* `./project-slides/src/main/webapp/WEB-INF/thymeleaf/demo-th.html`
* `./project-slides/src/main/webapp/WEB-INF/thymeleaf/reveal-th.html`


## Demonstration

* Introduction to Thymeleaf
* How to write a Thymeleaf Dialect 
* How to tests a Dialect
* How to tests a Thymeleaf template

## License

[The MIT License (MIT)](./LICENSE)
