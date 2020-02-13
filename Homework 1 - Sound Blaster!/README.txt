1.	I created a test class and tried different edge cases, such as, removing when empty, peeking when empty, etc.
2. 	The size increases like this: 1 > 2 > 4 > 8 > 16 > 32 > 64 ... which is 2^n. 
   	So, if we set the equation like this, 1,000,000 = 2^n, one can find that the resizing will occur 19.93 or 20 times.
   	For 1,000,000,000 it is 29.89 or 30 times and 40 times for trillion.
3. 	PUSH
	Enqueue the data into Queue
	
	POP
	for(size - 1 times){
		dequeue from Queue
		enqueue into Queue
	}
	return dequeue from Queue
4.	I think that the queue implementation has a O(N), since the pop requires one loop of the entire data.
	N is much better than 2^n so I will choose the Queue.
5.	My array implementation will resize is 3/4 is empty.
6.	It was interesting to see the numbers make sound.
7. 	The scent of bitter almonds always reminded him of the fate of unrequited love.
			