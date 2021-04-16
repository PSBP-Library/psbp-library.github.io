package psbp.external.specification.programWithWriting

import psbp.external.specification.program.writing.{
  Writable
  , ToWritable
  , Writing
}

given givenToWritableConsumer [
  Y: [Y] =>> ToWritable[Y, W, >-->]
  , W: Writable
  , >-->[- _, + _]: [>-->[- _, + _]] =>> ProgramWithWriting[W, >-->]
]: Consumer[Y, >-->] with 

  private val toWritable: ToWritable[Y, W, >-->] = 
    summon[ToWritable[Y, W, >-->]]
  import toWritable.`y>-->w`
  
  private val writing: Writing[W, >-->] = 
    summon[Writing[W, >-->]]
  import writing.`w>-->u`

  override def consume: Y >--> Unit =
    `y>-->w` >--> `w>-->u`

      