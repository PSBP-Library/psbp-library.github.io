# Program Specification Based Programming

This document describes `PSBP`, a `Scala` library for *Program Specification Based Programming*.

```scala
//          ________    ________     __         _______
//         / ___   /\  / ______/\   / /\       / ___  /\
//        / /  /  / / / /\_____\/  / / /      / /  / / /
//       / /__/  / / / /_/____    / /_/__    / /__/ / /
//      / ______/ / /______  /\  / __   /\  / _____/ /   
//     / / _____\/  \____ / / / / /  / / / / / ____\/
//    / / /        ______/ / / / /__/ / / / / /  
//   /_/ /        /_______/ / /______/ / /_/ /
//   \_\/         \_______\/  \______\/  \_\/
//          
```

[source code](https://github.com/PSBP-Library/psbp-library.github.io)

The document describes both 

- the *definition* of the `PSBP` library,
- the *usage* of the `PSBP` library.

## Definition

The definition of the `PSBP` library separates *specifications* from *implementations*.

- `PSBP` specifications are defined as `trait`'s that define *typeclasses*. 
- `PSBP` implementations are defined as `given`'s that define types to belong to typeclasses. 

## Usage

The usage of the `PSBP` library is in terms of examples that are 

- *defined* in terms of members of specification `trait`'s
- *used* in terms of implementation `given`'s that are made available using *dependency injection by* `import`.

# Programs

The *specification part* of the `PSBP` library models *programming*.

Think about it as explaining to `Scala` what programming is all about.

Members of specification `trait`'s are referred to as *programming capabilities*.

A `val` that is defined in terms of programming capabilities is referred to as a *program*. 

A program has a *descriptive* nature, in fact it would be more appropriate to refer to it as a *program description*.

Compare this with the famous painting [Ceci n'est pas une pipe](https://en.wikipedia.org/wiki/The_Treachery_of_Images) of [René Magritte](https://en.wikipedia.org/wiki/Ren%C3%A9_Magritte).

The painting is not a *pipe*, it is a *pipe description*.

In a way a program is a generalization of a *function*.

A function of type `Z => Y` *transforms* an *argument* of type `Z` to a *result* of type `Y`.

A program of type `Z >--> Y` *describes* transforming an argument of type `Z` to a result of type `Y`.

A program may, or may not, *describe* performing side effects along the way.

A described side effect is referred to as an *effect*.

A *main program* is a program of type `Unit >--> Unit`.

Programs of type `Z >--> Y` are, somehow, combined with *producers* of type `Unit >--> Z` and *consumers* of type `(Z && Y) >--> Unit` to main programs.

Main programs, together with the producers, programs and consumers in terms of which they are defined, are *materialized* to functions.

*Materialized main program implementations*, typically, somehow behave as follows

- the *materialized producer implementation*, somehow, produces an argument from an *input source*, possibly performing side effects along the way, 
- the *materialized program implementation*, somehow, transforms the argument to a result, possibly performing side effects along the way,
- the *materialized consumer implementation*,somehow, consumes the argument and result to an *output sink*, possibly performing side effects along the way.

Side effects can be *external* or *internal*.

External side effects interact with the external world.

Internal side effects typically manifest themselves in the function types of materialized program implementations.

Materialized main program implementations are, eventually, used in a *main* `Scala` *programs*.

## Basic Programming Capabilities

The *basic* programming capabilities are

- being *functional*

and

- *sequential composition*
- *construction*
- *condition*

Programming capabilities have a *denotational*, *pointfree* nature.

They generalize corresponding capabilities of functions.

Functions also have an important capability having an *operational*, *pointful* nature

- *application*

A *function application* is an *expression*.

Expressions are operational artifacts: they are *evaluated* to a result.

## `Functional`

```scala
package psbp.specification.program

trait Functional[>-->[- _, + _]]:

  // declared

  private[psbp] def toProgram[Z, Y]: (Z => Y) => Z >--> Y

  // defined extensions

  extension [Z, Y] (`z=>y`: Z => Y) def asProgram: Z >--> Y =
    toProgram(`z=>y`)
```

`Functional` specifies that *functions can be used as programs*.

The public member `asProgram` is an `extension` that can be used as postfix operator.

The full power that comes with the `PSBP` library is not available for functions that are used as programs using `asProgram`, so, which functions to use as programs using `asProgram` is an important choice.

Funny looking names like `` `z=>y` `` can be thought of as *typeful generic names* for functions.

## `Composition`

```scala
package psbp.specification.program

trait Composition[>-->[- _, + _]]:

  // declared

  private[psbp] def andThen[Z, Y, X](`z>-->y`: Z >--> Y, `y>-->x`: => Y >--> X): Z >--> X

  // defined extensions
  
  extension [Z, Y, X] (`z>-->y`: Z >--> Y) def >-->(`y>-->x`: => Y >--> X): Z >--> X =
    andThen(`z>-->y`, `y>-->x`)
```

`Composition` specifies that *programs can be composed sequentially*.

Sequential composition is simply referred to as composition.

The public member `>-->` is an `extension` that can be used as infix operator.

The type of the second parameter of `andThen` is a *call-by-name* one.

Funny looking names like `` `z>-->y` `` can be thought of as typeful generic names for programs.

## `Construction`

```scala
package psbp.specification.program

trait Construction[>-->[- _, + _]]:

  // declared

  private[psbp] def construct[Z, Y, X] (`z>-->y`: Z >--> Y, `z>-->x`: => Z >--> X): Z >--> (Y && X) 

  // defined extensions

  extension [Z, Y, X] (`z>-->y`: Z >--> Y) def &&(`z>-->x`: => Z >--> X): Z >--> (Y && X) =
    construct(`z>-->y`, `z>-->x`)
```

where

```scala
package psbp.specification.program

// product

type &&[+Z, +Y] = (Z, Y)

// ...
```

`Construction` specifies that *programs can construct product based composite data*.

The public member `&&` is an `extension` that can be used as infix operator.

The type of the second argument of `construct` is a call-by-name one.

`&&` is, somewhat artificially, sequentially biased towards it's first argument.

## `Condition`

```scala
package psbp.specification.program

// import psbp.specification.types.||

trait Condition[>-->[- _, + _]]:

  // declared

  private[psbp] def conditionally[Z, Y, X] (`y>-->z`: => Y >--> Z, `x>-->z`: => X >--> Z): (Y || X) >--> Z

  // defined extensions
  
  extension [Z, Y, X] (`y>-->z`: => Y >--> Z) def ||(`x>-->z`: => X >--> Z): (Y || X) >--> Z =
    conditionally(`y>-->z`, `x>-->z`)
```

where

```scala
package psbp.specification.program

// ...

// sum

enum ||[+Z, +Y]:
  case Left(z: Z) extends (Z || Y)
  case Right(y: Y) extends (Z || Y)

  def foldSum[X](`z=>x`: => Z => X, `y=>x`: => Y => X): X =
    this match
      case Left(z) =>
        `z=>x`(z)
      case Right(y) =>
        `y=>x`(y)  
```

`foldSum` hides the `enum` representation of the sum type.

`Condition` specifies that *programs can perform sum based conditional logic*.

The public member `||` is an extension that can be used as infix operator.

The types of the arguments of `conditionally` are call-by-name ones.

## `Program`

```scala
package psbp.specification.program

trait Program[>-->[- _, + _]]
  extends Functional[>-->]
  with Composition[>-->]
  with Construction[>-->]
  with Condition[>-->]:

    private implicit val program: Program[>-->] = this

    // defined extensions

    extension [Z, Y, X, W] (`z>-->x`: Z >--> X) def &&&(`y>-->w`: => Y >--> W): (Z && Y) >--> (X && W) =
      (`(z&&y)>-->z` >--> `z>-->x`) && (`(z&&y)>-->y` >--> `y>-->w`)  

    extension [Z, Y, X, W] (`x>-->z`: => X >--> Z) def |||(`w>-->y`: => W >--> Y): (X || W) >--> (Z || Y) =
      (`x>-->z` >--> `z>-->(z||y)`) || (`w>-->y` >--> `y>-->(z||y)`)  

    extension [Z, Y] (program: Z >--> Y) def toMainWith(producer: Unit >--> Z, consumer: (Z && Y) >--> Unit) =
      producer >--> {
        Let { 
          program
        } In { 
          consumer 
        }
      }
    
    // defined
    
    def Let[Z, Y, X](`z>-->y`: Z >--> Y): In[Z, Y, X] =
      new {
        def In(`(z&&y)>-->x`: => (Z && Y) >--> X): Z >--> X =
          (`z>-->z` && `z>-->y`) >--> `(z&&y)>-->x`
      } 

    private[psbp] trait In[Z, Y, X]:
      def In(`(z&&y)>-->x`: => (Z && Y) >--> X): Z >--> X
    
    def If[Z, Y](`z>-->b`: Z >--> Boolean): Apply[Z, Y] =
      new {
        override def apply(`z>-t->y`: => Z >--> Y): Else[Z, Y] =
          new {
            override def Else(`z>-f->y`: => Z >--> Y): Z >--> Y =
              Let {
                `z>-->b`
              } In {
                `(z&&b)>-->(z||z)`
              } >--> {
                `z>-t->y` || `z>-f->y`
              }  
          }
      } 

    private[psbp] trait Apply[Z, Y]:
      def apply(`z>-t->y`: => Z >--> Y): Else[Z, Y]

    private[psbp] trait Else[Z, Y]:
      def Else(`z>-f->y`: => Z >--> Y): Z >--> Y
```

where

```scala
package psbp.specification.program

import scala.language.postfixOps

// functional

def `z>-->z`[>-->[- _, + _]: Functional, Z]: Z >--> Z =
  `z=>z` asProgram
    
// construction

def `(z&&y)>-->z`[>-->[- _, + _]: Functional, Z, Y]: (Z && Y) >--> Z =
  `(z&&y)=>z` asProgram
    
def `(z&&y)>-->y`[>-->[- _, + _]: Functional, Z, Y]: (Z && Y) >--> Y =
  `(z&&y)=>y` asProgram

// condition

def `z>-->(z||y)`[>-->[- _, + _]: Functional, Z, Y]: Z >--> (Z || Y) =
  `z=>(z||y)` asProgram
  
def `y>-->(z||y)`[>-->[- _, + _]: Functional, Z, Y]: Y >--> (Z || Y) =
  `y=>(z||y)` asProgram 

// construction and condition
  
def `(z&&b)>-->(z||z)`[>-->[- _, + _]: Functional, Z]: (Z && Boolean) >--> (Z || Z) =
  `(z&&b)=>(z||z)` asProgram 

// ...  
```

are program utilities,

where

```scala
package psbp.specification.program

// functional

def `z=>z`[Z]: Z => Z = 
  z =>
    z

// construction

def `(z&&y)=>z`[Z, Y]: (Z && Y) => Z =
  (z, _) => 
    z

def `(z&&y)=>y`[Z, Y]: (Z && Y) => Y =
  (_, y) => 
    y  

// condition

import ||.{ Left, Right }

def `z=>(z||y)`[Z, Y]: Z => (Z || Y) =
  z =>
    Left(z)

def `y=>(z||y)`[Z, Y]: Y => (Z || Y) =
  y =>
    Right(y)   

// construction and condition

def `(z&&b)=>(z||z)`[Z]: (Z && Boolean) => (Z || Z) =
  (z, b) => 
    (z, b) match {
      case (_, true) => Left(z)
      case (_, false) => Right(z) 
    } 
    
// ...    
```

are function utilities.

The members `&&&` resp. `|||` are `extension`'s that are more complex versions of `&&` resp. `||`.

The `Let {} In {}` programming capability specifies that programs can *define local values*.

The `If() {} Else {}` programming capability specifies that programs can *perform if-then-else based logic*.

`Let {} In {}` and `If() {} Else {}` are perfect examples of the **sca**lable **la**nguage capability of `Scala`.

The member `toMainWith` is an `extension` that combines a program with a producer and a consumer to a main program.

The simple, pointfree definition of `factorial` has abstracted away complexity of the pointful definition.

On the one hand this is an advantage because being confronted with complexity is not fun.

On the other hand this is a disadvantage because abstraction needs to be understood.

But here is the thing: often different definitions have similar complexity.

Understanding the abstraction of similar complexity of different definitions needs to be done *only once*.

Being confronted with the complexity of different situations happens *over and over again*.

Let's explain the pointfree definition of `toMainWith` in a pointful way using an argument `()` (thinking of programs as functions).

At level `1`, `toMainWith` looks like

```scala
      producer >--> {
        Let { 
          program
        } In { 
          consumer 
        }
      }  
```

```scala
      // level 1.1 
        >--> 
          // level 1.2
```

at level `1.1`, `toMainWith` looks like

```scala
    producer
```

The result is `producer(())`, which equals, say, `z` a produced argument value of type `Z`.

At level `1.2`, `toMainWith` looks like

```scala
        Let { 
          // level 2.1
        } In { 
          // level 2.2
        }
      }  
```

At level `2.1`, `toMainWith` looks like

```scala
          program
```

a local result value, say `y`, which equals `program(z)` is constructed, which is available as the second component of a product value `(z, y)`.

At level `2.2`, `factorial` looks like

```scala
          consumer 
```

The result is `consumer(z, y)`, which equals `consumer(z, program(z))`, which equals `consumer(producer(()), program(producer(())))`.

## About power of expression of `&&&` and `Let {} In {}`

```scala
package examples.specification.program

import psbp.specification.program.{ &&, Program, `z>-->(z&&z)`, `(z&&y)>-->z` , `(z&&y&&x)>-->(y&&x)` }

def `construct using &&&`[>-->[- _, + _]: Program, Z, Y, X] 
  (`z>-->y`: Z >--> Y, `z>-->x`: => Z >--> X): Z >--> (Y && X) =
  `z>-->(z&&z)` >--> (`z>-->y` &&& `z>-->x`)

def constructUsingLet[>-->[- _, + _]: Program, Z, Y, X] 
  (`z>-->y`: Z >--> Y, `z>-->x`: => Z >--> X): Z >--> (Y && X) =
 
  val program: Program[>-->] = summon[Program[>-->]]
  import program.Let

  Let {
    `z>-->y`
  } In {
    Let {
      `(z&&y)>-->z` >--> `z>-->x`
    } In {
      `(z&&y&&x)>-->(y&&x)`
    }
  }
```

where

```scala
package psbp.specification.program

// ...

// construction

// ...

def `z>-->(z&&z)`[>-->[- _, + _]: Functional, Z]: Z >--> (Z && Z) =
  `z=>(z&&z)` asProgram  

def `(z&&y&&x)>-->(y&&x)`[>-->[- _, + _]: Functional, Z, Y, X]: (Z && Y && X) >--> (Y && X) =
  `(z&&y&&x)=>(y&&x)` asProgram
```

are program utilities,

where

```scala
package psbp.specification.program

// ...

// construction

// ...

def `z=>(z&&z)`[Z]: Z => (Z && Z) =
  z =>
    (z, z)   
    
def `(z&&y&&x)=>(y&&x)`[Z, Y, X]: (Z && Y && X) => (Y && X) =
  case ((_, y), x) =>
    (y, x) 
```

are function utilities.

`&&&` and `Let {} In {}` have the same power of expression as `construct` and `&&`.

## About power of expression of `|||` and `If() {} Else {}`

```scala
package examples.specification.program

import psbp.specification.program.{ ||, Program, `(z||z)>-->z`, `(y||x)>-->b`, `(y||x)>-->y`, `(y||x)>-->x` }

def `conditionally using |||`[>-->[- _, + _]: Program, Z, Y, X]
  (`y>-->z`: => Y >--> Z, `x>-->z`: => X >--> Z): (Y || X) >--> Z =
  (`y>-->z` ||| `x>-->z`) >--> `(z||z)>-->z`

def conditionallyUsingIf[>-->[- _, + _]: Program, Z, Y, X]
  (`y>-->z`: => Y >--> Z, `x>-->z`: => X >--> Z): (Y || X) >--> Z =

  val program: Program[>-->] = summon[Program[>-->]]
  import program.If

  If(`(y||x)>-->b`) {
    `(y||x)>-->y` >--> `y>-->z`
  } Else {
    `(y||x)>-->x` >--> `x>-->z`
  }
```
where

```scala
package psbp.specification.program

// ...

// condition

def `(z||z)>-->z`[>-->[- _, + _]: Functional, Z]: (Z || Z) >--> Z =
  `(z||z)=>z` asProgram  
  
def `(y||x)>-->b`[>-->[- _, + _]: Functional, Y, X]: (Y || X) >--> Boolean =
  `(y||x)=>b` asProgram

def `(y||x)>-->y`[>-->[- _, + _]: Functional, Y, X]: (Y || X) >--> Y =
  `(y||x)=>y` asProgram

def `(y||x)>-->x`[>-->[- _, + _]: Functional, Y, X]: (Y || X) >--> X =
  `(y||x)=>x` asProgram

// ...
```

are program utilities,

where

```scala
package psbp.specification.program

// ...

// condition

// ...

def `(z||z)=>z`[Z]: (Z || Z) => Z =
  _.foldSum(z => z, z => z)  
  
def `(y||x)=>b`[Y, X]: (Y || X) => Boolean =
  _.foldSum(_ => true, _ => false)

def `(y||x)=>y`[Y, X]: (Y || X) => Y =
  _.foldSum(y => y, _ => ???) 

def `(y||x)=>x`[Y, X]: (Y || X) => X =
  _.foldSum(_ => ???, x => x)
```

are function utilities.

`|||` and `If() {} Else {}` have the same power of expression as `conditionally` and `||`.

## `factorial`

```scala
package examples.specification.program

import psbp.specification.program.Program

def factorial[>-->[- _, + _]: Program]: BigInt >--> BigInt =

  val program: Program[>-->] = summon[Program[>-->]]
  import program.{ Let, If }

  If(isZero) {
    one
  } Else {
    Let {
      subtractOne >--> factorial
    } In {
      multiply
    }
  }
```

where

```scala
package examples.specification.program

import scala.language.postfixOps

import psbp.specification.types.&&

import psbp.specification.functional.Functional

import examples.specification.function

def isZero[>-->[- _, + _]: Functional]: BigInt >--> Boolean =  
  function.isZero asProgram

def one[>-->[- _, + _]: Functional, Z]: Z >--> BigInt =
  function.one asProgram

def subtractOne[>-->[- _, + _]: Functional]: BigInt >--> BigInt =
  function.subtractOne asProgram

def multiply[>-->[- _, + _]: Functional]: (BigInt && BigInt) >--> BigInt =  
  function.multiply asProgram


// ...
```

are program utilities,

where

```scala
package examples.specification.function

import psbp.specification.types.&&

val isZero: BigInt => Boolean =
  n =>
   n == 0
  
def one[Z]: Z => BigInt =
  _ =>
    1
  
val subtractOne: BigInt => BigInt =
  n =>
    n - 1
    
val multiply: BigInt && BigInt => BigInt =
  (n, m) =>
    n * m

// ...
```

are function utilities.

`factorial` is a program that makes use of programming capabilities 

- `asProgram` (being functional)
- `>-->` (sequential composition)
- `Let {} In {}` (defining local values)
- `If() {} Else {}` (performing if-then-else logic)

Let's explain the pointfree definition of `factorial` in a pointful way using an argument `i` (thinking of programs as functions).

At level `1`, `factorial` looks like

```scala
  If(isZero) {
    // level 2.1
  } Else {
    // level 2.2
  }
```

If `isZero(i)`, in other words, if `i == 0` then, 

at level `2.1`, `factorial` looks like

```scala
    one
```

The result is `one(i)`, which equals `1`.

Otherwise, in other words, if `i != 0` then, 

at level `2.2`, `factorial` looks like

```scala
    Let {
      // level 3.1
    } In {
      // level 3.2
    }
```

At level `3.1`, `factorial` looks like

```scala
      subtractOne >--> factorial
```

a local value, which equals `(subtractOne >--> factorial)(i)`, which equals `factorial(subtractOne(i))`, which equals `factorial(i - 1)` is constructed, which is available as the second component of a product value `(i, factorial(i - 1))`.

At level `3.2`, `factorial` looks like

```scala
      multiply
```

The result is `multiply(i, factorial(i - 1))`, which equals `i * factorial(i - 1)`.

## `fibonacci`

```scala
package examples.specification.program

import psbp.specification.program.Program

def fibonacci[>-->[- _, + _]: Program]: BigInt >--> BigInt =

  val program: Program[>-->] = summon[Program[>-->]]
  import program.{ Let, If }

  If(isZero) {
    zero
  } Else {
    If(isOne) {
      one
    } Else {
      (subtractOne && subtractTwo) >-->
        (fibonacci &&& fibonacci) >-->
        add
    }
  }
```

where

```scala
package examples.specification.program

// ...

def zero[Z]: Z => BigInt =
  _ =>
    0

val isOne: BigInt => Boolean =
  n =>
   n == 1
   
val subtractTwo: BigInt => BigInt =
  n =>
    n - 2   

val add: BigInt && BigInt => BigInt =
  (n, m) =>
    n + m

// ...
```

are program utilities,

where

```scala
package examples.specification.program

// ..

def isOne[>-->[- _, + _]: Functional]: BigInt >--> Boolean =  
  function.isOne asProgram  

def subtractTwo[>-->[- _, + _]: Functional]: BigInt >--> BigInt =
  function.subtractTwo asProgram  

def add[>-->[- _, + _]: Functional]: (BigInt && BigInt) >--> BigInt =  
  function.add asProgram 

// ...
```

are function utilities.

`fibonacci` is a program that makes use of programming capabilities 

- `asProgram` (being functional)
- `>-->` (sequential composition)
- `&&` and `&&&` (construction)
- `If() {} Else {}` (performing if-then-else logic)

Let's explain the pointfree definition of `fibonacci` in a pointful way using an argument `i` (thinking of programs as functions).

At level `1`, `fibonacci` looks like

```scala
  If(isZero) {
    // level 2.1
  } Else {
    // level 2.2
  }
```

If `isZero(i)`, in other words, if `i == 0` then, 

at level `2.1`, `fibonacci` looks like

```scala
    zero
```

The result is `zero(i)`, which equals `0`.

Otherwise, in other words, if `i != 0` then, 

at level `2.2`, `fibonacci` looks like

```scala
    If(isOne) {
      // level 3.1
    } Else {
      // level 3.2
    }
```

If `isOne(i)` in other words if `i == 1` then, 

at level `3.1`, `fibonacci` looks like

```scala
      one
```

The result is `one(i)`, which equals `1`.

Otherwise, in other words, if `i != 1` then, 

at level `3.2`, `fibonacci` looks like

```scala
      (subtractOne && subtractTwo) >-->
        (fibonacci &&& fibonacci) >-->
        add
```

A first product value `(subtractOne && subtractTwo)(i)`, which equals `(i - 1, i - 2)`, is constructed.

A second product value `(fibonacci &&& fibonacci)(i - 1, i - 2)` which equals `(fibonacci(i - 1), fibonacci(i - 2))` is constructed.

The result is `add(fibonacci(i - 1), fibonacci(i - 2))` which equals `fibonacci(i - 1) + fibonacci(i - 2)`.

## `mainFactorial`

```scala
package examples.specification.program.effectful

import psbp.specification.program.Program

import examples.specification.program.factorial

def mainFactorial[>-->[- _, + _]: Program]: Unit >--> Unit =
  factorial toMainWith (
    producer = intProducer,
    consumer = factorialConsumer
  )
```

where


```scala
package examples.specification.program.effectful

import scala.language.postfixOps

import psbp.specification.program.Program

def intProducer[>-->[- _, + _]: Program]: Unit >--> BigInt = 
  { (_: Unit) =>
      println("Please type an integer")
      BigInt(scala.io.StdIn.readInt)
  } asProgram
```

and where

```scala
package examples.specification.program.effectful

import scala.language.postfixOps

import psbp.specification.program.{ &&, Program }

def factorialConsumer[>-->[- _, + _]: Program]: (BigInt && BigInt) >--> Unit =
  {
    (`i&&j`: BigInt && BigInt) =>
      val i = `i&&j`._1
      val j = `i&&j`._2
      println(s"applying factorial to the integer argument $i yields result $j")
  } asProgram
```

`mainFactorial` is a main program that, for now, makes use of an *effectful* producer and an *effectful* consumer.

## `mainFibonacci`

```scala
package examples.specification.program.effectful

import psbp.specification.program.Program

import examples.specification.program.fibonacci

def mainFibonacci[>-->[- _, + _]: Program]: Unit >--> Unit =
  fibonacci toMainWith (
    producer = intProducer,
    consumer = fibonacciConsumer
  )
```

where

```scala
package examples.specification.program.effectful

// ...

def fibonacciConsumer[>-->[- _, + _]: Program]: (BigInt && BigInt) >--> Unit =
  {
    (`i&&j`: BigInt && BigInt) =>
      val i = `i&&j`._1
      val j = `i&&j`._2
      println(s"applying fibonacci to the integer argument $i yields result $j")
  } asProgram
```

`mainFibonacci` is a main program that, for now, makes use of an effectful producer and an effectful consumer.

## Materialization

```scala
package psbp.specification.materialization

trait Materialization[>-->[- _, + _], -Z, +Y]:

  // declared

  val materialize: (Unit >--> Unit) => Z => Y

  // defined extensions

  extension (`u>-->u`: Unit >--> Unit) def materialized: Z => Y =
    materialize(`u>-->u`)
```

Main programs are materialized to functions.

The function type `Z => Y` is generic to allow for various materializations of main programs.

The public member `materialized` is an `extension` that can be used as postfix operator.

# Computations

An *internal specification part* of the `PSBP` library models *computing*.

Think about it as explaining to `Scala` what computing is all about.

Members of internal specification `trait`'s are referred to as *computational capabilities*.

A `val` that is defined in terms of computational capabilities is referred to as a *computation*. 

A computation has a *descriptive* nature, in fact it would be more appropriate to refer to it as a *computation description*.

In a way a computation is a generalization of an *expression*.

An expression of type `Y` evaluates to a *result* of type `Y`

A computation of type `C[Y]` *describes* computing a result of type `Y`.

A computation may, or may not, *describe* performing side effects along the way.

## Basic computational capabilities

The *basic* computational capabilities are

- *resulting*
- *binding*

Computational capabilities have an *operational* nature.

They contribute to *performing computations*

Performing computations generalizes *evaluating expressions*.

## `Resulting`

```scala
package psbp.internalSpecification.computation

private[psbp] trait Resulting[C[+ _]]:

  // declared

  private[psbp] def result[Z]: Z => C[Z]
```

The `result` member of `Computation` specifies that performing a computation yields a *result*.

## `Binding`

```scala
package psbp.internalSpecification.computation

private[psbp] trait Binding[C[+ _]]:

  // declared

  private[psbp] def bind[Z, Y] (cz: C[Z], `z=>cy`: => Z => C[Y]): C[Y]

  // defined computational capability

  private[psbp] def join[Z] (ccz: C[C[Z]]): C[Z] =
    bind(ccz, identity)

  // defined extensions

  extension [Z, Y] (cz: C[Z]) def >=(`z=>cy`: => Z => C[Y]): C[Y] =
    bind(cz, `z=>cy`)

  extension [Z, Y] (ccz: C[C[Z]]) def joined =
    join(ccz) 
```

The `bind` computational capability specifies that while performing a computation, the result yielded by performing an inner computation can be *bound* to the argument of a *continuation function* transforming it to a result that is an outer computation where performing continues with.

Compare this with evaluating expressions.

The `join` computational capability specifies that an inner computation result can be seen as an outer computation, modeling *nested computations*.

Compare this with nested expressions.

The member `>=` is an`extension` that can be used as infix operator.

The member `joined` is an`extension` that can be used as postfix operator.

## `Computation`

```scala
package psbp.internalSpecification.computation

import psbp.specification.program.{ &&, ||, Program }

private[psbp] trait Computation[C[+ _]] 
  extends Resulting[C] 
  with Binding[C]

private[psbp] given programFromComputation[C[+ _]: Computation]: Program[[Z, Y] =>> Z => C[Y]] with
  
  private val computation: Computation[C] = summon[Computation[C]]

  import computation.{ result, bind }

  private type `=>C`[-Z, +Y] = Z => C[Y]

  private[psbp] override def andThen[Z, Y, X]
    (`z>-->y`: Z `=>C` Y, `y>-->x`: => Y `=>C` X): Z `=>C` X =
    z =>
      `z>-->y`(z) >= 
        `y>-->x`

  private[psbp] override def construct[Z, Y, X]
    (`z>-->y`: Z `=>C` Y, `z>-->x`: => Z `=>C` X): Z `=>C` (Y && X) =
    z =>
      `z>-->y`(z) >= { y => 
        `z>-->x`(z) >= { x =>
          result(y, x)
        }
      }

  private[psbp] override def conditionally[Z, Y, X]
    (`y>-->z`: => Y `=>C` Z, `x>-->z`: => X `=>C` Z): (Y || X) `=>C` Z =
    _.foldSum(`y>-->z`, `x>-->z`)
```

`programFromComputation` is a `given` that defines that the basic programming capabilities of a program of type `Z => C[Y]` can be defined in terms of the basic computational capabilities of the computation of type `C[Y]`.

Compare this with functions being defined in terms of expressions.

## Setting the scene

*The* `PSBP` *library limits the program types* `Z >--> Y` *to program types* `Z => C[Y]` *defined in terms of computation types* `C[Y]`.

The `PSBP` specification `trait`''s define an *application developer API*.

The `PSBP` internal specification `trait`''s define a *library developer API*.

It is a deliberate choice to not let application developers make use of the pointful library developer API, forcing them to think in a pointfree way.

# Active programming

So far no implementation of the basic computational capabilities has been given yet.

A completely trivial way to implement computations of type `C[Y]` is as expressions of type `Y`, referred to as *active computations*.

As a consequence, programs of type `Z => C[Y]`, are implemented as functions of type `Z => Y`, referred to as *active programs*.

## `` Program[`=>A`] `` 

```scala
package psbp.implementation.active

import psbp.specification.program.Program

import psbp.internalSpecification.computation.{ Computation, programFromComputation }

given Computation[Active] with

  private[psbp] def result[Z]: Z => Active[Z] =
    z =>
      z

  private[psbp] def bind[Z, Y] (cz: Active[Z], `z=>cy`: => Z => Active[Y]): Active[Y] =
    `z=>cy`(cz)

given Program[`=>A`] = programFromComputation[Active]
```

where

```scala
package psbp.implementation.active

type Active[+Y] = Y

type `=>A`[-Z, +Y] = Z => Active[Y]
```

`Active` is a  `given` implementation of `Computation`.

As a consequence, `` `=>A` `` is an implementation of `Program`.

Think of active programming as *pure functional programming*.

Of course, using `Scala`, it is possible to write functions that perform side effects along the way of transforming their argument to a result.

For example, inside of `mainFactorial`, `intProducer` are `factorialConsumer` perform side effects.

Eventually side effects are unavoidable, but the intention is to push them as far as possible to the outside of programs in general, and main programs in particular.

## `` Materialization[`=>A`, Unit, Unit] `` 

```scala
package psbp.implementation.active

import psbp.specification.materialization.Materialization

given Materialization[`=>A`, Unit, Unit] with

  val materialize: (Unit `=>A` Unit) => Unit => Unit =
    identity
```

Materialization of `` `=>A` `` is completely trivial.

## Running active effectful factorial

```scala
package examples.implementation.active.program.effectful

import psbp.implementation.active.given

import examples.specification.program.effectful.mainFactorial

@main def factorial(args: String*): Unit =
  mainFactorial materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.implementation.active.program.effectful.factorial 
Please type an integer
10
applying factorial to the integer argument 10 yields result 3628800
[success] ...
```

## Running active effectful fibonacci

```scala
package examples.implementation.active.program.effectful

import psbp.implementation.active.given

import examples.specification.program.effectful.mainFibonacci

@main def fibonacci(args: String*): Unit =
  mainFibonacci materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.implementation.active.program.effectful.fibonacci 
Please type an integer
10
applying fibonacci to the integer argument 10 yields result 55
[success] ...
```

# Reactive programming

A less trivial way to implement computations of type `C[Y]` is as *callback handlers* of type `(Y => Unit) => Unit`, referred to as *reactive computations*.

As a consequence, programs of type `Z => C[Y]`, are implemented as functions to callback handlers, referred to as *reactive programs*.

## `` Program[`=>R`] `` 

```scala
package psbp.implementation.reactive

import psbp.specification.program.Program

import psbp.internalSpecification.computation.Computation

given Computation[Reactive] with

  private[psbp] def result[Z]: Z => Reactive[Z] =
    z => 
      `z=>u` =>
        `z=>u`(z)

  private[psbp] def bind[Z, Y] (cz: Reactive[Z], `z=>cy`: => Z => Reactive[Y]): Reactive[Y] =
    `y=>u` =>
      cz { 
        z =>
          `z=>cy`(z)(`y=>u`)
      }

import psbp.internalSpecification.computation.given // programFromComputation

given Program[`=>R`] = programFromComputation[Reactive]
```

where

```scala
package psbp.implementation.reactive

type Reactive[+Y] = (Y => Unit) => Unit

type `=>R`[-Z, +Y] = Z => Reactive[Y]
```

`Reactive` is a  `given` implementation of `Computation`.

As a consequence, `` `=>R` `` is an implementation of `Program`.

## `` Materialization[`=>R`, Unit, Unit] `` 

```scala
package psbp.implementation.reactive

import psbp.specification.materialization.Materialization

given Materialization[`=>R`, Unit, Unit] with

  val materialize: (Unit `=>R` Unit) => Unit => Unit =
    `u>-->u` =>
      u =>
        `u>-->u`(u)(identity)
```

Materialization of `` `=>R` `` is less trivial.

## Getting the types right

For reactive programming, there is really only one reasonable way to define `result`, `bind` and `materialize` in a way to get the types right.

This is a recurring theme in pure functional programming and, as a generalization, in program specification based programming.

Often an almost trivial choice for a function component, resp. program component does the trick to get the types right.

## Running reactive effectful `factorial`

```scala
package examples.implementation.reactive.program.effectful

import psbp.implementation.reactive.given

import examples.specification.program.effectful.mainFactorial

@main def factorial(args: String*): Unit =
  mainFactorial materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.implementation.reactive.program.effectful.factorial 
Please type an integer
10
applying factorial to the integer argument 10 yields result 3628800
[success] ...
```

The only difference with the active version is the usage of a different dependency injection by `import`.

## Running reactive effectful `fibonacci`

```scala
package examples.implementation.reactive.program.effectful

import psbp.implementation.reactive.given

import examples.specification.program.effectful.mainFibonacci

@main def fibonacci(args: String*): Unit =
  mainFibonacci materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.implementation.reactive.program.effectful.fibonacci 
Please type an integer
10
applying fibonacci to the integer argument 10 yields result 55
[success] ...
```
## Some remarks

Active `fibonacci` is extremely inefficient. 

Reactive `fibonacci` is even worse.

Time for some optimizations!

# Accumulator based optimization

```scala
package psbp.specification.program

// ...

// accumulator based optimization

def optimizeWith[>-->[- _, + _]: Program, A, Z, Y](
  accumulatorInitializer: Z >--> A,
  argumentPredicate: Z >--> Boolean,
  updater: (Z && A) >--> (Z && A),
  resultExtractor: A >--> Y): Z >--> Y =

  val program: Program[>-->] = summon[Program[>-->]]
  import program.{ Let, If }

  val argument: (Z && A) >--> Z = `(z&&y)>-->z`
  val accumulator: (Z && A) >--> A = `(z&&y)>-->y`

  lazy val recursiveAccumulatorUpdater: (Z && A) >--> A =
    If(argument >--> argumentPredicate) {
      accumulator
    } Else {
      updater >--> recursiveAccumulatorUpdater
    }
    
  Let {
    accumulatorInitializer
  } In {
    recursiveAccumulatorUpdater >--> resultExtractor
  }   
```

Recursive programs like `fibonacci` can be optimized using an *accumulator*.

`recursiveAccumulatorUpdater` is also a recursive program, but, when carefully designed, it should be efficient.

## `optimizedFactorial`

```scala
package examples.specification.program

import psbp.specification.program.Program

import psbp.specification.program.{ `(z&&y)>-->z` => argument, `z>-->z` => accumulator, optimizeWith }

def optimizedFactorial[>-->[- _, + _]: Program]: BigInt >--> BigInt =
  optimizeWith(
    accumulatorInitializer = one, 
    argumentPredicate = isZero, 
    updater = (argument >--> subtractOne) && multiply, 
    resultExtractor = accumulator
  )
```

For `optimizedFactorial` the accumulator type is `BigInt`.

One `BigInt` optimizes one recursion occurrence.

## `optimizedFibonacci`

```scala
package examples.specification.program

import psbp.specification.program.Program

import psbp.specification.program.{ `(z&&y)>-->z` => firstAccumulator, `(z&&y)>-->y` => secondAccumulator, optimizeWith}

def optimizedFibonacci[>-->[- _, + _]: Program]: BigInt >--> BigInt =
  optimizeWith(
    accumulatorInitializer = one && one, 
    argumentPredicate = isZero, 
    updater = subtractOne &&& (secondAccumulator && add), 
    resultExtractor = firstAccumulator
  )
```

For `optimizedFibonacci` the accumulator type is `BigInt && BigInt`.

Two `BigInt`'s optimize two recursion occurrences.

## `mainOptimizedFactorial`

```scala
package examples.specification.program.effectful

import psbp.specification.program.Program

import examples.specification.program.optimizedFactorial

def mainOptimizedFactorial[>-->[- _, + _]: Program]: Unit >--> Unit =
  optimizedFactorial toMainWith (
    producer = intProducer,
    consumer = factorialConsumer
  )
```

## `mainOptimizedFibonacci`

```scala
package examples.specification.program.effectful

import psbp.specification.program.Program

import examples.specification.program.optimizedFibonacci

def mainOptimizedFibonacci[>-->[- _, + _]: Program]: Unit >--> Unit =
  optimizedFibonacci toMainWith (
    producer = intProducer,
    consumer = fibonacciConsumer
  )
```

## Running active effectful optimized factorial

```scala
package examples.active.program.effectful

import psbp.active.program.given

import psbp.active.materialization.given

import examples.specification.program.effectful.mainOptimizedFactorial

@main def optimizedFactorial(args: String*): Unit =
  mainOptimizedFactorial materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.implementation.active.program.effectful.optimizedFactorial 
Please type an integer
10
applying factorial to the integer argument 10 yields result 3628800
[success] ...
```

## Running active effectful optimized fibonacci

```scala
package examples.implementation.active.program.effectful

import psbp.implementation.active.given

import examples.specification.program.effectful.mainOptimizedFibonacci

@main def optimizedFibonacci(args: String*): Unit =
  mainOptimizedFibonacci materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.implementation.active.program.effectful.optimizedFibonacci 
Please type an integer
10
applying fibonacci to the integer argument 10 yields result 89
[success] ...
```

You can also try it with `1000` instead of `10`!

With `10000` you will get a stack overflow.

## Running reactive effectful optimized factorial

```scala
package examples.implementation.reactive.program.effectful

import psbp.implementation.reactive.given

import examples.specification.program.effectful.mainOptimizedFactorial

@main def optimizedFactorial(args: String*): Unit =
  mainOptimizedFactorial materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.implementation.reactive.program.effectful.optimizedFactorial 
Please type an integer
10
applying factorial to the integer argument 10 yields result 3628800
[success] ...
```

## reactive optimized `fibonacci`

```scala
package examples.implementation.reactive.program.effectful

import psbp.implementation.reactive.given

import examples.specification.program.effectful.mainOptimizedFibonacci

@main def optimizedFibonacci(args: String*): Unit =
  mainOptimizedFibonacci materialized ()
```

Let's run it

```scala
sbt:PSBP> run
...
[info] running examples.implementation.reactive.program.effectful.optimizedFibonacci 
Please type an integer
10
applying fibonacci to the integer argument 10 yields result 89
[success] ...
```
You can also try it with `250` instead of `10`!

With `1000` you will get a stack overflow.




