package psbp.external.specification.program.reading

trait Readable[R]:

  // declared

  val r: R

trait FromReadable[R: Readable, Z, >-->[- _, + _]]:

  // declared

  private[psbp] def `r>-->z`: R >--> Z

  // defined

  private[psbp] def converter: R >--> Z =
    `r>-->z`

trait Reading[R: Readable, >-->[- _, + _]]:

  // declared

  private[psbp] def readR: Unit >--> R

  // defined

  def `u>-->r`: Unit >--> R =
    readR

  def read: Unit >--> R =
    `u>-->r`
