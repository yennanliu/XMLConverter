package boostdraftcodingassignment;
import java.util.ArrayList;
import xmlvalidatorlibrary.SimpleXmlValidator;

public class BoostDraftCodingAssignment {

    public static void main(String[] args) {
        // For this Java version implementation, 
        // you don't really need to make the build to print "Valid" or "Invalid" like what "checker.exe" does in the Take-home-coding-test descriptioin page
        // you just need to implement SimpleXmlValidator.determineXml and test it here
        
        // You can use here to test, feel free to modify/add the test cases here.
        // You can also use other ways to test if you want.
        ArrayList<TestCase> testCaseList = new ArrayList<>();
        testCaseList.add(new TestCase("<Design><Code>hello world</Code></Design>", true));//normal case
        testCaseList.add(new TestCase("<Design><Code>hello world</Code></Design></People>", false));//no closing tag for "People"
        testCaseList.add(new TestCase("<People><Design><Code>hello world</People></Code></Design>", false));// "/Code" should come before "/People" 
        testCaseList.add(new TestCase("<People age=\"1\">hello world</People>", false));//there is no closing tag for "People age="1"" and no opening tag for "/People"
        int failedCount = 0;
        for (int i = 0; i < testCaseList.size(); i++)
        {
            TestCase testCase = testCaseList.get(i);
            boolean result = SimpleXmlValidator.determineXml(testCase.input);
            String resultStr = result ? "Valid" : "Invalid";

            String mark;
            if (result == testCase.expectedOutput)
            {
                mark = "OK ";
            }
            else
            {
                mark = "NG ";
                failedCount++;
            }
            System.out.println(mark + " " + testCase.input + ": " + resultStr);
        }
        System.out.println("Result: " + (testCaseList.size() - failedCount) + "/" + testCaseList.size());
    }
    
}
