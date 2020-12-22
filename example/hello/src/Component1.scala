
package com.x.ss.component

import korolev._
import korolev.state.javaSerialization._

import scala.concurrent.Future

case class CreateGameComponentState11(name: String = "")

case class CreateGameComponentParameters11(
  p: String = "",
  list: List[String] = List.empty[String],
  nextId: Option[Long] = Option.empty[Long]
)

trait DiscoverEvent

case class GoToGamge(id: Long) extends DiscoverEvent

case class GoToEvent(n: Int) extends DiscoverEvent

case class AddMoreGame(n: Long) extends DiscoverEvent

object DiscoverPageComponent
    extends Component[
      Future,
      CreateGameComponentState11,
      CreateGameComponentParameters11,
      DiscoverEvent
    ](CreateGameComponentState11()) {

  import context._
  import levsha.dsl._
  import html._

  def render(
    parameters: CreateGameComponentParameters11,
    state: CreateGameComponentState11
  ): Node = {
    div(
      clazz := s"flex flex-col h-full",
      div(parameters.list.toString),
      div(
        clazz := "page__bd flex-grow-0 h-16",
        div(
          clazz := "weui-tab",
          div(
            clazz := "weui-tab__panel"
          ),
          div(
            clazz := "weui-tabbar",
            div(
              clazz := "weui-tabbar__item weui-bar__item_on hover:bg-gray-100",
              div(
                style := "display: inline-block; position: relative;cursor: pointer;",
                event("click") { a =>
                  {
                    a.publish(GoToEvent(1))
                  }
                },
                a(
                  i(
                    clazz := "iconfont iconshouye text-2xl cusor-pointer",
                    style := "font-size:24px"
                  ),
                  clazz := "weui-tabbar__icon"
                )
              ),
              p(
                clazz := "weui-tabbar__label",
                "Go To Home"
              )
            ),
            div(
              event("click") { a =>
                {
                  a.publish(GoToEvent(2))
                }
              },
              clazz := "weui-tabbar__item  hover:bg-gray-100",
              a(
                style := "cursor: pointer;",
                i(clazz := "iconfont iconfaxian text-2xl cusor-pointer", style := "font-size:24px"),
                alt := "",
                clazz := "weui-tabbar__icon"
              ),
              p(
                style := "cursor: pointer;",
                clazz := "weui-tabbar__label",
                "Go To Filter"
              )
            )
          )
        )
      )
    )
  }
}
