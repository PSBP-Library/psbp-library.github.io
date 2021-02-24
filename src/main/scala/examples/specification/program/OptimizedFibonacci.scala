package examples.specification.program

import psbp.specification.program.Program

import psbp.specification.program.{ 
  `(z&&y)>-->z` => firstAccumulator, `(z&&y)>-->y` => secondAccumulator, optimizeWith 
  }

def optimizedFibonacci[>-->[- _, + _]: Program]: BigInt >--> BigInt =
  optimizeWith(
    accumulatorInitializer = one && one, 
    argumentPredicate = isZero, 
    updater = subtractOne &&& (secondAccumulator && add), 
    resultExtractor = firstAccumulator
  )
