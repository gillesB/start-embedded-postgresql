**Note:**

After writing this Maven plugin I found out that a similar plugin already exists.
https://gitlab.com/janecekpetr/embedded-postgresql-maven-plugin

It is more versatile and better.

# Start Embedded PostgreSQL Mojo

A simple [Maven](https://maven.apache.org/) Mojo which creates an embedded [PostgreSQL](https://www.postgresql.org/) database and performs a [Flyway](https://flywaydb.org/) migration on it.
To generate the embedded database [OpenTable Embedded PostgreSQL](https://github.com/opentable/otj-pg-embedded) is used.

The database can for example be used by [JOOQ](https://www.jooq.org/)'s [code generation](https://www.jooq.org/doc/3.11/manual/code-generation/codegen-configuration/).
This Mojo was developed to generate this code without using an actual PostgreSQL database.
The provided integration test performs such a code generation.

## Parameters

|Parameter | Default value | Description |
|-|-|-|
|`flywayDirectory`| `src/main/resources/db/migration` | The filesystem folder containing the Flyway migration files. Only SQL migrations supported.  |

## Created Properties

|Property | Value | Description |
|-|-|-|
| `pgHost`  | `localhost` |  The host of the PostgreSQL server.  |
| `pgPort`  | Random port |  The port of the PostgreSQL server.  |
| `pgDbName`  | Random name |  The name of the PostgreSQL database.  |
| `pgUser`  | `postgres` |  The user to connect to the PostgreSQL server.  |
| `pgPw`  | `postgres` |  The password to connect to the PostgreSQL server.  |

