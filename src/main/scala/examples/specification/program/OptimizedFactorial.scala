package examples.specification.program

import psbp.specification.program.Program

import psbp.specification.functional.{ `(z&&y)>-->z` => argument, `z>-->z` => accumulator }

import psbp.specification.program.optimizeWith

import examples.specification.functional.{ isZero, one, subtractOne, multiply }

def optimizedFactorial[>-->[- _, + _]: Program]: BigInt >--> BigInt =
  optimizeWith(
    accumulatorInitializer = one, 
    argumentPredicate = isZero, 
    updater = (argument >--> subtractOne) && multiply, 
    resultExtractor = accumulator
  )

  