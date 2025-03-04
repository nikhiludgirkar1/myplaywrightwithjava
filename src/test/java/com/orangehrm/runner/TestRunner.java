package com.orangehrm.runner;


import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("feature/AddEmployee.feature")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.orangehrm.hooks, com.orangehrm.definitions")
@ConfigurationParameter(key = SNIPPET_TYPE_PROPERTY_NAME, value = "camelcase")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@addemployee-test")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "html:target/cucumber.html," + "pretty,json:target/cucumber.json,summary,junit:target/cucumber.xml")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@addemployee-test")
@ConfigurationParameter(key = SNIPPET_TYPE_PROPERTY_NAME, value = "camelcase")
@ConfigurationParameter(key = PLUGIN_PUBLISH_TOKEN_PROPERTY_NAME, value = "a62e34da-b425-4b40-89ff-779a6bf593a8")
@ConfigurationParameter(key = ANSI_COLORS_DISABLED_PROPERTY_NAME, value = "true")
@ConfigurationParameter(key = PLUGIN_PUBLISH_ENABLED_PROPERTY_NAME, value = "false")
@ConfigurationParameter(key = PLUGIN_PUBLISH_QUIET_PROPERTY_NAME, value = "true")
@ConfigurationParameter(key = EXECUTION_DRY_RUN_PROPERTY_NAME, value = "false")
@ConfigurationParameter(key = JUNIT_PLATFORM_NAMING_STRATEGY_PROPERTY_NAME, value = "long")
public class TestRunner {

}
