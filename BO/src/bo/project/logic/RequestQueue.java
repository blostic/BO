package bo.project.logic;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import bo.project.message.MessageMock;

public class RequestQueue {

	private Queue<MessageMock> queue;
	private int maxQueueSize;
	private Lock lock;
	private Condition emptyQueue, fullQueue;
	
	public RequestQueue (int queueMaxSize) {
		this.maxQueueSize = queueMaxSize;
		this.queue = new LinkedList<MessageMock>();
		this.lock = new ReentrantLock();
		this.emptyQueue = lock.newCondition();
		this.fullQueue = lock.newCondition();
	}
	
	public MessageMock pollRequest() {
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
	
	public void addRequest(MessageMock request) {
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
