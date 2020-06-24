1. To clone the project, you must have Git, and then run:
git clone https://github.com/MatthewJChang/atmcontroller.git

2.One cloned, cd into the directory

3.Make sure you have gradle installed on your machine 

4. Run ./gradlew build to build the project 

5. To run the tests, run gradle tasks using the command:
./gradlew cleanTest test 

Note: In order to not clutter the code for the tests with
unnecessary print statements, I didn't make the 
tests print out anything; I only had assert statements.
Please look at src/test/java/AtmControllerTest.java to see
what the tests are actually testing 