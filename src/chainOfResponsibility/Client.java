package chainOfResponsibility;

import java.util.Random;

import chainOfResponsibility.handler.ProcessHandlerFacade;
import chainOfResponsibility.handler.Student;

class Client {

	public static void main(String[] args) {
		ProcessHandlerFacade facadeService=ProcessHandlerFacade.getInstance();
		facadeService.addHandler("SquadLeaderHandler");
		facadeService.addHandler("TeacherHandler");
		facadeService.addHandler("SchoolMasterHandler");
		Random random=new Random();
		Student[] students=new Student[3];
		for (int i=0;i<students.length;i++){
			students[i]=new Student(random.nextInt(3)," i am student "+(i+1)+", i need a day off.");
		}
		for (Student s:students){
			facadeService.sendRequest(s);
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		facadeService.resetHandler();
		System.out.println();
		facadeService.addHandler("SchoolMasterHandler");// change the chain of handler
		facadeService.addHandler("TeacherHandler");
		facadeService.addHandler("SquadLeaderHandler");
		for (Student s:students){
			facadeService.sendRequest(s);
		}
	}

}
