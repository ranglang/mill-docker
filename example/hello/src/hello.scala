package example

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives
import korolev.Context
import scala.concurrent.ExecutionContext.Implicits.global
import korolev.akka.AkkaHttpServerConfig
import korolev.state.javaSerialization._

object hello extends App with Directives {
  println(s"Hello ${args.mkString(" ")}")

  implicit  val system:ActorSystem=akka.actor.ActorSystem("hello")

  val route2 = (new com.x.component.Koute()).service(AkkaHttpServerConfig())

  val route = withSizeLimit(95386390) {
    route2
  }

  Http().bindAndHandle(route, "0.0.0.0", 9205)
}
