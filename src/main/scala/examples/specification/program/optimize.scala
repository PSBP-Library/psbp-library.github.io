package examples.specification.program
  
import psbp.external.specification.types.&&

import psbp.external.specification.program.Program

import psbp.external.specification.functional.{
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

  lazy val updateUntilArgumentPredicateHolds: (Z && A) >--> A =
    If(argument >--> argumentPredicate) {
      accumulator
    } Else {
      updater >--> updateUntilArgumentPredicateHolds
    }
    
  Let {
    accumulatorInitializer
  } In {
    updateUntilArgumentPredicateHolds >--> resultExtractor
  }