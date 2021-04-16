package examples.implementation.active.programWithState.seed

import psbp.external.specification.program.state.Initial

import examples.specification.programWithState.seed.Seed
  
given givenInitialSeedState: Initial[Seed] = 
  new { 
    override val s = 1L 
  }
