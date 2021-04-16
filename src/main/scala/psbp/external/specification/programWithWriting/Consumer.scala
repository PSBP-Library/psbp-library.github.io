package psbp.external.specification.programWithWriting

import psbp.external.specification.program.Program

trait Consumer[Y, >-->[- _, + _]]:

  // declared

  private[psbp] def consume: Y >--> Unit

  // defined

  def `y>-->u`: Y >--> Unit =
    consume