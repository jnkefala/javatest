package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RunTests {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(Tests.class);
		if(result.getFailureCount()>0){
			System.out.println((result.getRunCount()-result.getFailureCount())+"/"+result.getRunCount()+" successfull tests. Failures:\n");
			for (Failure failure : result.getFailures())
				System.err.println(failure.toString());
		}else
			System.out.print("All "+result.getRunCount()+" tests were successfull.");

	}

}
