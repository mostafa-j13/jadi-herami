import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;


public class Solver {

    private final int generation;
    final private BigInteger mem[];

    /**
     * use java org.rajman.Solver [number of month(default is 30)
     * @param args command line parameter
     */
    public static void main(String[] args) {

        Solver solver;
        if(args.length==0)
            solver=new Solver();
        else
            solver=new Solver(Integer.valueOf(args[0]));

        solver.solve();

    }

    /**
     * default constructor
     */
    Solver(){
        this(30);
    }

    Solver(int generation){
        this.generation = generation;
        mem= new BigInteger[generation];
    }

    public void solve(){
        NumberFormat nf=new DecimalFormat();

        System.out.printf("%10s\t%20s\t%20s\n","month","population","benefit");

        for(int i=1;i<generation;++i)
            System.out.printf("%10d\t%20s\t%20s\n",i,nf.format(a(i)),nf.format(a(i - 6)));
    }


    private BigInteger a(int n){
        if(n<0)
            return new BigInteger("0");
        if(n==0)
            return new BigInteger("1");
        /**
         * mem[n]=a(n-1)+2*(a(n-1)-a(n-7));
         * a(n-1) number of current population
         * a(n-1)-a(n-7) number of population that not retired
         */
        if(mem[n]==null)
            mem[n]=a(n-1).add(a(n-1).subtract(a(n - 7)).multiply(new BigInteger("2")));

        return mem[n];

    }




}
