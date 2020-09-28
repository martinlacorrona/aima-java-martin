package aima.gui.demo.search;

import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.environment.eightpuzzle.BidirectionalEightPuzzleProblem;
import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import aima.core.environment.eightpuzzle.EightPuzzleFunctions;
import aima.core.search.agent.SearchAgent;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.problem.Problem;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.informed.AStarSearch;

/**
 * @author Ravi Mohan
 * @author Ruediger Lunde
 * 
 */

public class EightPuzzleDemo {
//	private static EightPuzzleBoard boardWithThreeMoveSolution =
//			new EightPuzzleBoard(new int[] { 1, 2, 5, 3, 4, 0, 6, 7, 8 });

//	private static EightPuzzleBoard random1 =
//			new EightPuzzleBoard(new int[] { 1, 4, 2, 7, 5, 8, 3, 0, 6 });

//	private static EightPuzzleBoard extreme =
//			new EightPuzzleBoard(new int[] { 0, 8, 7, 6, 5, 4, 3, 2, 1 });

	private static EightPuzzleBoard initial =
			new EightPuzzleBoard(new int[] 
	
	// 25 movimientos
	{ 6, 7, 4, 0, 5, 1, 3, 2, 8 }
	// {6, 0, 7, 5, 4, 1, 3, 8, 2}
	// {3, 4, 8, 5, 7, 1, 6, 0, 2}
	// {4, 5, 3, 7, 6, 2, 8, 0, 1}
	// {2, 7, 8, 5, 4, 0, 3, 1, 6}

	 // 30 movimientos
	 //{5, 6, 7, 2, 8, 4, 0, 3, 1}
	// {5, 6, 7, 4, 0, 8, 3, 2, 1}
	// {5, 4, 7, 6, 0, 3, 8, 2, 1}
	// {3, 8, 7, 4, 0, 6, 5, 2, 1}
	// {5, 6, 3, 4, 0, 2, 7, 8, 1}
			
	);

	public static void main(String[] args) {
		System.out.println("Initial State:\n" + initial);
//		eightPuzzleDLSDemo();
//		eightPuzzleIDLSDemo();
//		eightPuzzleGreedyBestFirstDemo();
//		eightPuzzleGreedyBestFirstManhattanDemo();
//		eightPuzzleAStarDemo();
//		eightPuzzleAStarManhattanDemo();
//		eightPuzzleSimulatedAnnealingDemo();
		eightPuzzleAStarNullDemo();								//h = 0
		eightPuzzleAStarWeigthedMisplacedDemo();				//h1
		eightPuzzleAStarWeigthedManhattanDemo();				//h2
		eightPuzzleAStarWeigthedNonConsistentHeuristicDemo();	//h3
		eightPuzzleAStarEpsilonWeigthedManhattanDemo();		// (1 + epsilon)*h2
	}

	private static void eightPuzzleAStarNullDemo() {
		System.out.println("\nEightPuzzleAStarNullDemo AStart Search (H0)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(initial);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions::nullHeuristic);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void eightPuzzleAStarWeigthedMisplacedDemo() {
		System.out.println("\nEightPuzzleAStarWeigthedMisplacedDemo AStart Search (H1)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(initial);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions::getWeigthedNumberOfMisplacedTiles);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void eightPuzzleAStarWeigthedManhattanDemo() {
		System.out.println("\nEightPuzzleAStarWeigthedMisplacedDemo AStart Search (H2)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(initial);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions::getWeigthedManhattanDistance);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void eightPuzzleAStarWeigthedNonConsistentHeuristicDemo() {
		System.out.println("\nEightPuzzleAStarWeigthedNonConsistentHeuristicDemo AStart Search (H3)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(initial);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions::nonWeigthedConsistentHeuristic);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void eightPuzzleAStarEpsilonWeigthedManhattanDemo() {
		System.out.println("\nEightPuzzleAStarEpsilonWeigthedManhattanDemo AStart Search ((1 + epsilon)*H2)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(initial);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions::getWeigthedManhattanDistance);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	private static void eightPuzzleDLSDemo() {
//		System.out.println("\nEightPuzzleDemo recursive DLS (9)");
//		try {
//			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(boardWithThreeMoveSolution);
//			SearchForActions<EightPuzzleBoard, Action> search = new DepthLimitedSearch<>(9);
//			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
//			printActions(agent.getActions());
//			printInstrumentation(agent.getInstrumentation());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static void eightPuzzleIDLSDemo() {
//		System.out.println("\nEightPuzzleDemo Iterative DLS");
//		try {
//			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
//			SearchForActions<EightPuzzleBoard, Action> search = new IterativeDeepeningSearch<>();
//			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
//			printActions(agent.getActions());
//			printInstrumentation(agent.getInstrumentation());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static void eightPuzzleGreedyBestFirstDemo() {
//		System.out.println("\nEightPuzzleDemo Greedy Best First Search (MisplacedTileHeursitic)");
//		try {
//			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(boardWithThreeMoveSolution);
//			SearchForActions<EightPuzzleBoard, Action> search = new GreedyBestFirstSearch<>
//					(new GraphSearch<>(), EightPuzzleFunctions::getNumberOfMisplacedTiles);
//			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
//			printActions(agent.getActions());
//			printInstrumentation(agent.getInstrumentation());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static void eightPuzzleGreedyBestFirstManhattanDemo() {
//		System.out.println("\nEightPuzzleDemo Greedy Best First Search (ManhattanHeursitic)");
//		try {
//			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(boardWithThreeMoveSolution);
//			SearchForActions<EightPuzzleBoard, Action> search = new GreedyBestFirstSearch<>
//					(new GraphSearch<>(), EightPuzzleFunctions::getManhattanDistance);
//			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
//			printActions(agent.getActions());
//			printInstrumentation(agent.getInstrumentation());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	private static void eightPuzzleAStarDemo() {
//		System.out.println("\nEightPuzzleDemo AStar Search (MisplacedTileHeursitic)");
//		try {
//			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
//			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>
//					(new GraphSearch<>(), EightPuzzleFunctions::getNumberOfMisplacedTiles);
//			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
//			printActions(agent.getActions());
//			printInstrumentation(agent.getInstrumentation());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static void eightPuzzleSimulatedAnnealingDemo() {
//		System.out.println("\nEightPuzzleDemo Simulated Annealing Search");
//		try {
//			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
//			SimulatedAnnealingSearch<EightPuzzleBoard, Action> search = new SimulatedAnnealingSearch<>
//					(EightPuzzleFunctions::getManhattanDistance);
//			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
//			printActions(agent.getActions());
//			System.out.println("Final State:\n" + search.getLastState());
//			printInstrumentation(agent.getInstrumentation());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static void eightPuzzleAStarManhattanDemo() {
//		System.out.println("\nEightPuzzleDemo AStar Search (ManhattanHeursitic)");
//		try {
//			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
//			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>
//					(new GraphSearch<>(), EightPuzzleFunctions::getManhattanDistance);
//			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
//			printActions(agent.getActions());
//			printInstrumentation(agent.getInstrumentation());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	private static void printInstrumentation(Properties properties) {
		properties.keySet().stream().map(key -> key + "=" + properties.get(key)).forEach(System.out::println);
	}

	private static void printActions(List<Action> actions) {
		actions.forEach(System.out::println);
	}
}