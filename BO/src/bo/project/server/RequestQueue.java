package bo.project.server;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import bo.project.message.Message;

/**
 * FIFO queue of unprocessed requests
 * 
 * @author Marcin
 *
 */
public class RequestQueue {

	private Queue<Message> queue;
	private int maxQueueSize;
	private Lock lock;
	private Condition emptyQueue, fullQueue;

	/**
	 * Constructor
	 * 
	 * @param queueMaxSize		maximum size of queue
	 */
	public RequestQueue (int queueMaxSize) {
		this.maxQueueSize = queueMaxSize;
		this.queue = new LinkedList<Message>();
		this.lock = new ReentrantLock();
		this.emptyQueue = lock.newCondition();
		this.fullQueue = lock.newCondition();
	}

	/**
	 * Gets first request from queue.
	 * 
	 * @return	first request
	 */
	public Message pollRequest() {
		lock.lock();
		try {
			while (queue.isEmpty()) {
				emptyQueue.await();
			}

			fullQueue.signal();
			return queue.poll();
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
	 * 
	 * @param request
	 */
	public void addRequest(Message request) {
		lock.lock();
		try {
			while (queue.size() == this.maxQueueSize) {
				fullQueue.await();
			}

			queue.add(request);
			emptyQueue.signal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}




}