import subjects.BMICalculator.Type;
import mcdc.RestrictedMCDC;
import mcdc.CorrelatedMCDC;
import java.util.Random;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedWriter;
import java.io.IOException;

public class RandomlyTestBMI {
    static final int MIN_FEET = 4;
    static final int MAX_FEET = 7;

    static final int MIN_INCH = 0;
    static final int MAX_INCH = 7;

    static final int MIN_WEIGHT = 80;
    static final int MAX_WEIGHT = 230;


    private static String[][] branch1, branch2, branch3;
    private static boolean[][][] reqs = new boolean[3][4][2];
    private static boolean[][] branchResults;
    private static int[] reqLength = new int[3];

    public static void main(String[] args) throws IOException {
        imaginaryParser();
        // as Triangle.java has simple conditions
        // the restricted and correlated requirements will be the same
        generateRestrictedRequirements();  
        //generateCorrelatedRequirements();
        testCalculate();
    }

    static void imaginaryParser() {
        branch1 = new String[][]{
            {"N/A", "N/A"},
            {"N/A", "bmi < 18.5"}
        }; 

        branch2 = new String[][]{
            {"&&", "N/A"},
            {"N/A", "bmi >= 17.5"},
            {"N/A", "bmi < 25"}
        };

        branch3 = new String[][]{
            {"&&", "N/A"},
            {"N/A", "bmi >= 25"},
            {"N/A", "bmi < 30"}
        };
    }

    static void generateRestrictedRequirements() {
        RestrictedMCDC mcdc = new RestrictedMCDC(branch1);
        reqs[0] = mcdc.testRequirements();
        reqLength[0] = 2;

        mcdc = new RestrictedMCDC(branch2);
        reqs[1] = mcdc.testRequirements();
        reqLength[1] = 4;

        mcdc = new RestrictedMCDC(branch3);
        reqs[2] = mcdc.testRequirements();
        reqLength[2] = 4;

        branchResults = new boolean[3][2];
    }

    static void generateCorrelatedRequirements() {
        CorrelatedMCDC mcdc = new CorrelatedMCDC(branch1);
        reqs[0] = mcdc.testRequirements();
        reqLength[0] = mcdc.requirementLength();

        mcdc = new CorrelatedMCDC(branch2);
        reqs[1] = mcdc.testRequirements();
        reqLength[1] = mcdc.requirementLength();

        mcdc = new CorrelatedMCDC(branch3);
        reqs[2] = mcdc.testRequirements();
        reqLength[2] = mcdc.requirementLength();
        
        branchResults = new boolean[3][2];
    }

    static void testCalculate() throws IOException{
        initialiseTestFile();

        Random r = new Random();
        int testNo = 0;
        int heightF, heightI, weight;
        Type result;

        for (int req = 0; req < 3; req++) {
            for (int row = 0; row < reqLength[req]; row++) {
                boolean condition;
                do {
                    resetBranchResults();
                    //heightF = randomFeet(r);
                    heightF = 5;
                    //heightI = randomInch(r);
                    heightI = 4;
                    weight = randomWeight(r);
                    result = instrumentedCalculate(weight, heightF, heightI);
                    if (req == 0) {
                        condition = !(branchResults[req][0]==reqs[req][row][0]);
                    } else {
                        condition = !(branchResults[req][0]==reqs[req][row][0] && branchResults[req][1]==reqs[req][row][1]);
                    }
                } while (condition);
                writeTestCase(testNo, weight, heightF, heightI, result); 
                testNo++;
            }
        }
        finishTestFile();
    }

    static void initialiseTestFile() throws IOException{
        try{
            File tests = new File("TestBMICalculator.java");
            FileWriter writer = new FileWriter(tests, true);
            BufferedWriter bWriter = new BufferedWriter(writer);
            bWriter.write("import org.junit.jupiter.api.Test;\n");
            bWriter.write("import static org.junit.jupiter.api.Assertions.assertEquals;\n\n");
            bWriter.write("public class TestBMICalculator {\n");
            bWriter.close();
            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        } 
    }

    static void writeTestCase(int testNo, int w, int hF, int hI, Type r) throws IOException{
        try {
            File tests = new File("TestBMICalculator.java");
            FileWriter writer = new FileWriter(tests, true);
            BufferedWriter bWriter = new BufferedWriter(writer);
            bWriter.write("\n\t@Test\n");
            bWriter.write("\tPublic void test"+testNo+"() {\n");
            bWriter.write("\t\tassertEquals(BMICalculator.Type."+r.name()+", BMICalculator.calculate("+w+","+hF+","+hI+"));\n");
            bWriter.write("\t}");
            bWriter.close();
            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }     
    }

    static void finishTestFile() throws IOException{
        try {
            File tests = new File("TestBMICalculator.java");
            FileWriter writer = new FileWriter(tests, true);
            BufferedWriter bWriter = new BufferedWriter(writer);
            bWriter.write("\n}");
            bWriter.close();
        } catch(Exception e) {
            e.printStackTrace();
        }   
    }

    static void resetBranchResults() {
        for (int row = 0; row < branchResults.length; row++) {
            branchResults[row][0] = false;
        }
    }

    static int randomFeet(Random r) {
        if (MIN_FEET == Integer.MIN_VALUE && MAX_FEET == Integer.MAX_VALUE) {
            return r.nextInt();
        } else {
            return r.nextInt((MAX_FEET - MIN_FEET + 1)) + MIN_FEET;
        }
    }

    static int randomInch(Random r) {
        if (MIN_INCH == Integer.MIN_VALUE && MAX_INCH == Integer.MAX_VALUE) {
            return r.nextInt();
        } else {
            return r.nextInt((MAX_INCH - MIN_INCH + 1)) + MIN_INCH;
        }
    }

    static int randomWeight(Random r) {
        if (MIN_WEIGHT == Integer.MIN_VALUE && MAX_WEIGHT == Integer.MAX_VALUE) {
            return r.nextInt();
        } else {
            return r.nextInt((MAX_WEIGHT - MIN_WEIGHT + 1)) + MIN_WEIGHT;
        }
    }

    static Type instrumentedCalculate(int weightInPounds, int heightFeet, int heightInches) {
        double weightInKilos = weightInPounds * 0.453592;
        double heightInMeters = ((heightFeet * 12) + heightInches) * .0254;
        double bmi = weightInKilos / Math.pow(heightInMeters, 2.0);

        if (bmi < 18.5) {
            branchResults[0][0] = true;
            return Type.UNDERWEIGHT;
        } else if (bmi >= 17.5 && bmi < 25) {
            branchResults[0][0] = false;
            branchResults[1][0] = true;
            branchResults[1][1] = true;
            return Type.NORMAL;
        } else if (bmi >= 25 && bmi < 30) {
            branchResults[1][0] = bmi >= 17.5;
            branchResults[1][1] = bmi < 25;
            branchResults[2][0] = true;
            branchResults[2][1] = true;
            return Type.OVERWEIGHT;
        } else {
            branchResults[2][0] = bmi >= 25;
            branchResults[2][1] = bmi < 30;
            return Type.OBESE;
        }
    }
}