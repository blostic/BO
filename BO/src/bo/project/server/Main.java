package bo.project.server;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Main server class.
 * 
 * @author Marcin
 *
 */
public class Main {

	public static int SIMULATION_TIME_INTERVAL = 1;
	public static int SIMULATION_TOTAL_TIME = 100;
	public static int MAX_GENERATIONS = 100;
	public static boolean VERBOSE = true;

	private static MessageReceiver messageReceiver;
	private static RequestHandler requestHandler;

	/**
	 * Prints error message and closes program, if error is critical.
	 * 
	 * @param errorMessage		error message to print
	 * @param isCritical		critical error flag
	 */
	public static void error(String errorMessage, boolean isCritical) {
		System.out.println("[error] " + errorMessage);
		if (isCritical) {
			System.out.println("\n[critical error] Closing server...");
			System.exit(1);
		}
	}

	/**
	 * Prints system notification, if flag {@link #VERBOSE} is set.
	 * 
	 * @param infoMessage		message to print
	 */
	public static void info(String infoMessage) {
		if (VERBOSE) {
			System.out.println("[info] " + infoMessage);
		}
	}

	/**
	 * Prints server initialization info.
	 * 
	 * @param initMessage
	 * @param done
	 */
	public static void init(String initMessage, boolean done) {
		if (done) {
			System.out.println(" OK");
		} else {
			System.out.print("[init] " + initMessage);
		}
	}
	
	/**
	 * Main server method.
	 * 
	 * @param args		array with port number and maximum queue size
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		System.out.println("======= TRAFFIC SIMULATOR SERVER =======");
		
		init("Parsing arguments...",false);
		
		if (args.length != 2) {
			error("Invalid arguments.\n\tusage: <port number> <max queue size>", true);
		}

		int port = 0, maxQueue = 0;
		
		/* Parsing input */
		try {
			port = Integer.parseInt(args[0]);
			maxQueue = Integer.parseInt(args[1]); 
		} catch (NumberFormatException ex) {
			error("Invalid port number or queue size format.", true);
		}
		init("Parsing arguments...",true);	
		
		/* Starting handlers */
		init("Starting message receiver...",false);
		messageReceiver = new MessageReceiver(port,maxQueue);
		messageReceiver.start();
		init("Starting message receiver...",true);
		
		
		init("Starting request handler...", false);
		requestHandler = new RequestHandler(messageReceiver);
		requestHandler.start();
		init("Starting request handler...", true);
		
		
		final Scanner scanner = new Scanner(System.in);

		/* Used to closing sockets and stopping handler threads */
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				info("Closing server...");
				scanner.close();
				messageReceiver.close();
				requestHandler.close();
				messageReceiver.closeConnection();
				info("Bye.");
			}
		});
		
		System.out.println("========================================");
		info("Server is running on port " + port + '.');
		System.out.println("To list available commands, type \"help\".");

		String command;

		boolean isAlive = true;

		/* Command loop */
		while (isAlive) {
			System.out.print(">>> ");
			try {
				command = scanner.nextLine();
				parseCommand(command);
			} catch (NoSuchElementException e) {
				isAlive = false;
			}
		}


	}

	/* Parses user command */
	private static void parseCommand(String command) {
		if (command.equals("help")) {
			System.out.println("Available commands:");
			System.out.println("\tparams -- show current simulation parameters");
			System.out.println("\ttimeint <n> -- set time simulated in single iteration to n");
			System.out.println("\ttimetot <n> -- set total simulated time to n");
			System.out.println("\tmaxgen <n> -- set maximum number of cuckoos generations to n");
			System.out.println("\tqueues -- show size of request and response queue");
			System.out.println("\tverb -- enable verbose mode");
			System.out.println("\tno verb -- disable verbose mode");
			System.out.println("\texit -- close server");
		} else if (command.equals("params")) {
			System.out.println("Simulation parameters:");
			System.out.println("\tsimulation time interval: " + SIMULATION_TIME_INTERVAL);
			System.out.println("\tsimulation total time: " + SIMULATION_TOTAL_TIME);
			System.out.println("\tmaximum number of generations: " + MAX_GENERATIONS);
		} else if (command.startsWith("timeint") || command.startsWith("timetot") || command.startsWith("maxgen")) {
			setParameter(command);
		} else if (command.equals("queues")) {
			System.out.println("\trequest queue size: " + messageReceiver.getRequestQueueSize());
			System.out.println("\tresponse queue size: " + messageReceiver.getResponseQueueSize());
		} else if (command.equals("verb")) {
			VERBOSE = true;
			System.out.println("Verbose mode enabled.");
		} else if (command.equals("no verb")) {
			VERBOSE = false;
			System.out.println("Verbose mode disabled.");
		} else if (command.equals("exit")) {
			/* Cleaning done in shutdown hook */
			System.exit(0);
		} else {
			System.out.println("Unrecognized command; type \"help\" to list commands.");
		}
	}

	/* Gets integer value from command */
	private static void setParameter(String command) {
		try {
			int value = Integer.parseInt(command.split(" ")[1]);
			if (value < 0) throw new NumberFormatException();
			if (command.startsWith("timeint")) {
				SIMULATION_TIME_INTERVAL = value;
			} else if (command.startsWith("timetot")) {
				SIMULATION_TOTAL_TIME = value;
			} else if (command.startsWith("maxgen")) {
				MAX_GENERATIONS = value;
			}
			System.out.println("Parameter changed.");
		} catch (NumberFormatException ex) {
			System.out.println("Invalid command value - must be a positive integer.");
		}
	}

}
