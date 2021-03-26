package examples.specification.program
  
import psbp.external.specifcation.types.&&

import psbp.external.specifcation.program.Program

import psbp.external.specifcation.functional.{
  `(z&&y)>-->z` => argument
  , `(z&&y)>-->y` => accumulator
  }

def optimize[
  >-->[- _, + _]: Program
  , A
  , Z
  , Y
  ](
    accumulatorInitializer: Z >--> A,
    argumentPredicate: Z >--> Boolean,
    updater: (Z && A) >--> (Z && A),
    resultExtractor: A >--> Y
    ): Z >--> Y =

  val program: Program[>-->] = 
    summon[Program[>-->]]
  import program.{ 
    Let
    , If
    }

  lazy val recursiveAccumulatorUpdater: (Z && A) >--> A =
    If(argument >--> argumentPredicate) {
      accumulator
    } Else {
      updater >--> recursiveAccumulatorUpdater
    }
    
  Let {
    accumulatorInitializer
  } In {
    recursiveAccumulatorUpdater >--> resultExtractor
  }