package com.o2htechnology.utils.common_utils;

import com.o2htechnology.test_core.BaseTest;

import java.util.Arrays;
import java.util.List;

/**
 * All the data needed in the test cases will be added here.
 */
public class TestData {
    public static class TestCase {
        public static class InfoMessages {
            public final static String CURRENTLY_EXECUTING_TEST = "Currently executing test case : ";
            public final static String TEST_PASSED = "Test passed! Test case : ";
            public final static String TEST_FAILED = "Test failed! Test case : ";
            public final static String TEST_SKIPPED = "Test skipped! Test case : ";
            public final static String TEST_PASSED_WITH_EXCEPTION = "Test passed with certain exceptions! Test case : ";
            public final static String TEST_SUITE_INSTANTIATED = "Test suite instantiated : ";
            public final static String TEST_SUITE_FINISHED = "Test suite completed : ";
            public final static String TEST_DESCRIPTION = "Test case description : ";
        }

        public static class FailMessages {
            public final static String USER_NOT_ON_EXPECTED_PAGE = "Not on the expected page! Expected page : ";
        }
    }

    public static class PageObjects {
        public static class LoginPage {
            public static class ExpectedURL {
                public final static List<String> LOGIN_URL = Arrays.asList(BaseTest.url, BaseTest.url + "/", BaseTest.url + "/#", BaseTest.url + "/login" + BaseTest.url + "/login/");
            }

            public static class Descriptions {
                public final static String TEST_USER_IS_ON_LOGIN_PAGE = "Test that the user is on the Login page";
                public final static String TEST_USER_LOGIN_IS_SUCCESSFUL = "Test that the user is able to login successfully";
                public final static String TEST_USER_LOGIN_IS_UNSUCCESSFUL = "Test that the user is unable to login with invalid credentials";
            }

            public static class Messages {
                public final static String INCORRECT_CREDENTIALS = "These credentials do match our records.";
            }
        }
    }
}
