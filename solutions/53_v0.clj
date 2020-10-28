; Problem 53 Longest Increasing Sub-Seq
(fn [s] 
  (let [res (apply max-key count (reverse (reduce (
    fn [r e] (let [ss (last r)]
		(if (> e (last ss)) 
			(concat (drop-last r) (list (concat ss (list e))))
			(concat r (list (list e)))))
    ) (list (list (first s))) (rest s))))] (if (= 1 (count res)) [] res)))
