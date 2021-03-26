package examples.specification.program

import psbp.external.specifcation.program.Program

import psbp.external.specifcation.functional.{ 
  `(z&&y)>-->z` => argument
  , `z>-->z` => accumulator 
  }

import examples.specification.functional.{ 
  isZero
  , one
  , subtractOne
  , multiply 
  }

import examples.specification.program.optimize

def optimizedFactorial[
  >-->[- _, + _]: Program]
  : BigInt >--> BigInt =
  optimize(
    accumulatorInitializer = one, 
    argumentPredicate = isZero, 
    updater = (argument >--> subtractOne) && multiply, 
    resultExtractor = accumulator
  )

  