package $package$.client.app

import cats.effect._
import $package$.common.SeedConfig
import $package$.config.ConfigService
import fs2.Stream
import fs2.StreamApp
import io.chrisdavenport.log4cats.Logger
import io.chrisdavenport.log4cats.slf4j.Slf4jLogger
import monix.execution.Scheduler

abstract class ClientBoot[F[_]: Effect] extends StreamApp[F] {

  implicit val S: Scheduler = monix.execution.Scheduler.Implicits.global

  implicit val TM: Timer[F] = Timer.derive[F](Effect[F], IO.timer(S))

  override def stream(args: List[String], requestShutdown: F[Unit]): Stream[F, StreamApp.ExitCode] =
    for {
      config   <- ConfigService[F].serviceConfig[SeedConfig]
      logger   <- Stream.eval(Slf4jLogger.fromName[F](config.name))
      exitCode <- serverStream(config)(logger)
    } yield exitCode

  def serverStream(config: SeedConfig)(implicit L: Logger[F]): Stream[F, StreamApp.ExitCode]
}
