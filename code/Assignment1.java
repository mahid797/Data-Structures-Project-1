//EECS 2011 - SU2020
//By: Adrian Angara 216090532

import java.io.*;

public class Assignment1{
  
  public int[][] denseMatrixMult(int[][] A, int[][] B, int size)
  {
    //Pre-Condition
    int[][] arr = initMatrix(size); // O(h(n)) = n^2
    int half_n = size/2;

    // Base Case when n=1
    if (size == 1) {
      arr[0][0] = A[0][0]*B[0][0];
      return arr;
    }

    // Recursive Case when n>1; f(n) = 7F(n/2);
    //Define mn; nE[0,6];
    //m0 = [A00 + A11][B00 + B11];
    int[][] m0 = denseMatrixMult(sum(A, A, 0, 0, half_n, half_n, half_n),
                                  sum(B, B, 0, 0, half_n, half_n, half_n),  half_n);  // F(n/2);
    //m1 = [A10 + A11][B00];
    int[][] m1 = denseMatrixMult(sum(A, A, half_n, 0, half_n, half_n, half_n),
                                  sum(arr, B, 0,0,0,0, half_n),  half_n); //F(n/2);
    //m2 = [A00][B01 - B11];
    int[][] m2 = denseMatrixMult(sum(arr, A, 0,0,0,0, half_n),
                                  sub(B, B, 0, half_n, half_n, half_n, half_n),  half_n); //F(n/2);
    //m3 = [A11][B10 - B00];
    int[][] m3 = denseMatrixMult(sum(arr, A, 0,0, half_n, half_n, half_n),
                                  sub(B, B, half_n, 0, 0, 0, half_n),  half_n); // F(n/2);
    //m4 = [A00 + A01][B11];
    int[][] m4 = denseMatrixMult(sum(A, A, 0, 0, 0, half_n, half_n),
                                  sum(arr, B, 0,0, half_n, half_n, half_n),  half_n); // F(n/2);
    //m5 = [A10 - A00][B00 + B01];
    int[][] m5 = denseMatrixMult(sub(A, A, half_n, 0, 0, 0, half_n),
                                  sum(B, B, 0, 0, 0, half_n, half_n),  half_n); //F(n/2);
    //m6 = [A01 - A11][B10 + B11];
    int[][] m6 = denseMatrixMult(sub(A, A, 0, half_n, half_n, half_n, half_n),
                                  sum(B, B, half_n, 0, half_n, half_n, half_n),  half_n);//F(n/2);

    // Define cnm; n,mE{0,1};
    // c00 = m0 + m3 - m4 + m6;
    int[][] c00_op1 = sum(m0, m3, 0,0,0,0, half_n);
    int[][] c00_op2 = sub(c00_op1, m4, 0, 0, 0, 0, half_n);
    int[][] c00 = sum(c00_op2, m6, 0, 0, 0, 0, half_n);

    // c01 = m2 + m4;
    int[][] c01 = sum(m2, m4, 0,0,0,0, half_n);

    // c10 = m1 + m3;
    int[][] c10 = sum(m1, m3, 0,0,0,0, half_n);

    // c11 = m0 - m1 + m2 + m5
    int[][] c11_op1 = sub(m0, m1, 0,0,0,0, half_n);
    int[][] c11_op2 = sum(c11_op1, m2, 0, 0, 0, 0, half_n);
    int[][] c11 = sum(c11_op2, m5, 0, 0, 0, 0, half_n);

    //Reconstruct arr using the cnm variables // O(g(n)) = n^2
    for(int i = 0; i < size; i++) {
      for(int j = 0; j < size; j++) {

        //Different cases:
        // c00 <--> (i < half_n && j < half_n)
        if(i < half_n && j < half_n) {
          arr[i][j] = c00[i][j];
        }

        // c01 <--> (i  half_n && j >= half_n)
        if(i < half_n && j >= half_n) {
          arr[i][j] = c01[i][j - half_n];
        }

        // c10 <--> (i >= half_n && j < half_n)
        if(i >= half_n && j < half_n) {
          arr[i][j] = c10[i - half_n][j];
        }

        // c11 <--> (i >= half_n && j >= half_n)
        if(i >= half_n && j >= half_n) {
          arr[i][j] = c11[i - half_n][j - half_n];
        }

      }
    }


    return arr;
  }
  
  public int[][] sum(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)
  {
    int[][] arr = new int[n][n];

    int m2_row = x2;
    int m2_column = y2;

    int m1_row = x1;
    int m1_column = y1;

    for(int i = 0; i < n; i++) {
      m2_column = y2;
      m1_column = y1;

      for(int j = 0; j < n; j++) {
        arr[i][j] = A[m1_row][m1_column] + B[m2_row][m2_column];

        m2_column++;
        m1_column++;
      }
      m2_row++;
      m1_row++;
    }

    return arr;
  }
  
  public int[][] sub(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)
  {
    int[][] arr = new int[n][n];

    int m2_row = x2;
    int m2_column = y2;

    int m1_row = x1;
    int m1_column = y1;

    for(int i = 0; i < n; i++) {
      m2_column = y2;
      m1_column = y1;

      for(int j = 0; j < n; j++) {
        arr[i][j] = A[m1_row][m1_column] - B[m2_row][m2_column];

        m2_column++;
        m1_column++;
      }
      m2_row++;
      m1_row++;
    }

    return arr;
  }
  
  
  public int[][] initMatrix(int n)
  {
    int[][] arr = new int[n][n];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        arr[i][j] = 0;
      }
    }

    return arr;
  }
  
  public void printMatrix(int n, int[][] A)
  {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(A[i][j] + " ");
      }
      System.out.println();
    }
  }
  
  public int[][] readMatrix(String filename, int n) throws Exception
  {
    int[][] arr = new int[n][n];

    File file = new File(filename);

    BufferedReader br = new BufferedReader(new FileReader(file));

    String st;

    int i = 0;
    while ((st = br.readLine()) != null) {
      String[] strArr = st.split(" ", n);

      for(int j = 0; j < n; j ++) {
        arr[i][j] = Integer.parseInt(strArr[j]);
      }
      i++;
    }

    return arr;
  }
  
}
