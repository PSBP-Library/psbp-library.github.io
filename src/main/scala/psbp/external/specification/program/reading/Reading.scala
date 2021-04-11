package psbp.external.specification.program.reading

trait Reading[R, >-->[- _, + _]]:

  // declared

  private[psbp] def `u>-->r`: Unit >--> R

  // defined

  def read: Unit >--> R =
    `u>-->r`

