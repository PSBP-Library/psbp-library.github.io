package psbp.external.specification.program

trait Identity[>-->[- _, + _]]:

  // declared

  def identity[Z]: Z >--> Z
