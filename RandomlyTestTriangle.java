/*
*  Writing to file from: https://stackoverflow.com/questions/9961292/write-to-text-file-without-overwriting-in-java
*  Parts of this file are inspired from RandomlyTestTriangle.java from COM3529 lectures, Spring 2022, University of Sheffield
*/
import subjects.Triangle.Type;
import mcdc.RestrictedMCDC;
import mcdc.CorrelatedMCDC;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedWriter;
import java.io.IOException;

public class RandomlyTestTriangle {
    static final int MIN_INT = 0;
    static final int MAX_INT = 10;
    private static String[][] branch1, branch2, branch3, branch4, branch5, branch6, branch7;
    private static boolean[][][] reqs = new boolean[7][2][1];
    private static boolean[][] branchResults;

    public static void main(String[] args) throws IOException {
        imaginaryParser();
        // as Triangle.java has simple conditions
        // the restricted and correlated requirements will be the same
        generateRestrictedRequirements();  
        //generateCorrelatedRequirements();
        testClassify();
    }

    static void imaginaryParser() {
        branch1 = new String[][]{
            {"N/A", "N/A"},
            {"N/A", "side1 > side2"}
        }; 

        branch2 = new String[][]{
            {"N/A", "N/A"},
            {"N/A", "side1 > side3"}
        };

        branch3 = new String[][]{
            {"N/A", "N/A"},
            {"N/A", "side2 > side3"}
        };

        branch4 = new String[][]{
            {"N/A", "N/A"},
            {"N/A", "side1 + side2 <= side3"}
        };

        branch5 = new String[][]{
            {"N/A", "N/A"},
            {"N/A", "side1 == side2"}
        };

        branch6 = new String[][]{
            {"N/A", "N/A"},
            {"N/A", "side2 == side3"}
        };

        branch7 = new String[][]{
            {"N/A", "N/A"},
            {"N/A", "side2 == side3"}
        };
    }

    static void generateRestrictedRequirements() {
        RestrictedMCDC mcdc = new RestrictedMCDC(branch1);
        reqs[0] = mcdc.testRequirements();

        mcdc = new RestrictedMCDC(branch2);
        reqs[1] = mcdc.testRequirements();

        mcdc = new RestrictedMCDC(branch3);
        reqs[2] = mcdc.testRequirements();

        mcdc = new RestrictedMCDC(branch4);
        reqs[3] = mcdc.testRequirements();

        mcdc = new RestrictedMCDC(branch5);
        reqs[4] = mcdc.testRequirements();

        mcdc = new RestrictedMCDC(branch6);
        reqs[5] = mcdc.testRequirements();

        mcdc = new RestrictedMCDC(branch7);
        reqs[6] = mcdc.testRequirements();

        branchResults = new boolean[7][1];
    }

    static void generateCorrelatedRequirements() {
        CorrelatedMCDC mcdc = new CorrelatedMCDC(branch1);
        reqs[0] = mcdc.testRequirements();

        mcdc = new CorrelatedMCDC(branch2);
        reqs[1] = mcdc.testRequirements();

        mcdc = new CorrelatedMCDC(branch3);
        reqs[2] = mcdc.testRequirements();

        mcdc = new CorrelatedMCDC(branch4);
        reqs[3] = mcdc.testRequirements();

        mcdc = new CorrelatedMCDC(branch5);
        reqs[4] = mcdc.testRequirements();

        mcdc = new CorrelatedMCDC(branch6);
        reqs[5] = mcdc.testRequirements();

        mcdc = new CorrelatedMCDC(branch7);
        reqs[6] = mcdc.testRequirements();
        
        branchResults = new boolean[7][1];
    }

    static void testClassify() throws IOException{
        initialiseTestFile();

        Random r = new Random();
        int testNo = 0;
        int side1, side2, side3;
        Type result;

        for (int req = 0; req < 7; req++) {
            for (int row = 0; row < reqs[req].length; row++) {
                do {
                    resetBranchResults();
                    side1 = randomInt(r);
                    side2 = randomInt(r);
                    side3 = randomInt(r);
                    result = instrumentedClassify(side1, side2, side3);
                } while (branchResults[req][0]!=reqs[req][row][0]);
                writeTestCase(testNo, side1, side2, side3, result); 
                testNo++;
            }
        }
        finishTestFile();
    }

    static void initialiseTestFile() throws IOException{
        try{
            File tests = new File("TestTriangle.java");
            FileWriter writer = new FileWriter(tests, true);
            BufferedWriter bWriter = new BufferedWriter(writer);
            bWriter.write("import org.junit.jupiter.api.Test;\n");
            bWriter.write("import static org.junit.jupiter.api.Assertions.assertEquals;\n\n");
            bWriter.write("public class TestTriangle {\n");
            bWriter.close();
            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        } 
    }

    static void writeTestCase(int testNo, int s1, int s2, int s3, Type r) throws IOException{
        try {
            File tests = new File("TestTriangle.java");
            FileWriter writer = new FileWriter(tests, true);
            BufferedWriter bWriter = new BufferedWriter(writer);
            bWriter.write("\n\t@Test\n");
            bWriter.write("\tPublic void test"+testNo+"() {\n");
            bWriter.write("\t\tassertEquals(Triangle.Type."+r.name()+", Triangle.classify("+s1+","+s2+","+s3+"));\n");
            bWriter.write("\t}");
            bWriter.close();
            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }     
    }

    static void finishTestFile() throws IOException{
        try {
            File tests = new File("TestTriangle.java");
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

    static int randomInt(Random r) {
        if (MIN_INT == Integer.MIN_VALUE && MAX_INT == Integer.MAX_VALUE) {
            return r.nextInt();
        } else {
            return r.nextInt((MAX_INT - MIN_INT + 1)) + MIN_INT;
        }
    }

    static Type instrumentedClassify(int side1, int side2, int side3) {
        Type type;
        boolean value;
        if (side1 > side2) {
            value = side1 > side2;
            branchResults[0][0] = true;
            int temp = side1;
            side1 = side2;
            side2 = temp;
        } else {
            branchResults[0][0] = false;
        }

        if (side1 > side3) {
            branchResults[1][0] = true;
            int temp = side1;
            side1 = side3;
            side3 = temp;
        } else {
            branchResults[1][0] = false;
        }

        if (side2 > side3) {
            branchResults[2][0] = true;
            int temp = side2;
            side2 = side3;
            side3 = temp;
        } else {
            branchResults[2][0] = false;
        }

        if (side1 + side2 <= side3) {
            branchResults[3][0] = true;
            type = Type.INVALID;
        } else {
            branchResults[3][0] = false;
            type = Type.SCALENE;
            if (side1 == side2) {
                branchResults[4][0] = true;
                if (side2 == side3) {
                    branchResults[5][0] = true;
                    type = Type.EQUILATERAL;
                } else {
                    branchResults[5][0] = false;
                }
            } else {
                branchResults[4][0] = false;
                if (side2 == side3) {
                    branchResults[6][0] = true;
                    type = Type.ISOSCELES;
                } else {
                    branchResults[6][0] = false;
                }
            }
        }
        return type;
    }
}