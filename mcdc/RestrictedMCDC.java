package mcdc;

public class RestrictedMCDC {
    private String[][] parsed;
    private int noCond = 0;
    private boolean[][] requirements;

    public RestrictedMCDC(String[][] p){
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
                start ++;
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
        for (int row = 1; row < parsed.length; row++) {
            if (row == major_row) {
                start = evaluateMajorRow(row, cond, start, req_row);
            } else {
                for (int col = 1; col < parsed[row].length; col++) {
                    if (!parsed[row][col].equals("N/A")){
                        requirements[req_row][start] = value;
                        start++;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public boolean[][] testRequirements() {
        String mainLogicOp = parsed[0][0];
        boolean value = true;

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
                    evaluateRows(cond-1, true_pos, value, row);
                    requirements[false_pos][cond-1] = false;
                    evaluateRows(cond-1, false_pos, value, row);
                } else {
                    break;
                }
            }
        }

        return requirements;
    }
}