package examples.specification.program

import psbp.external.specification.program.Program

import examples.specification.functional.{ 
  isZero
  , zero
  , isOne
  , one
  , subtractOne
  , subtractTwo
  , add
}

def fibonacci
  [>-->[- _, + _]: Program
]: BigInt >--> BigInt =

  val program: Program[>-->] = 
    summon[Program[>-->]]
  import program.{
    If
  }

  If(isZero) {
    zero
  } Else {
    If(isOne) {
      one
    } Else {
      (subtractOne && subtractTwo) >-->
        (fibonacci &&& fibonacci) >-->
        add
    }
  }

  