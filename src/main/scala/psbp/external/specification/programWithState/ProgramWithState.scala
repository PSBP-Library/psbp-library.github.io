package psbp.external.specification.programWithState

import psbp.external.specification.program.Program

import psbp.external.specification.program.state.State

import psbp.external.specification.functional.`z>-->u`

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

