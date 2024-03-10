package mainPackage;

import java.io.IOException;

public class Allure_util {
	
	public  void runAllureCommand(String command) throws IOException, InterruptedException {
		ProcessBuilder pb;
		if(System.getProperty("os.name").toLowerCase().contains("win")) {
			pb= new ProcessBuilder("cmd.exe","/c",command);
		} else {
			pb= new ProcessBuilder("/bin/bash","-c",command);
		}
		Process p = pb.start();
		p.waitFor();
		
	}
	public  void runAllureReportCommand(String command) throws IOException {
		ProcessBuilder pb;
		if(System.getProperty("os.name").toLowerCase().contains("win")) {
			pb= new ProcessBuilder("cmd.exe","/c",command);
		} else {
			pb= new ProcessBuilder("/bin/bash","-c",command);
		}
		Process p = pb.start();
		
	}

}
