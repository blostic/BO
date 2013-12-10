package bo.project.logic;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import bo.project.message.MessageMock;

public class ResponseQueue {

	private List<MessageMock> queue;
	private int maxQueueSize;
	private Lock lock;
	private Condition emptyQueue;
	
	public ResponseQueue (int maxQueueSize) {
		this.maxQueueSize = maxQueueSize;
		this.queue = new LinkedList<MessageMock>();
		this.lock = new ReentrantLock();
		this.emptyQueue = lock.newCondition();
	}
	
	public MessageMock pollRequest() {
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
	
	public void addRequest(MessageMock request) {
		lock.lock();
		try {
			if (queue.size() == maxQueueSize) {
				queue.remove(0);
			}
			
			queue.add(request);
			emptyQueue.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public MessageMock getById(int id) {
		lock.lock();
		try {
			for (MessageMock msg : queue) {
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
