package examples.specification.program

import psbp.specification.program.Program

import psbp.specification.program.{ `(z&&y)>-->z` => argument, `z>-->z` => accumulator, optimizeWith }

def optimizedFactorial[>-->[- _, + _]: Program]: BigInt >--> BigInt =
  optimizeWith(
    accumulatorInitializer = one, 
    argumentPredicate = isZero, 
    updater = (argument >--> subtractOne) && multiply, 
    resultExtractor = accumulator
  )

  