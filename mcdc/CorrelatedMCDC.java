package mcdc;
import java.util.Random;

public class CorrelatedMCDC {
    private String[][] parsed;
    private int noCond = 0;
    private boolean[][] requirements;
    private int[][] completion;

    public CorrelatedMCDC(String[][] p){
        parsed = p;
        createRequirementsMatrix();
    }

    public void createRequirementsMatrix() {
        for (int row = 1; row < parsed.length; row++) {
            for (int col = 1; col < parsed[row].length; col++) {
                if (!parsed[row][col].equals("N/A")) {
                    noCond++;
                }      
            }
        } 
        requirements = new boolean[noCond*2][noCond];
        completion = new int[2][noCond];
    }

    public int evaluateMajorRow(int row, int cond, int start, int req_row) {
        String logic_op = parsed[row][0];
        boolean value = true;

        if (logic_op.equals("||")) {
            value = false;
        } else if (logic_op.equals("N/A")) {
             return start+1;
        }
           
        for (int col = 1; col < parsed[row].length; col++) {
            if (start == cond) {
                start += 1;
            } else if (!parsed[row][col].equals("N/A")) {
                requirements[req_row][start] = value;
                start++;
            } else if (parsed[row][col].equals("N/A")) {
                break;
            }
        }
        return start;
    }

    public void evaluateRows(int cond, int req_row, boolean value, int major_row) {
        int start = 0;
        Random random = new Random();
        for (int row = 1; row < parsed.length; row++) {
            if (row == major_row) {
                start = evaluateMajorRow(row, cond, start, req_row);
            } else {
                String logic_op = parsed[row][0];
                if ((logic_op.equals("&&") && value==true) || 
                    (logic_op.equals("||") && value==false)) {
                        for (int col = 1; col < parsed[row].length; col++) {
                            if (!parsed[row][col].equals("N/A")) {
                                requirements[req_row][start] = value;
                                if (value) {
                                    completion[1][start] = 1;
                                } else {
                                    completion[0][start] = 1;
                                }
                                start++;
                            } 
                        }
                    } else {
                        requirements[req_row][start] = value;
                        start++;
                        for (int col = 2; col < parsed[row].length; col++) {
                            if (!parsed[row][col].equals("N/A")) {
                                boolean v = random.nextBoolean();
                                requirements[req_row][start] = v;
                                if (v) {
                                    completion[1][start] = 1;
                                } else {
                                    completion[0][start] = 1;
                                }
                            }
                        }
                    }
            }
        }
    }

    public boolean isCompleted() {
        for (int row = 0; row < completion.length; row++) {
            for (int col = 0; col < completion[row].length; col++) {
                if (completion[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean[][] testRequirements() {
        String mainLogicOp = parsed[0][0];
        boolean value = true;
        int req_rows = 0;

        if (mainLogicOp.equals("||")) {
            value = false;
        }

        int cond = 1;
        for (int row = 1; row < parsed.length; row++) {
            for (int col = 1; col < parsed[row].length; col++) {
                if (!parsed[row][col].equals("N/A")) {
                    int false_pos = (cond-1)*2;
                    int true_pos = false_pos + 1;

                    requirements[true_pos][cond-1] = true;
                    completion[1][cond-1] = 1;
                    requirements[false_pos][cond-1] = false;
                    completion[0][cond-1] = 1;

                    evaluateRows(cond-1, true_pos, value, row);
                    evaluateRows(cond-1, false_pos, value, row);

                    req_rows+=2;

                    if (isCompleted()) {
                        break;
                    }
                } else {
                    break;
                }
            }
            if (isCompleted()) {
                break;
            }
        }

        boolean[][] trimmed_req = new boolean[req_rows][noCond];

        for (int row = 0; row < req_rows; row++) {
            for (int col = 0; col < requirements[row].length; col++) {
                trimmed_req[row][col] = requirements[row][col];
            }
        }
        return trimmed_req;
    }

}