;; Difficulty:	Hard
;; Topics:	numbers


;; Given a pair of numbers, the start and end point,
;; find a path between the two using only three possible operations:

;;     double
;;     halve (odd numbers cannot be halved)
;;     add 2

;; Find the shortest path through the "maze". Because there are multiple
;; shortest paths, you must return the length of the shortest path, not
;; the path itself.

#(loop [s [%1] c 1]
    (if (some (partial = %2) s) c
      (recur (filter integer?
                (concat (map (partial * 2)   s) 
                        (map (partial * 1/2) s) 
                        (map (partial + 2)   s)))
             (inc c))))
