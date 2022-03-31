## How to run the files

1. Make sure you have Java 11
2. Open a terminal in the root directory 
3. Compile the two packages:
    - javac mcdc/*.java 
    - javac subjects/*.java 
4. If TestTriangle.java and/or TestBMICalculator.java is not empty, delete the contents
5. Compile the testing class:
    - javac RandomlyTestTriangle.java
    - javac RandomlyTestBMI.java 
6. Run the testing class:
    - java RandomlyTestTriangle
    - java RandomlyTestBMI

## Choosing between Correlated MCDC and Restricted MCDC 

1. Go to RandomlyTestTriangle.java or RandomlyTestBMI.java
2. Uncomment your preferred MCDC in "public static void main(..."
3. Comment out the other MCDC 
4. Recompile the class 
5. Run the class

## Note on video

I don't have a microphone, so the video is silent.
