
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

