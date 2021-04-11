package examples.implementation.active.programWithState.effectful

import psbp.external.specification.program.state.Initial

import psbp.external.specification.programWithState.givenProgramWithState

import psbp.external.implementation.stateActive.{ 
  givenStateActiveProgram
  , givenStateActiveState
  , givenStateActiveMaterialization
}

import examples.specification.programWithState.Seed
  
given initialSeedState: Initial[Seed] = 
  new { 
    override val s = 1L 
  }

import examples.specification.programWithState.effectful.materializedMainTwoRandoms
  
import examples.implementation.givenUnit

@main def twoRandoms(args: String*): Unit =
  materializedMainTwoRandoms
