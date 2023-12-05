package TestSuites;

import TestCases.NegativeLoginTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({NegativeLoginTest.class})
public class NegativeTests {

}
