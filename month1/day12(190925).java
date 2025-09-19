//3484 Design Spreadsheet
//ez igot 125 beats 42% 

class Spreadsheet {
    List<Row> table = new ArrayList<>();

    class Row {
        int col[] = new int[26];
    }

    public Spreadsheet(int rows) {
        for (int i = 0; i < rows; i++) {
            table.add(new Row());
        }
    }

    public void setCell(String cell, int value) {
        int row = Integer.parseInt(cell.substring(1));   
        int col = cell.charAt(0) - 'A';                  
        table.get(row - 1).col[col] = value;
    }

    public void resetCell(String cell) {
        this.setCell(cell, 0);
    }

    public int getValue(String formula) {
        // formula looks like "=X+Y"
        formula = formula.substring(1);          
        String[] parts = formula.split("\\+");   // split into X and Y

        int sum = 0;
        for (String part : parts) {
            if (Character.isDigit(part.charAt(0))) {
            
                sum += Integer.parseInt(part);
            } else {
            
                int row = Integer.parseInt(part.substring(1));
                int col = part.charAt(0) - 'A';
                sum += table.get(row - 1).col[col];
            }
        }
        return sum;
    }
}
