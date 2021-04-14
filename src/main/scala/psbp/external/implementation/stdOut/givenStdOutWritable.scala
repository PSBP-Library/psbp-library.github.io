package psbp.external.implementation.stdOut

import psbp.external.specification.types.&&

import psbp.external.specification.program.writing.Writable

given givenStdOutWritable: Writable[StdOut] with

  // defined

  def nothing: StdOut = 
    // StdOut(effect = { println(">>> nothing") ; Predef.identity })
    // println(">>> nothing")
    StdOut(effect = Predef.identity)

  def append: (StdOut && StdOut) => StdOut =
    case (StdOut(firstEffect), StdOut(secondEffect)) =>
      // StdOut({ println(">>> firstEffect") ; firstEffect } andThen { println(">>> secodEffect") ; secondEffect })
      // println(">>> append")
      StdOut(firstEffect andThen secondEffect)