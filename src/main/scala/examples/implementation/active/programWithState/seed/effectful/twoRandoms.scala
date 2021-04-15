package examples.implementation.active.programWithState.seed.effectful

import psbp.external.specification.programWithState.givenProgramWithState

import psbp.external.implementation.stateActive.{ 
  givenStateActiveProgram
  , givenStateActiveState
}

import examples.implementation.active.programWithState.seed.givenSeedStateActiveMaterialization

import examples.specification.programWithState.seed.effectful.materializedMainTwoRandoms

import examples.implementation.givenUnit

@main def twoRandoms(args: String*): Unit =
  materializedMainTwoRandoms
