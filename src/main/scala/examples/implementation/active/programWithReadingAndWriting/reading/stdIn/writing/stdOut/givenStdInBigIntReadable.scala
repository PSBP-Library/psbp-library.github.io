package examples.implementation.active.programWithReadingAndWriting.reading.stdIn.writing.stdOut

import psbp.external.specification.program.reading.Readable

import psbp.external.implementation.stdIn.StdIn

given givenStdInBigIntReadable: Readable[StdIn[BigInt]] with

  private type R = StdIn[BigInt]

  override val r: R = 
    println("Please type an integer")
    StdIn(BigInt(scala.io.StdIn.readInt))   



