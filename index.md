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

The course is intended to fill up the gap between, on the one hand, the academic world, and, on the other hand, the industrial world.

On the one hand, course is illustrated using `Scala` code, which, nowadays, is considered to be an industrial programming language.
 
On the other hand the code deals with high-level concepts, which, sadly, are still considered to only be used in universities and research centra.

Hopefully, `Scala` programmers will enjoy seeing `Scala` being used to illustrate a programming course dealing with high-level academic concepts.

Hopefully, computer scientists and mathematicians will enjoy seeing their concepts coming alive using industry-level executable `Scala` code.

But there is more, filling up the gap between the academic world and the industrial world can seen as a step in the direction of disciplined programming.

The course examples make use of a `Scala` library, in what follows also referred to as `PSBP` library, that is written using `Scala 3`, also known as `Dotty`, which is the next generation `Scala`.

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

In what follows we simply refer to `Scala 3` as `Scala`.

Below are some links to

- the [Scala](https://www.scala-lang.org/) programming language
- the [Scala 3](https://dotty.epfl.ch/) programming language

and

- the [source code](https://github.com/PSBP-Library/psbp-library.github.io) of the `PSBP` library

An important aspect of disciplined programming is the reuse of components.

Starting from primitive components, component fragments can be plugged into component templates to obtain composite components.

Functional programming makes use of function components.

`Haskell`, designed, among others, by Simon Peyton Jones and Philip Wadler, is one of the most prominent functional programming languages.

See [Haskell](https://www.haskell.org/).

Function components can be reused as pointful, open, white-box components or pointfree, closed, black-box components.

Functional programming, makes use of both white-box and black-box function components.

Pointfree functional programming, also referred to as function-level programming, only makes use of black-box function components.

`FP`, designed by Turing-Award winner John Backus, was the first such a function-level programming language.

See [FP](https://en.wikipedia.org/wiki/FP_(programming_language).

Programming is not only about functions, transforming an argument to a result, but also about possible side-effects happening along the way.

This is where an abstraction of the function concept imposes itself, allowing for more concrete realizations than only functions.

In the context of this course an abstraction of a function is used that is is referred to as a program.

This implies that a course title *Program-Level Programming* imposes itself.

An important aspect of disciplined programming is the strict separation of specifications from implementations.

Moreover external specifications and implementations, to be used by application developers, are separated from internal specifications and implementations, to be used by library desigers.

The external specification part of the `PSBP` library is all about program specifications.

This explains why the course title *Program Specification Based Programming*.

The design `PSBP` library is, somewhat shamelessly, strongly based upon the design of the `FP` language.

But, compared to the `FP` language, the `PSBP` library also has some benefits.

The main benefit of the `PSBP` library is that specified programs can make use of specified side-effects, referred to as effects.

Side effects are inevitable anyway, but, instead of happening in the middle of code, they can be pushed to the border of `@main` `Scala` code that makes use of a materialized implemented main program.

Apart from this main benefit, it has the following extra benefits

- flexibility
 - a library specification, naturally, has many implementations (for example, for side-effect free testing purposes)
 - a language specification, typically, only has only one implementation
- extensibility 
 - adding extra library-level programming ingredients is an easy task that can, naturally, be done by all library desigers
 - adding extra language-level programming ingredients is a more difficult task that can, typically, only be done by a handful of language desigers

## [Introduction](introduction.md)

## [Program](program.md)



