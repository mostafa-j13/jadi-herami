import com.sanityinc.jargs.CmdLineParser;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by mostafa on 12/27/14.
 */
public class AdvancedSolver {


    private static final BigInteger ZERO = BigInteger.valueOf(0);
    private final int generation;
    private final BigInteger branch;
    private final int retiredPeriod;
    private final int benefitPeriod;
    private final BigInteger initialNumber;
    private final BigInteger[] mem;
    private int firstStep=2;



    AdvancedSolver(){
        this(30,2,6,4,1,6);
    }


    public AdvancedSolver(int generation,int branch,int retiredPeriod,int benefitPeriod,int initialNumber,int numOfChidToBenefit) {

        this.generation = generation;
        this.branch =  BigInteger.valueOf(branch);
        this.retiredPeriod = retiredPeriod;
        this.benefitPeriod = benefitPeriod;
        this.initialNumber = BigInteger.valueOf(initialNumber);
        mem= new BigInteger[generation];
        //noinspection LoopStatementThatDoesntLoop
        for(int i=1;;++i){
            if(a(i).longValue()>numOfChidToBenefit) {
                firstStep = i;
                break;
            }
        }


    }

    public void solve(){
        NumberFormat nf=new DecimalFormat();

        System.out.printf("%10s\t%20s\t%20s\n","month","population","benefit");

        for(int i=1;i<generation;++i)
            System.out.printf("%10d\t%20s\t%20s\n",i,nf.format(a(i)),nf.format(benefit(i)));
    }

    BigInteger a(int n){
        if(n<0)
            return ZERO;
        if(n==0)
            return initialNumber;

        if(mem[n]==null){
            mem[n]=a(n-1).add(a(n-1).subtract(a(n - retiredPeriod-1)).multiply(branch));
        }
        return mem[n];
    }

    BigInteger benefit(int n){
        return a(n-benefitPeriod-firstStep);
    }
}
