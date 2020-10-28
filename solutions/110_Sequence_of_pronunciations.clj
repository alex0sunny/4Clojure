;Write a function that returns a lazy sequence of "pronunciations" of a sequence of numbers.
;A pronunciation of each element in the sequence consists of the number of repeating
;identical numbers and the number itself. For example, [1 1] is pronounced as [2 1] ("two ones"),
;which in turn is pronounced as [1 2 1 1] ("one two, one one").

;Your function should accept an initial sequence of numbers, and return an infinite lazy sequence
;of pronunciations, each element being a pronunciation of the previous element.

(fn f [s] 
  (let [f-s  (fn f-s [[a :as s]]
               (let [c (count (take-while (partial = a) s))
                     r (drop-while (partial = a) s)]
                   (cons c (cons a (if (empty? r) r (f-s r))))))
        f-sq (fn f-sq [s] (lazy-seq (cons (f-s s) (f-sq (f-s s)))))]
      (f-sq s)))
