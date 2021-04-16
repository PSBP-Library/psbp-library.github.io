package psbp.external.specification.programWithReading

trait Producer[Z, >-->[- _, + _]]:

  // declared

  private[psbp] def produce: Unit >--> Z

  // defined

  def `u>-->z`: Unit >--> Z =
    produce