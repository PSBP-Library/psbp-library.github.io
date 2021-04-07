package examples.specification.program

import psbp.external.specification.program.Program

import psbp.external.specification.functional.{ 
  `(z&&y)>-->z` => argument
}

import examples.specification.functional.{ 
  isZero
  , one
  , subtractOne
  , multiply 
}

import examples.specification.program.optimize

def optimizedFactorial[
  >-->[- _, + _]: Program
]: BigInt >--> BigInt =

  val program: Program[>-->] = 
    summon[Program[>-->]]
  import program.{ `z>-->z` => accumulator } 

  optimize(
    accumulatorInitializer = one, 
    argumentPredicate = isZero, 
    updater = (argument >--> subtractOne) && multiply, 
    resultExtractor = accumulator
  )

  