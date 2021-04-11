package exercises.external.specification.function

def `z==>z`[Z]: Z => Z =
  z =>
    z

// @main def main(args: String*): Unit =
//   val argument = 12345
//   val result = `z==>z`(argument)
//   println(s"applying `z==>z` to the integer argument $argument yields result $result")

def `z=s=>z`[Z]: Z => Z =
  z =>
    println("side effect")
    z

// @main def main(args: String*): Unit =
//   `z=s=>z`(12345)

def hello: String => String =
  s =>
    s + "Hello"

def comma: String => String =
  s =>
    s + ","
    
def blank: String => String =
  s =>
    s + " "

def world: String => String =
  s =>
    s + "world"

def bang: String => String =
  s =>
    s + "!"

def nwln: String => String =
  s =>
    s + "\n"
 
def emptyStringProducer: Unit => String =
  _ =>
    ""

def stringConsumer: String => Unit =
  s =>
    println(s)

