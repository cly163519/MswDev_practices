
//方法外面是不允许有可执行语句的，编译器会报错
public class OnionTest {

	public static void main(String[] args) {

		OnionTest test = new OnionTest();
		test.onion(4);
		test.cascade(5);
		test.factorialResult(10);
		test.fibonacciResult(10);
	}
	
	public void onion (int layers){//output is "(((()"
		System.out.print( "(" );
		if (layers > 1)  {  
			this.onion(layers-1); 
		}else {
			System.out.print( ")");
		}	
	}
	
	// 递减级联：n, n-1, ..., 1
    public void cascade(int n) {//output is "(((())))((()))(())()"
        if (n == 0) return;
        printNest(n);     // 打印 n 层
        cascade(n - 1);   // 继续打印 n-1, n-2, ...
    }

    // 打印 k 层的成对括号，如 k=3 -> "((()))"
    public void printNest(int k) {
        if (k == 0) return;//return; 的意思就是：不再继续往下执行，回到上一层调用的地方
        System.out.print("(");
        printNest(k - 1);
        System.out.print(")");
    }
    
    
    public int factorial(int n) {
    	if(n == 0 || n == 1) {return 1;}
    	return n * factorial(n - 1);
    }
    public void factorialResult(int n) {
    	System.out.println(factorial(n));
    }
    
    
    public int fibonacci(int n) {
    	if(n == 0)return 0;
    	if(n == 1)return 1;
    	return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    public void fibonacciResult(int n) {
    	for(int i = 0; i < n; i++) {
    		System.out.println(fibonacci(i) + " ");
    	}
    	
    }

}
