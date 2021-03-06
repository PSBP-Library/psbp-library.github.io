package examples.implementation.active.programWithState.effectful

import psbp.specification.program.state.Initial

import psbp.implementation.programWithState

import psbp.implementation.stateActive.given

import examples.specification.programWithState.Seed

import examples.specification.programWithState.effectful.mainTwoRandoms

given initialSeedState: Initial[Seed] = 
  new { 
    override val s = 1L 
  }

@main def twoRandoms(args: String*): Unit =
  mainTwoRandoms materialized ()