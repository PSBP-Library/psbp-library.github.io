package examples.specification.program.effectful

import psbp.external.specification.program.{
  Program
  , toMainProgram
}

import examples.specification.program.factorial

def mainFactorial[
  >-->[- _, + _]: Program
]: Unit >--> Unit =
  toMainProgram(
    produce = intProducer
    , program = factorial
    , consume = factorialConsumer
  )

// package examples.specification.program.effectful

// import psbp.external.specification.program.Program

// import examples.specification.program.factorial

// def mainFactorial[
//   >-->[- _, + _]: Program
// ]: Unit >--> Unit =
//   factorial toMainWith (
//     producer = intProducer,
//     consumer = factorialConsumer
//   )
