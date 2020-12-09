;; Sequs Horribilis
;; Difficulty:	Medium
;; Topics:	seqs


;; Create a function which takes an integer and a nested collection of 
;; integers as arguments. Analyze the elements of the input collection 
;; and return a sequence which maintains the nested structure, and which 
;; includes all elements starting from the head whose sum is less than
;; or equal to the input integer.

#(second (% %2 %3))
 (fn fs [n [f & r]]
   (if-not f [n []]
           (if (sequential? f) 
               (let [[nm fm] (fs n f)]
                 [nm (cons fm (if (= f fm) (second (fs nm r))))])
               (let [nm (- n f)]
                 [nm (if (neg? nm) [] (cons f (second (fs nm r))))]))))
