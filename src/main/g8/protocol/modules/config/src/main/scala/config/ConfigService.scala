package $package$
package config

import cats.effect.Effect
import cats.syntax.either._
import pureconfig.{ConfigReader, Derivation}
import fs2.Stream

trait ConfigService[F[_]] {

  def serviceConfig[Config](implicit reader: Derivation[ConfigReader[Config]]): Stream[F, Config]

}

object ConfigService {
  def apply[F[_]: Effect]: ConfigService[F] = new ConfigService[F] {

    override def serviceConfig[Config](
        implicit reader: Derivation[ConfigReader[Config]]): Stream[F, Config] =
      for {
        config <- Stream.eval(
          Effect[F].fromEither(
            pureconfig
              .loadConfig[Config]
              .leftMap(e => new IllegalStateException(s"Error loading configuration: \$e"))))
      } yield config

  }
}
