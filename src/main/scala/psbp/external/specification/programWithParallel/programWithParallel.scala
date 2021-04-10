package psbp.external.specification.programWithParallel

import psbp.external.specification.program.Program

import psbp.external.specification.program.parallel.Parallel

given programWithParallel[
  >-->[- _, + _]: Program
                : Parallel
]: ProgramWithParallel[>-->] with
 
  private val program: Program[>-->] = summon[Program[>-->]]

  export program.toProgram
  export program.andThen
  export program.construct
  export program.conditionally

  private val parallel: Parallel[>-->] = summon[Parallel[>-->]]

  export parallel.parallel
