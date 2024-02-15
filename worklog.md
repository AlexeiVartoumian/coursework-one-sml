
Day1 Thurs 8/02 Coursework Drop:
-----------------------------------------------
<p>Did nothing. put my name on the readMe. Read it a little.
Poked around the codebase</p>
-------------------------------------------------

##Day2 Fri 8/02 Setup:
-----------------------------------
<p>Had issues with getting the project running.
Could not get Lombok to work with the project. 
Did the following:
****************
-Installed Jdk 21\
-Downloaded Lombok Jar file\
-Upgraded to Intellij Ultimate edition\ 
****************
I could not figure out why I could run the project with 
the green button but running the project in the terminal
would always error out with project lombok not found.

Workaround: pass in commandLine arguments through edit
configurations.
</p>
------------------------------------------
##Day3 Mon 12/02 Game Plan :
------------------------------
Make copy of repo .
Understanding:
Machine--> relies on the Translator to read the file turn the program
into a list of instructions from based on string parameters.

Translator --> I can boil down this classes purpose to one role.
Read and Translate. I only care about reading the strings and turning
them into something that I can use.

I begin with instructions.
-use Add Instruction as template for Mul, Div ,Sub.
figured whatever I do the first pass will be pretty bad
so pump something out and iterate on it.
-------------------------------------------
##DAY 3.1 Mon 12/02 Checkpoint:
<p>
Base instructions working.
Read and Translate method filled out with case switch statements.
Clunky implementation of Print/Out Instruction. -->
hardcoding getters in Register Class.
But program is kind of working
Next Up:Branch instruction left to do.
Overall goal:
Get something working then ican think about testing , factoryand creator patters
and using reflection and dependency Injection to decouple the Instructions
from the machine? need to think about this.
</p>
-------------------------------------------------------------------------
<p> Branch instruction done. alot of cleanup to do but basic progrqam is
functioning. WHOLE BUNCH of testcases to condisder with the:
-branch instruction --> i.e what if it poitns to non-exisitent instruction, what if theres an infininte loop
-Print Instruction --> printing non-existent instruction
-divide instruciton --> dividing by zero
-basice operation instruction --> handling positive and negative value? Integer Over/underflow?

Next Plan 
</p>>
----------------------------------------------------------
#Day4 Understanding the main point of the excercise

The question I ask is why do I need dependency injection 
and what problem does it solve. The heart of the work to be done is in the translator 
class. It has several roles it reads the instruction. It also translates the instruction.
The question to ask is "what if we increase or decrease our instruction set".
The current implementation with switch statements require us to modify it every time we 
so. But if we can "inject our dependency" ; that is our reliance on a given Instruction
to carry on with the program , then there is no need to modify.

As such my plan is to get rid of the switch statement and use reflection
to create an instance of an instruction on the fly.

~~
I have no idea what I have just done. I've attempted to use Reflection
to build instances of Instruction classes in the 
Translator class method Return Instruction.
It works but May God have mercy on my soul.
-----------------------------------------------------
##Day 5 Day of Dependency

HOw would using dependency Injection make the program better? 
what problem do I solve?
My position is this :
the question I want to ask here is 
what If I want to change the Instruction set?

As it stands the Translator class has two responsibilities.
it Reads from the file.
It translates from the file.
If I were to change the instruction set i.e a complex archtecture
with more addressable modes
then I would have to completely rehaul both the getInstruction method
and the return instruction. both rely on current implementation of Instruction class.
These methods are the providers.

My plan is to have these two methods become concrete implementations
of a Instruction Provider interface. this interface has one responsibility.
its task is to accept any number of String arguments and turn them into
instructions.

I will have another interface InstructionCreator. It has one role to serve 
instances of the Instructions and it
depends on the Provider.I only want a single instance of this.
The binding of the two happen here.
This is to represent and solve the case where if we want to change
out instruction set then all that has to be done is to edit the configuration
file.




