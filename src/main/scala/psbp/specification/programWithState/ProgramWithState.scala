package psbp.specification.programWithState

import psbp.specification.program.Program

import psbp.specification.program.`z>-->u`

import psbp.specification.state.State

trait ProgramWithState[S, >-->[- _, + _]] extends Program[>-->] with State[S, >-->]:

  private implicit val program: Program[>-->] = this

  // defined

  def readState[Z]: Z >--> S =
    `z>-->u` >--> `u>-->s`  

  def writeState: S >--> Unit =
    `s>-->u` 
    
  def modifyStateWith[Z]: (S >--> S) => (Z >--> Unit) =
    `s>-->s` =>
      readState >--> `s>-->s` >--> writeState
  
  def readStateModifiedWith[Z]: (S >--> S) => (Z >--> S) =
    `s>-->s` =>
      modifyStateWith(`s>-->s`) >--> readState

// given programWithState[S: [S] =>> State[S, >-->], >-->[- _, + _]: Program]: ProgramWithState[S, >-->] with
 
//   private val program: Program[>-->] = summon[Program[>-->]]

//   private val state: State[S, >-->] = summon[State[S, >-->]]

//   export program.toProgram
//   export program.andThen
//   export program.construct
//   export program.conditionally

//   export state.`u>-->s`
//   export state.`s>-->u`
