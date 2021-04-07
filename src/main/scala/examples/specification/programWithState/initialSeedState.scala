package examples.specification.programWithState

import psbp.external.specification.program.state.Initial
  
given initialSeedState: Initial[Seed] = 
  new { 
    override val s = 1L 
  }