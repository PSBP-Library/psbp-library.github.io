package psbp.external.specification.functional

import scala.language.postfixOps

import psbp.external.specification.types.{ 
  &&
  , ||
}

import psbp.external.specification.program.Functional

import psbp.external.specification.function

// functional

def `z>-->u`[
  Z
  , >-->[- _, + _]: Functional
]: Z >--> Unit =
  function.`z=>u` asProgram

// construction

def `(z&&y)>-->z`[
  Z, Y
  , >-->[- _, + _]: Functional
]: (Z && Y) >--> Z =
  function.`(z&&y)=>z` asProgram
    
def `(z&&y)>-->y`[
  Z, Y
  , >-->[- _, + _]: Functional
]: (Z && Y) >--> Y =
  function.`(z&&y)=>y` asProgram

def `z>-->(z&&z)`[
  Z
  , >-->[- _, + _]: Functional
]: Z >--> (Z && Z) =
  function.`z=>(z&&z)` asProgram  

def `(z&&y&&x)>-->(y&&x)`[
  Z, Y, X  
  , >-->[- _, + _]: Functional
]: (Z && Y && X) >--> (Y && X) =
  function.`(z&&y&&x)=>(y&&x)` asProgram
    
def `z>-->(z&&u)`[>-->[- _, + _]: Functional, Z]: Z >--> (Z && Unit) =
  function.`z=>(z&&u)` asProgram

def `(y&&u)>-->y`[>-->[- _, + _]: Functional, Y]: (Y && Unit) >--> Y =
  function.`(y&&u)=>y` asProgram 

// condition

def `z>-->(z||y)`[
  Z, Y
  , >-->[- _, + _]: Functional
]: Z >--> (Z || Y) =
  function.`z=>(z||y)` asProgram
  
def `y>-->(z||y)`[
  Z, Y
  , >-->[- _, + _]: Functional
]: Y >--> (Z || Y) =
  function.`y=>(z||y)` asProgram 

def `(z||z)>-->z`[
  Z
  , >-->[- _, + _]: Functional
]: (Z || Z) >--> Z =
  function.`(z||z)=>z` asProgram  
  
def `(y||x)>-->b`[
  Y, X
  , >-->[- _, + _]: Functional
]: (Y || X) >--> Boolean =
  function.`(y||x)=>b` asProgram

def `(y||x)>-->y`[
  Y, X
  , >-->[- _, + _]: Functional
]: (Y || X) >--> Y =
  function.`(y||x)=>y` asProgram

def `(y||x)>-->x`[
  Y, X
  , >-->[- _, + _]: Functional
]: (Y || X) >--> X =
  function.`(y||x)=>x` asProgram    

// construction and condition
  
def `(z&&b)>-->(z||z)`[
  Z
  , >-->[- _, + _]: Functional
]: (Z && Boolean) >--> (Z || Z) =
  function.`(z&&b)=>(z||z)` asProgram 
 
