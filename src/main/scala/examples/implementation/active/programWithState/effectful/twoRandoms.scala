package examples.implementation.active.programWithState.effectful

import psbp.external.specification.program.Program

import psbp.external.specification.program.state.State

import psbp.external.specification.program.state.Initial

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.stateActive.{
  given Program
  , given State
  , given Materialization
}

// given
import psbp.external.specification.programWithState.programWithState

import examples.specification.programWithState.effectful.mainTwoRandoms


import examples.specification.programWithState.Seed
  
given initialSeedState: Initial[Seed] = 
  new { 
    override val s = 1L 
  }

@main def twoRandoms(args: String*): Unit =
  mainTwoRandoms materialized ()