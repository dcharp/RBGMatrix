package SystemManager;
import java.io.IOException;
import java.net.*;
import java.util.*;

import Dwhiz.controller.MatrixController;
import Dwhiz.model.Matrix;

public class SystemController {
	MatrixController mainController = new MatrixController();
	ServerSocket s ;
	
	public void mainLoop() throws IOException{
		Runnable rm = new SystemHandler(mainController);
		Thread tm = new Thread(rm);
		tm.start();
		try {
			s = new ServerSocket(8200);
			while(true){
				Socket incoming = s.accept();
				Runnable r = new MatrixClientHandler(this,incoming);
				Thread t = new Thread(r);
				t.start();
			}
		} catch (Exception e) {
			// log some errors
		}
		finally{
			s.close();
		}

	}
	public static void main(String[] args) throws IOException {
		SystemController sc = new SystemController();
		sc.mainLoop();
	}

}

class MatrixClientHandler implements Runnable{
	int matrixId;
	Socket incoming;
	SystemController controller ;
	public MatrixClientHandler(SystemController sc, Socket i){
		this.controller = sc;
		this.incoming = i; 
	}
	@Override
	public void run() {
		
		
	}
	
}

class SystemHandler implements Runnable{
	MatrixController serverMatrix;
	public SystemHandler(MatrixController m){
		this.serverMatrix = m;
	}
	@Override
	public void run() {
		
		
	}
	
}
