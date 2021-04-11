<style type="text/css"> body { margin: auto; max-width: 44em; font-family: Calibri, sans-serif; font-size: 18pt; } /* automatic heading numbering */ h1 { counter-reset: h2counter; } 
h2 { counter-reset: h3counter; } 
h3 { counter-reset: h4counter; } 
h4 { counter-reset: h5counter; } 
h5 { counter-reset: h6counter; }
h6 { } 
h2:before { counter-increment: h2counter; content: counter(h2counter) ".\0000a0\0000a0"; } 
h3:before { counter-increment: h3counter; content: counter(h2counter) "." counter(h3counter) ".\0000a0\0000a0"; } 
h4:before { counter-increment: h4counter; content: counter(h2counter) "." counter(h3counter) "." counter(h4counter) ".\0000a0\0000a0"; } 
h5:before { counter-increment: h5counter; content: counter(h2counter) "." counter(h3counter) "." counter(h4counter) "." counter(h5counter) ".\0000a0\0000a0"; } 
h6:before { counter-increment: h6counter; content: counter(h2counter) "." counter(h3counter) "." counter(h4counter) "." counter(h5counter) "." counter(h6counter) ".\0000a0\0000a0"; } 
</style>

# Program Specification Based Programming Course

In what follows we often simply refer to *Program Specification Based Programming* as `PSBP`.

This course, in what follows also referred to as `PSBP` course, is an opiniated one.

The course is intended to fill the gap between the industrial world and the academic world.

On the one hand, the course is illustrated with `Scala` code, which, by many programmers, is used in the industrial world.

See [Scala](https://www.scala-lang.org/).
 
On the other hand, the code models category theory concepts, which, by many scientists, is used in the academic world.

See [Category Theory](https://en.wikipedia.org/wiki/Category_theory).

Hopefully, both programmers and scientists will enjoy reading, writing and executing `Scala` code that deals with academic concepts.

But there is more, filling the gap between the academic world and the industrial world can be seen as an important step towards more disciplined programming.

## `PSBP` library

The course examples make use of a library, in what follows also referred to as `PSBP` library.

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
//     Program Specification Based Programming Library
//     author: Luc Duponcheel               2021 - ...
//        
```

See [source code](https://github.com/PSBP-Library/psbp-library.github.io/tree/course).

The `PSBP` library is written using `Scala 3`, also known as `Dotty`, which is the next generation `Scala`.

See [Scala 3](https://dotty.epfl.ch/).

In what follows we simply refer to `Scala 3` as `Scala`.

## components

An important aspect of disciplined programming is the use of components.

Starting from primitive components, components can be plugged into component templates to obtain composite components.

Functional programming uses functions as components, referred to as function components.

`Haskell`, designed, among others, by Simon Peyton Jones and Philip Wadler, is one of the most prominent functional programming languages.

See [Haskell](https://www.haskell.org/).

Components can be used as open, white-box components or closed, black-box components.

Functional programming, uses function components both as white-box components and black-box components.

Pointfree functional programming, also referred to as function-level programming, uses function components only as black-box components.

`FP`, designed by Turing-Award winner John Backus, was the first such a function-level programming language.

See [FP](https://en.wikipedia.org/wiki/FP_(programming_language)).

## side effects

Programming is not only about functions, transforming an argument to a result, but also about possible side-effects happening along the way.

This is where an abstraction of the function concept imposes itself, allowing for more concrete realizations than only functions.

In the context of this course an abstraction of a function is used that is referred to as a program.

*The core sections of the course consistently use the word "code" to distinguish it from the word "program".*

## specifications and implementations

An important aspect of disciplined programming is the separation of specifications from implementations as well as the separation of wxternal specifications and implementations, to be used by application developers, from internal specifications and implementations, to be used by library desigers.

The external specification part of the `PSBP` library is all about program specifications.

The internal specification part of the `PSBP` library is all about computation specifications.

## `FP` versus `PSBP`

The design `PSBP` library is, somewhat shamelessly, strongly based upon the design of the `FP` language.

Despite its conceptual beauty, `FP` has never been a successful language.

There are reasons for this.

Luckily, compared to the `FP` language, the `PSBP` library has some extra benefits.

### effects

Specified programs can have specified side-effects, referred to as effects.

Deciding how to implement effects can be postponed to `@main` `Scala` code that makes use of a materialized main program.

### flexibility

Library level specifications are loose specifications in the sense that they can have implementations with totally different meanings.

Language level specifications are strict specifications in the sense that they can have implementations with totally different meanings.

This flexibility is, for example, very useful for testing purposes.

### extensibility

Adding extra library-level features is a relatively simple and easy task that can be done by a lot of library desigers.

Adding extra language-level features is a relatively complex and difficult task that can only be done by a few language desigers.

## [Introduction](https://github.com/PSBP-Library/psbp-library.github.io/blob/course/introduction.md)

## [Program](https://github.com/PSBP-Library/psbp-library.github.io/blob/course/program.md)



