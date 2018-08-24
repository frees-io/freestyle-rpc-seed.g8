package $package$
package common

case class SeedConfig(name: String, host: String, port: Int)

case class Seed(config: SeedConfig)
