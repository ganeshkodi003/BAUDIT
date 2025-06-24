package com.bornfire.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

@Service
public class LogService {
		private final List<String> logs = new ArrayList<>();

	    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	    public void add(String log) {
	        String timestamp = dateFormat.format(new Date());
	        logs.add("[" + timestamp + "] " + log);
	        // Optional: To limit memory usage, keep only last 100 logs
	        if (logs.size() > 100) {
	            logs.remove(0);
	        }
	    }

	    public List<String> getLogs() {
	        return new ArrayList<>(logs); // return a copy to avoid concurrent modification
	    }

	    public void clearLogs() {
	        logs.clear();
	    }

		
}
