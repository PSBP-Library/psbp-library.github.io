package examples.implementation.active.programWithState.effectful

import psbp.external.implementation.stateActive.{
  stateActiveProgram
  , stateActiveState
  , stateActiveMaterialization
}

import psbp.external.specification.programWithState.programWithState

import examples.specification.programWithState.initialSeedState

import examples.specification.programWithState.effectful.mainTwoRandoms

@main def twoRandoms(args: String*): Unit =
  mainTwoRandoms materialized ()