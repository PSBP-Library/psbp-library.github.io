package psbp.external.specification.program.state

trait Initial[S]:
 
  // declared

  val s: S
  
trait State[S, >-->[- _, + _]]:

  // declared

  private[psbp] def readS: Unit >--> S

  private[psbp] def writeS: S >--> Unit  

  // defined

  def `u>-->s`: Unit >--> S =
    readS

  def `s>-->u`: S >--> Unit =
    writeS    

