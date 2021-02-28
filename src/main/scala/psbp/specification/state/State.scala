package psbp.specification.state

trait State[S, >-->[- _, + _]]:

  // declared

  def `u>-->s`: Unit >--> S

  def `s>-->u`: S >--> Unit

trait Initial[S]:
 
  // declared

  val s: S
