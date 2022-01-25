package com.studentapp.cucumber;

/*
 * Created By: Hiren Patel
 * Project Name: StudentApp-Serenity-Week-21
 */

import com.studentapp.testbase.TestBase;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/java/resources/feature/")

public class CucumberRunner extends TestBase {


}
