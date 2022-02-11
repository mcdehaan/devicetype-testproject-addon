package nl.monkeysquare.addon;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.android.AndroidElement;
import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.Parameter;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.v2.addons.AndroidAction;
import io.testproject.java.sdk.v2.addons.helpers.AndroidAddonHelper;
import io.testproject.java.sdk.v2.drivers.AndroidDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;
import java.io.IOException;

@Action(name = "Get device type")
public class GetDeviceTypeAndroid implements AndroidAction {

	ObjectMapper mapper = new ObjectMapper();
	BrowserstackSessionInfoProvider BrowserstackSessionInfoProvider = new BrowserstackSessionInfoProvider();

	@Parameter(description = "Browserstack Username", direction = ParameterDirection.INPUT)
	public String browserstackUsername;

	@Parameter(description = "Browserstack Password", direction = ParameterDirection.INPUT)
	public String browserstackPassword;

	@Parameter(description = "Device Type", direction = ParameterDirection.OUTPUT)
	public String deviceType;

	@Override
	public ExecutionResult execute(AndroidAddonHelper helper) throws FailureException {
		AndroidDriver<AndroidElement> driver = helper.getDriver();
		ActionReporter report = helper.getReporter();
		String sessionID = driver.getSessionId().toString();
		try {
			this.deviceType = BrowserstackSessionInfoProvider.GetBrowserstackSessionInfo(
					sessionID,
					browserstackUsername,
					browserstackPassword);
		} catch (IOException e) {
			e.printStackTrace();
		}
		report.result("Device name=" + deviceType);
		return ExecutionResult.PASSED;
	}
}
