package psbp.internalImplementation.programWithParallel

import psbp.specification.program.Program

import psbp.specification.program.parallel.Parallel

import psbp.specification.programWithParallel.ProgramWithParallel

given programWithParallel[>-->[- _, + _]: Program: Parallel]: ProgramWithParallel[>-->] with
 
  private val program: Program[>-->] = summon[Program[>-->]]

  private val parallel: Parallel[>-->] = summon[Parallel[>-->]]

  export program.toProgram
  export program.andThen
  export program.construct
  export program.conditionally

  export parallel.parallel
