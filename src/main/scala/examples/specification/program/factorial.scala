package examples.specification.program

import psbp.external.specifcation.program.Program

import examples.specification.functional.{ 
  isZero
  , one
  , subtractOne
  , multiply 
  }

def factorial[
  >-->[- _, + _]: Program
  ]: BigInt >--> BigInt =

  val program: Program[>-->] = 
    summon[Program[>-->]]
  import program.{
    Let
    , If 
    }

  If(isZero) {
    one
  } Else {
    Let {
      subtractOne >--> factorial
    } In {
      multiply
    }
  }

  