package examples.specification.program

import psbp.specification.program.Program

import psbp.specification.functional.{ `(z&&y)>-->z` => firstAccumulator, `(z&&y)>-->y` => secondAccumulator }

import psbp.specification.program.optimize

import examples.specification.functional.{ isZero, zero, isOne, one, subtractOne, subtractTwo, add }

def optimizedFibonacci[>-->[- _, + _]: Program]: BigInt >--> BigInt =
  optimize(
    accumulatorInitializer = one && one, 
    argumentPredicate = isZero, 
    updater = subtractOne &&& (secondAccumulator && add), 
    resultExtractor = firstAccumulator
  )
