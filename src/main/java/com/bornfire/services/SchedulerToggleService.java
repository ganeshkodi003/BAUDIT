package com.bornfire.services;

import org.springframework.stereotype.Service;

@Service
public class SchedulerToggleService {
	 private volatile boolean isSchedulerEnabled = false;

	    public boolean isSchedulerEnabled() {
	        return isSchedulerEnabled;
	    }

	    public void setSchedulerEnabled(boolean enabled) {
	        this.isSchedulerEnabled = enabled;
	   }
}
