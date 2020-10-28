; Difficulty:	Medium
;Topics:	seqs higher-order-functions


;take-while is great for filtering sequences, but it limited: you can only examine a single item of the sequence at a time. What if you need to keep track of some state as you go over the sequence?

;Write a function which accepts an integer n, a predicate p, and a sequence. It should return a lazy sequence of items in the list up to, but not including, the nth item that satisfies the predicate

#(butlast (% %2 %3 %4))
(fn fs [n p [f & r :as s]] 
		(if (pos? n) (cons f (fs (if (p f) (dec n) n) p r))))
