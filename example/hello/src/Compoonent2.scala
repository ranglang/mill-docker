
package com.x.component

import GameFilterPage._
import korolev._
import korolev.state.javaSerialization._
import scala.concurrent.ExecutionContext.Implicits.global


import scala.concurrent.Future

import scala.concurrent.{ExecutionContext, Future}

object GameFilterPage {
  case class CreateSymbolState(brandIds: List[Long] =List.empty[Long], targets:List[String]=List.empty[String], olds: List[Long]=List.empty[Long])

  trait Evt  
  case class GoHome() extends Evt
  case class ReqSetFilter(targets:List[String]=List.empty[String], brands: List[Long]=List.empty[Long], olds: List[Long]=List.empty[Long]) extends Evt
}



object  GameFilterPageComponent {

  import _root_.korolev._
  import _root_.korolev.state.javaSerialization._


  val yeaers:List[Long] = List(3, 4, 5, 6, 8, 10)

  val targers = List(
    "a",
    "b",
    "c",
  )

  def ComponentAsFunction(): Component[Future, GameFilterPage.CreateSymbolState, String, GameFilterPage.Evt] =
    Component[Future, GameFilterPage.CreateSymbolState, String, GameFilterPage.Evt](GameFilterPage.CreateSymbolState()) { (context, parameters1, state) => {

      import context._
      import levsha.dsl._
      import html._

      div(
        clazz:="flex flex-col h-full",
        div(
          clazz:="flex flex-auto flex-col h-full overflow-y-auto",
          div(
            div(
            clazz:="items-center flex color-black bg-gray-100 py-4 pl-2",
              "states"
            ),
            div(
              clazz:="flex flex-row flex-wrap p-4",
              targers.map(tem => div(
                {
                  span(
                    event("click")(a => {
                      a.transition {
                        case state =>
                          if (state.targets.contains(tem)) {
                            state.copy(targets = state.targets.filterNot(r => r == tem))
                          } else {
                            state.copy(targets = state.targets.::(tem))
                          }
                      }
                    }),
                    div(color@=s"${if (state.targets.contains(tem)) "blue"else "red"}",
                        tem.toString()
                    ))
                }
              ))


            )
          ),

        div(
          clazz:="py-3 flex-none flex w-full justify-around flex-row w-full space-around bg-color-grey-100 border border-t ",
                  div(
                    clazz:="border-solid hover:opacity-60 px-10 py-2 flex items-center border  border-solid border-black cursor-pointer",
                    event("click")(a => {
                      a.publish(GameFilterPage.GoHome());
                    }),
                  "Home"),

                  div(
                    clazz:="border-solid hover:opacity-60 px-10 text-white py-2 flex items-center border cursor-pointer border-solid border-white bg-red-600 ",
                    event("click")(a => {
                      a.publish(ReqSetFilter(brands = state.brandIds, olds = state.olds, targets = state.targets))
                    }),
                    "Continue")
                )
      )
      )
    }
  }
}
