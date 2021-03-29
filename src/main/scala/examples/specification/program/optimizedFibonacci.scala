package examples.specification.program

import psbp.external.specification.program.Program

import psbp.external.specification.functional.{ 
  `(z&&y)>-->z` => firstAccumulator
  , `(z&&y)>-->y` => secondAccumulator
  }

import examples.specification.functional.{ 
  isZero
  , zero
  , isOne
  , one
  , subtractOne
  , subtractTwo
  , add 
  }

import examples.specification.program.optimize

def optimizedFibonacci[
  >-->[- _, + _]: Program
  ]: BigInt >--> BigInt =
  optimize(
    accumulatorInitializer = one && one, 
    argumentPredicate = isZero, 
    updater = subtractOne &&& (secondAccumulator && add), 
    resultExtractor = firstAccumulator
  )
