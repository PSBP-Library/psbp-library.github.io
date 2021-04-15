package examples.implementation.active.programWithState.seed

import psbp.external.specification.materialization.Materialization

import psbp.external.implementation.stateActive.{
  `=>SA`
  , givenStateActiveMaterialization
}  

import examples.specification.programWithState.seed.Seed

given givenSeedStateActiveMaterialization: Materialization[
  `=>SA`[Seed]
  , Unit
  , Unit
] = givenStateActiveMaterialization[Seed]
