package psbp.external.specification.program.reading

trait FromReadable[R, Z, >-->[- _, + _]]:

  def converter: R >--> Z

trait Reading[R, >-->[- _, + _]]:

  def read: Unit >--> R
