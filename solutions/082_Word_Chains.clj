; Problem 82 Word Chains
; Difficulty:	Hard
; Topics:	seqs


; A word chain consists of a set of words ordered so that each word differs by only one letter from the words 
; directly before and after it. The one letter difference can be either an insertion, a deletion, 
; or a substitution. Here is an example word chain:

; cat -> cot -> coat -> oat -> hat -> hot -> hog -> dog

; Write a function which takes a sequence of words, and returns true 
; if they can be arranged into one continous word chain, and false if they cannot.

(fn [wset] (let [chained? (fn chained? [s1 s2] (or (every? #(>= 1 (count %)) (vector s1 s2))
								(if (= (first s1) (first s2)) (chained? (rest s1) (rest s2)) 
        	               		(if (= (last s1) (last s2)) (chained? (butlast s1) (butlast s2)) false)) ))
                 filter-set (fn [s el] (set (filter #(not (= % el)) s)))
                 chained-seq? (fn chained-seq? 
                              ([s el] (if (empty? (rest s)) (chained? el (first s))
                                          (some #(and (chained? el %) (chained-seq? (filter-set s %) %)) s)))
                              ([s] (some identity (map #(chained-seq? (filter-set s %) %) s))))]
             (= true (chained-seq? wset))))
