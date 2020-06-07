;Difficulty:	Medium
;Topics:	math
;A balanced number is one whose component digits have the same sum on the left and right halves of the number. Write a function which accepts an integer n, and returns true iff n is balanced.

(fn [n] 
  (let [s       (map #(- (int %) 48) (str n))
        c       (count s)
        [l r]   (map #(quot % 2) (list (inc c) c))
        ss      (take 2 (partition l r s))]
      (apply = (map #(apply + %) ss))))
