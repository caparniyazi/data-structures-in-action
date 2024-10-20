# Data Structures in Action REST API & Implementation
This repository contains solutions to some problems related to data structures or ADTs.
# Contact
<mailto:capar.niyazi.tr@gmail.com>
# To build the project
mvn clean install

# To run the spring boot project
mvn spring-boot:run

# Usage
Once the application is started you can use an HTTP client, for example Postman, to send
REST requests.

1. To calculate a postfix expression:
   POST http://localhost:8080/api/v1/calculate
<p/>
<pre>
{
    "expression": "20 5 /"
}
</pre>

2. To display hints for minefield:
   POST http://localhost:8080/api/v1/show-hints
<p/>
<pre>
{
    "square": [
        "*...",
        "....",
        ".*..",
        "...."
    ]
}
</pre>