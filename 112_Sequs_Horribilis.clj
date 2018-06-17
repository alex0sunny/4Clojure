; Create a function which takes an integer and a nested collection of integers as arguments. Analyze the elements of the input collection and return a sequence which maintains the nested structure, and which includes all elements starting from the head whose sum is less than or equal to the input integer.

(fn fs [n [f & r :as s]]
  (if f 
      (if (sequential? f) 
          (#(if (= f %) (cons f (fs (- n (apply + (flatten f))) r))
                (list %))
            (fs n f))
          (if (< n f) '() (cons f (fs (- n f) r))))
      '()))

#(or (:s (% %2 %3)) [])
(fn fs [n [f & r :as s]]
  (if f 
      (if (sequential? f) 
          (let [{nn :n f-new :s} (fs n f)]
            (if f-new
                {:n nn 
                 :s (cons f-new 
                          (if (= f f-new) (:s (fs nn r)))) } ))
          (let [nn (- n f)]
              { :n nn 
				        :s (if (>= nn 0) (cons f (:s (fs nn r)))) } ))))
