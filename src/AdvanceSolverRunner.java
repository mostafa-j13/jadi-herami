import com.sanityinc.jargs.CmdLineParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mostafa on 12/27/14.
 */
public class AdvanceSolverRunner extends CmdLineParser {
    public static void main(String args[]){

        AdvanceSolverRunner parser = new AdvanceSolverRunner();
        CmdLineParser.Option<Integer> month = parser.addHelp(
                parser.addIntegerOption('m', "month"),
                "number of month default 30");
        CmdLineParser.Option<Integer> branch = parser.addHelp(
                parser.addIntegerOption('n', "new"),
                "number of user add every month");
        CmdLineParser.Option<Integer> retired = parser.addHelp(
                parser.addIntegerOption('r', "retired"),
                "number of month that someone add user to system ");
        CmdLineParser.Option<Integer> benefitPeriod = parser.addHelp(
                parser.addIntegerOption('b', "benefit-period"),
                "number of month that some one  get benefit");
        CmdLineParser.Option<Integer> benefitCount = parser.addHelp(
                parser.addIntegerOption('c', "benefit-count"),
                "number of user that some one  have to add before get benefit");
        CmdLineParser.Option<Integer> initialPension = parser.addHelp(
                parser.addIntegerOption('i', "init"),
                "number of user at zero time");

        CmdLineParser.Option<Boolean> help = parser.addHelp(
                parser.addBooleanOption('h', "help"),
                "Show this help message");


        try {
            parser.parse(args);
        } catch ( CmdLineParser.OptionException e ) {
            System.err.println(e.getMessage());
            parser.printUsage();
            System.exit(2);
        }

        if ( parser.getOptionValue(help,false) ) {
            parser.printUsage();
            System.exit(0);
        }


        AdvancedSolver advancedSolver=new AdvancedSolver(
                parser.getOptionValue(month,30),
                parser.getOptionValue(branch,2),
                parser.getOptionValue(retired,6),
                parser.getOptionValue(benefitPeriod, 4),
                parser.getOptionValue(initialPension,1),
                parser.getOptionValue(benefitCount,6));
        advancedSolver.solve();
    }

    List<String> optionHelpStrings = new ArrayList<String>();
    public <T> Option<T> addHelp(Option<T> option, String helpString) {
        optionHelpStrings.add(" -" + option.shortForm() + "/--" + option.longForm() + ": " + helpString);
        return option;
    }
    public void printUsage() {
        System.err.println("usage: prog [options]");
        for (String help : optionHelpStrings) {
            System.err.println(help);
        }
    }
}
