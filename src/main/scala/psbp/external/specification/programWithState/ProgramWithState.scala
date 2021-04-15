package psbp.external.specification.programWithState

import psbp.external.specification.functional.`z>-->u`

import psbp.external.specification.program.Program

import psbp.external.specification.program.state.State

trait ProgramWithState[S, >-->[- _, + _]] 
  extends Program[>-->] 
  with State[S, >-->]:

  private given program: Program[>-->] = this

  // defined

  def `z>-->s`[Z]: Z >--> S =
    `z>-->u` >--> `u>-->s`    

  def readState[Z]: Z >--> S =
    `z>-->s`

  def writeState: S >--> Unit =
    `s>-->u` 
    
  def modifyStateWith[Z]: (S >--> S) => (Z >--> Unit) =
    `s>-->s` =>
      `z>-->s` >--> `s>-->s` >--> `s>-->u`
  
  def readStateModifiedWith[Z]: (S >--> S) => (Z >--> S) =
    `s>-->s` =>
      modifyStateWith(`s>-->s`) >--> `z>-->s`

