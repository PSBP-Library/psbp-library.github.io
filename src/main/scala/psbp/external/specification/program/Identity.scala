package psbp.external.specification.program

trait Identity[>-->[- _, + _]]:

  // declared

  private[psbp] def identity[Z]: Z >--> Z

  // defined

  def `z>-->z`[Z]: Z >--> Z =
    identity

  def `y>-->y`[Y]: Y >--> Y =
    `z>-->z`[Y]

  def `x>-->x`[X]: X >--> X =
    `z>-->z`[X]      
  
  def `w>-->w`[W]: W >--> W =
    `z>-->z`[W]

  def `v>-->v`[V]: V >--> V =
    `z>-->z`[V]  

  // ...

  def `u>-->u`: Unit >--> Unit =
    `z>-->z`[Unit] 

  // ...  
