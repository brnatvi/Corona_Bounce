[main]: https://gaufre.informatique.univ-paris-diderot.fr/raveneau/corona-bounce
[coverage-shield]: https://img.shields.io/badge/Coverage-35%25-yellow
[comment-shield]: https://img.shields.io/badge/Javadoc-100%25-brightgreen
[ ![coverage-shield][] ][main]
[ ![comment-shield][] ][main]


# **Overview**

Corona Bounce is an epidemic simulation tool with a GUI interface.

It provides possibility to compare two populations during un epidemic.
It is also possible to variate population’s and disease’s parameters and apply four government strategies.

# **How to use**

## Compile (to do in code/)
```
mvn compile
```

## Run (jar file may change version)
```
java -jar code/cb1.0.8.jar
```

## Re-Run with Maven (to do in code/)
```
./run.sh
```

## Open JavaDoc
Launch code/docs/javadoc/apidocs/index.html on the web browser.

# **Maintainers:**

* Zahra Alliche
* Natalia Bragina
* Anna Golikova
* Kenza Sahi
* Emilien Raveneau Grisard


# **Features**

It's possible to modify follows parameters:
* Size of population
* Duration of incubation
* Duration of healing
* Duration of immunity
* Radius of contamination
* Number of boundaries
* Boundaries closing speed 


![](code/target/Project_1_resize.mp4)

Five scenarios are available to imitate government strategies:

* «Soft Lockdown» - all people can move but in limited area
* «Boundaries» - closure of borders for travel 
* «Strict Lockdown» - only police, emergency, medicals, delivery etc can move
* «Soft Lockdown + Boundaries»
* «No scenario»

![](code/target/Project_2_resize.mp4)
