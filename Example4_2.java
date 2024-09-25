public class Example4_2 {
    // Program 2:
    public static void main(String[] args) {
        A a1 = new A();
        Object a2 = new A();
        System.out.println(a1.equals(a2)); // true or false?
    }
}


class B {

    public boolean equals(Object b) {
        System.out.println("Hi");
        return true;
    }
}

class A extends B{
    int x;

        
    public boolean equals(A a) {
        return this.x == a.x;
    }

    
}
