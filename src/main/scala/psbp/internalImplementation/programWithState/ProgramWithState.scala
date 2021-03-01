package psbp.internalImplementation.programWithState

import psbp.specification.program.Program

import psbp.specification.state.State

import psbp.specification.programWithState.ProgramWithState

given programWithState[S: [S] =>> State[S, >-->], >-->[- _, + _]: Program]: ProgramWithState[S, >-->] with
 
  private val program: Program[>-->] = summon[Program[>-->]]

  private val state: State[S, >-->] = summon[State[S, >-->]]

  export program.toProgram
  export program.andThen
  export program.construct
  export program.conditionally

  export state.`u>-->s`
  export state.`s>-->u`