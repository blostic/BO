package bo.project.server;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import bo.project.message.Message;


/** 
 * Queue containing results of simulations
 * 
 * @author Marcin
 *
 */
public class ResponseQueue {

	private List<Message> queue;
	private int maxQueueSize;
	private Lock lock;
	private Condition emptyQueue;

	/** 
	 * Constructor
	 * 
	 * @param maxQueueSize maximum size of queue
	 */
	public ResponseQueue (int maxQueueSize) {
		this.maxQueueSize = maxQueueSize;
		this.queue = new LinkedList<Message>();
		this.lock = new ReentrantLock();
		this.emptyQueue = lock.newCondition();
	}

	/**
	 * Gets first request from queue.
	 * 
	 * @return	first request
	 */
	public Message pollResponse() {
		lock.lock();
		try {
			while (queue.isEmpty()) {
				emptyQueue.await();
			}

			return queue.remove(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			lock.unlock();
		}
	}
	
	public int getSize() {
		return this.queue.size();
	}

	/**
	 * Adds request to queue on last position
	 * If maximum queue size was reached, the first element is removed.
	 * 
	 * @param response
	 */
	public void addResponse(Message response) {
		lock.lock();
		try {
			if (queue.size() == maxQueueSize) {
				queue.remove(0);
			}

			queue.add(response);
			emptyQueue.signal();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * Searches for element in queue with specified ID
	 * 
	 * @param id
	 * @return 	if no solution is found, returns null
	 */
	public Message getById(int id) {
		lock.lock();
		try {
			for (Message msg : queue) {
				if (msg.getId() == id) {
					queue.remove(msg);
					return msg;
				}
			}
			return null;

		} finally {
			lock.unlock();
		}
	}


}