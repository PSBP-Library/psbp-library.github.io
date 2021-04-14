package examples.specification.program.effectful

import psbp.external.specification.program.{
  Program
  , toMain
}

import examples.specification.program.factorial

def mainFactorial[
  >-->[- _, + _]: Program
]: Unit >--> Unit =
  toMain(
    producer = intProducer
    , program = factorial
    , consumer = factorialConsumer
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
