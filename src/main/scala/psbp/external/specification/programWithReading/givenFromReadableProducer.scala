package psbp.external.specification.programWithReading

import psbp.external.specification.program.reading.{
  Readable
  , FromReadable
  , Reading
}

given givenFromReadableProducer [
  Z: [Z] =>> FromReadable[R, Z, >-->]
  , R: Readable
  , >-->[- _, + _]: [>-->[- _, + _]] =>> ProgramWithReading[R, >-->]
]: Producer[Z, >-->] with
    val fromReadable: FromReadable[R, Z, >-->] = 
      summon[FromReadable[R, Z, >-->]]
    import fromReadable.`r>-->z`
    
    val reading: Reading[R, >-->] = 
      summon[Reading[R, >-->]]
    import reading.`u>-->r`
  
    override def produce: Unit >--> Z =
    
      `u>-->r` >--> `r>-->z`


