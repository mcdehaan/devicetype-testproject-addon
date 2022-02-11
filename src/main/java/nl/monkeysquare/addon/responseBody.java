package nl.monkeysquare.addon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class responseBody {
    AutomationSession automation_session;

    public AutomationSession getautomation_session() {
        return automation_session;
    }

    public void setautomation_session(AutomationSession automation_session) {
        this.automation_session = automation_session;
    }
}
