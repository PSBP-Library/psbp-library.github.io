package exercises.external.specification.function

def genericFunctionOfType_Z2Z[Z]: Z => Z =
  z =>
    println("I am a generic function of type Z => Z")
    z

@main def mainGenericFunctionOfType_Z2Z(args: String*): Unit =
  genericFunctionOfType_Z2Z[BigInt](1234567890)
