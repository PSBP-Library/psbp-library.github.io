package psbp.external.specification.programWithState

import psbp.external.specification.program.Program

import psbp.external.specification.program.state.State

given programWithState[
  S, 
  >-->[- _, + _]: Program: [>-->[- _, + _]] =>> State[S, >-->]]: ProgramWithState[S, >-->] with
 
  private val program: Program[>-->] = summon[Program[>-->]]

  private val state: State[S, >-->] = summon[State[S, >-->]]

  export program.toProgram
  export program.andThen
  export program.construct
  export program.conditionally

  export state.`u>-->s`
  export state.`s>-->u`