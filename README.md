# Freestyle-rpc-seed

This is a giter8 template that includes a couple of microservices:

- The RPC server that exposes a very simple service `PeopleService[F]` whose protocol has been expressed in Avro Schema Definition, and a very basic interpreter `PeopleServiceHandler`.

- The RPC client that consumes the mentioned service.

## Usage

1. Create a new project:

```bash
sbt new frees-io/freestyle-rpc-seed.g8
```

or

```bash
g8 frees-io/freestyle-rpc-seed.g8
```

if you have the [g8 plugin](http://www.foundweekends.org/giter8/setup.html) installed.

2. Fill the required information (you can see an example below):

```bash

...

name [Project Name]: my-new-rpc-microservice
projectDescription [Project Description]: A new RPC microservice with Freestyle-RPC
project [project-name]: my-new-rpc-microservice
package [org.mycompany]: org.mycompany
freestyleRPC [0.14.0]: 0.14.0

...

Template applied in ./my-new-rpc-microservice
```
