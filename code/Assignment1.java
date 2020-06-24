import java.io.*;

public class Assignment1{
  
  public int[][] denseMatrixMult(int[][] A, int[][] B, int size)
  {
    int[][] arr2 = new int[0][];

    return arr2;
  }
  
  public int[][] sum(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)
  {
    int[][] arr2 = new int[0][];

    return arr2;
  }
  
  public int[][] sub(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)
  {
    int[][] arr2 = new int[0][];

    return arr2;
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
