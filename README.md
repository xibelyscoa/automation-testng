#Automation Test

## Requirements

* Maven
* Java 11

## Install reporting tool

```

npm install -g allure-commandline --save-dev

```

## Execute test with maven

```
mvn clean verify
```

## Allure report

```
allure serve
```
## Export results

```
allure generate
```