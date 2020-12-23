// -*- mode: scala -*-

// import $repo.`https://jitpack.io`

import mill._, scalalib._

// import $ivy.`io.github.vic::mill-docker:latest`
// import mill.docker._
  val korolevVersion = "0.17-M1"
  val akkaVersion="2.6.5"
val akkaHttpVersion="10.1.12"

object hello extends ScalaModule  {
  def scalaVersion = "2.13.2"
  def dockerTag = "hello:latest"

  override def finalMainClass = T("example.hello")

  override def ivyDeps =
    Agg(
    ivy"org.fomkin::korolev:$korolevVersion"  excludeOrg "com.typesafe.akka",
    ivy"org.fomkin::korolev-akka:$korolevVersion" excludeOrg "com.typesafe.akka",
  ivy"io.monix::monix-eval:3.3.0" ,
    ivy"io.monix::monix:3.3.0" ,
  ivy"org.fomkin::korolev-effect:$korolevVersion" excludeOrg "com.typesafe.akka",
    ivy"org.fomkin::korolev-monix:$korolevVersion" excludeOrg "com.typesafe.akka",
    ivy"org.fomkin::korolev-cats:$korolevVersion" excludeOrg "com.typesafe.akka",
  ivy"org.fomkin::korolev-slf4j:$korolevVersion" excludeOrg "com.typesafe.akka",
     ivy"com.typesafe.akka::akka-stream:$akkaVersion",
    ivy"com.typesafe.akka::akka-slf4j:$akkaVersion",
    ivy"com.typesafe.akka::akka-actor:$akkaVersion",
    ivy"com.typesafe.akka::akka-http:$akkaHttpVersion"
    )

}
