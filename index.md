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

def `construct using &&&`[>-->[- _, + _]: Program, Z, Y, X] (`z>-->y`: Z >--> Y, `z>-->x`: => Z >--> X): Z >--> (Y && X) =
  `z>-->(z&&z)` >--> (`z>-->y` &&& `z>-->x`)

def constructUsingLet[>-->[- _, + _]: Program, Z, Y, X] (`z>-->y`: Z >--> Y, `z>-->x`: => Z >--> X): Z >--> (Y && X) =
 
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

def `conditionally using |||`[>-->[- _, + _]: Program, Z, Y, X](`y>-->z`: => Y >--> Z, `x>-->z`: => X >--> Z): (Y || X) >--> Z =
  (`y>-->z` ||| `x>-->z`) >--> `(z||z)>-->z`

def conditionallyUsingIf[>-->[- _, + _]: Program, Z, Y, X](`y>-->z`: => Y >--> Z, `x>-->z`: => X >--> Z): (Y || X) >--> Z =

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

