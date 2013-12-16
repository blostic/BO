package bo.project.server;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import bo.project.message.Message;

public class ResponseQueue {

        private List<Message> queue;
        private int maxQueueSize;
        private Lock lock;
        private Condition emptyQueue;
        
        public ResponseQueue (int maxQueueSize) {
                this.maxQueueSize = maxQueueSize;
                this.queue = new LinkedList<Message>();
                this.lock = new ReentrantLock();
                this.emptyQueue = lock.newCondition();
        }
        
        public Message pollRequest() {
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
        
        public void addRequest(Message request) {
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