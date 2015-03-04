package com.cmu.project;


import java.math.BigInteger;
import java.util.HashMap;

public class Encrypt {
	public HashMap<String, Integer> mapAlph = new HashMap<String, Integer>();
	static BigInteger localBigInt = new BigInteger("8271997208960872478735181815578166723519929177896558845922250595511921395049126920528021164569045773");

//	public static void main(String[] args) {
//		String str1 = "URYEXYBJB";
//		String mesg = getDecriptedMsg(getArray(str1), getKey("306063896731552281713201727176392168770237379582172677299123272033941091616817696059536783089054693601"));
//		System.out.println("Message" + mesg);
//	}
	
	//get key from local x and the product of xy from server
	public Integer getKey(String bigInt){
		BigInteger serverBigInt = new BigInteger(bigInt);
		BigInteger keyY = serverBigInt.divide(localBigInt);
		BigInteger keyI = new BigInteger("1").add(keyY.mod(new BigInteger("25")));
		int key_I = keyI.intValue();
		return key_I;
	}

	//Use the key to do reverse Caesarify
	public String getDecriptedMsg(String str, int key) {
		char a = 'A';
		char z = 'Z';
		StringBuffer strBuf = new StringBuffer();
		for(int i = 0; i < str.length(); i++) {
			int asciVal = (int)str.charAt(i);
			int val = asciVal - key;
			if (val >= (int)a) {
				strBuf.append((char)val + "");
			} else {
				strBuf.append((char)((int)z -((int)a-val) + 1) + "");
			}
		}

		return strBuf.toString();
	}

	//ger the array from matrix
	public String getArray(String str1) {
		double n = Math.sqrt(str1.length());
		char[][] multD = new char[(int)n][(int)n];
		int j = 0;
			for (int i=0;i<n;i++) {
				for (int k=0;k<n;k++) {
					multD[i][k] = str1.charAt(j);
					j++;
				}
			}
		return spiralOrder(multD);
	}
	
	//Spirally read the matrix
	public String spiralOrder(char[][] matrix) {
        StringBuffer result = new StringBuffer();
 
        if(matrix == null || matrix.length == 0) return result.toString();
 
        int m = matrix.length;
        int n = matrix[0].length;
 
        int x=0; 
        int y=0;
 
        while(m>0 && n>0){
            if(m==1){
                for(int i=0; i<n; i++){
                    result.append(matrix[x][y++]);
                }
                break;
            }else if(n==1){
                for(int i=0; i<m; i++){
                    result.append(matrix[x++][y]);
                }
                break;
            }

            for(int i=0;i<n-1;i++){
                result.append(matrix[x][y++]);
            }

            for(int i=0;i<m-1;i++){
                result.append(matrix[x++][y]);
            }

            for(int i=0;i<n-1;i++){
                result.append(matrix[x][y--]);
            }

            for(int i=0;i<m-1;i++){
                result.append(matrix[x--][y]);
            }
            x++;
            y++;
            m=m-2;
            n=n-2;
        }
 
        return result.toString();
    }
}


