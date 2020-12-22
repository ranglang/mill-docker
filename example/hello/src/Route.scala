package com.x.component


  import akka.actor.ActorSystem
      import korolev._
  import korolev.akka._
  import korolev.server._
  import korolev.state.javaSerialization._
import scala.concurrent.Future

import korolev.Context

case class HappinessWebState(
  page: HappinessWebStateTrait,
  loading: Boolean = false
)

sealed trait HappinessWebStateTrait {
  val key: String;
}

object HappinessWebStateTrait {

  case class DiscoverGameList(
    key: String = "discover-game-list",
    list: List[String] = List.empty[String],
    nextId: Option[Long] = Option.empty[Long]
  ) extends HappinessWebStateTrait

 case class GameFiltersPages(
    key: String = "game-filters-page",
  ) extends HappinessWebStateTrait

}

object HappinessWebState {
  val globalContext = Context[Future, HappinessWebState, Any]
}

class Koute(implicit val system: ActorSystem ) {
   implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global
  import levsha.dsl._
  import levsha.dsl.html._
    import korolev._
  import korolev.akka._
  import korolev.server._
  import korolev.state.javaSerialization._
  import levsha.dsl._
    import HappinessWebState.globalContext._

    val root = Root

     val service = akkaHttpService {
    KorolevServiceConfig[Future, HappinessWebState, Any](
      stateLoader = StateLoader.default(HappinessWebState(HappinessWebStateTrait.DiscoverGameList())),
      router = Router(
        fromState = {
            case HappinessWebState(page: HappinessWebStateTrait.DiscoverGameList,_) => root/"list"
            case HappinessWebState(page: HappinessWebStateTrait.GameFiltersPages,_) => root/"filter"
        },
           toState = {
          case root/"list"  =>
            state => {
                Future.successful(HappinessWebState(HappinessWebStateTrait.DiscoverGameList()))
            }
          case root/"filter"  =>
            state => {
                Future.successful(HappinessWebState(HappinessWebStateTrait.GameFiltersPages()))
            }}),
          render = { 
            case HappinessWebState(page: HappinessWebStateTrait.DiscoverGameList, loading) =>
              body(  div(
com.x.ss.component.DiscoverPageComponent(com.x.ss.component.CreateGameComponentParameters11(list=page.list)){(access, event) => {
  println("event {}", event)
  event match {
    case com.x.ss.component.GoToEvent(1)=> {
       Future.successful(HappinessWebState(HappinessWebStateTrait.DiscoverGameList())).flatMap(r => access.transition{
         case _ => r
       })
    }
    case com.x.ss.component.GoToEvent(2)=> {
       Future.successful(HappinessWebState(HappinessWebStateTrait.GameFiltersPages())).flatMap(r => access.transition{
         case _ => r
       })
    }
    case _ =>  {
       Future.successful(HappinessWebState(HappinessWebStateTrait.GameFiltersPages())).flatMap(r => access.transition{
         case _ => r
       })
    }
  }
}
}))
            case HappinessWebState(page: HappinessWebStateTrait.GameFiltersPages, loading) =>
            body(
                GameFilterPageComponent.ComponentAsFunction()(""){(access, event) => {

                  event match {
                    case GameFilterPage.GoHome() => {

       Future.successful(HappinessWebState(HappinessWebStateTrait.DiscoverGameList(list=List("1","2")))).flatMap(r => access.transition{
         case _ => r
       })
                    }
                    case _ => {
                      Future.unit
                    }
                  }
                }}
            )
            case _ => div("ookk1")
    })
}
}