package TestSuites;

import TestCases.PositiveLoginTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({PositiveLoginTest.class})
public class PositiveTests {

}
