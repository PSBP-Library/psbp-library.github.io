package psbp.external.specification.programWithState

import psbp.external.specification.program.Program

import psbp.external.specification.program.state.State

given givenProgramWithState[
  S, 
  >-->[- _, + _]: Program
                : [>-->[- _, + _]] =>> State[S, >-->]
]: ProgramWithState[S, >-->] with
 
  private val program: Program[>-->] = 
    summon[Program[>-->]]

  export program.identity
  export program.toProgram
  export program.andThen
  export program.construct
  export program.conditionally

  private val state: State[S, >-->] = 
    summon[State[S, >-->]]

  export state.readS
  export state.writeS