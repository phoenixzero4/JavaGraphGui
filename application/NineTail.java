package application;


import java.util.List;
import java.util.Scanner;

public class NineTail {
	public static void main(String[] args) {
		System.out.println("Enter initial state of H and T: ");
		Scanner in = new Scanner(System.in);

		String s = in.nextLine();
		char[] init = s.toCharArray();
		NineTails model = new NineTails();
		List<Integer> path = model.getShortestPath(NineTails.getIndex(init));

		System.out.println("Steps to flip the coins are: ");

		for (int i = 0; i < path.size(); i++) {
			NineTails.printNode(NineTails.getNode(path.get(i).intValue()));
		}
	}
}
