public class Base
{
   public void methodOne()
   {
     System.out.print("A");
     methodTwo();
   }

   public void methodTwo()
   {
     System.out.print("B");
   }

   public static void main(String[] args) {
        Derived b = new Derived();
        b.methodOne();
   }
}

class Derived extends Base
{
   public void methodOne()
   {
      super.methodOne();
      System.out.print("C");
   }

   public void methodTwo()
   {
     super.methodTwo();
     System.out.print("D");
   }
}