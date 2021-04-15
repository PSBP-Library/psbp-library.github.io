package psbp.external.implementation.stdOut

import psbp.external.specification.types.&&

import psbp.external.specification.program.writing.Writable

given givenStdOutWritable: Writable[StdOut] with

  // defined

  def emptyW: StdOut = 
    StdOut(effect = Predef.identity)

  def appendW: (StdOut && StdOut) => StdOut =
    case (StdOut(firstEffect), StdOut(secondEffect)) =>
      StdOut(firstEffect andThen secondEffect)