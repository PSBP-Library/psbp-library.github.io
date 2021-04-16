package examples.implementation.active.programWithReadingAndWriting.reading.stdIn.writing.stdOut

import psbp.external.specification.types.&&

import psbp.external.specification.program.toMain

import psbp.external.specification.program.Program

import psbp.external.specification.programWithReading.Producer

import psbp.external.specification.programWithWriting.Consumer

import psbp.external.implementation.stdIn.StdIn

import psbp.external.implementation.stdOut.StdOut

import examples.specification.program.factorial

// experiment
import psbp.external.specification.programWithReading.givenFromReadableProducer

import psbp.external.specification.programWithWriting.givenToWritableConsumer


def mainFactorial[
  >-->[- _, + _]: [>-->[- _, + _]] =>> Producer[BigInt, >-->]
                : Program 
                : [>-->[- _, + _]] =>> Consumer[BigInt && BigInt, >-->]
]: Unit >--> Unit =

  toMain(factorial)








