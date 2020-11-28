;; Squares Squared
;; Difficulty:	Hard
;; Topics:	data-juggling


;; Create a function of two integer arguments: the start and end, respectively. You must create a vector of strings which renders a 45° rotated square of integers which are successive squares from the start point up to and including the end point. If a number comprises multiple digits, wrap them around the shape individually. If there are not enough digits to complete the shape, fill in the rest with asterisk characters. The direction of the drawing should be clockwise, starting from the center of the shape and working outwards, with the initial direction being down and to the right.

(fn [b e]
  (let [s (apply str (take-while #(<= % e) (iterate #(* % %) b)))
        [l] (drop-while #(< (* % %) (count s)) (range))
        xs (->> (map list (#(interleave % %) (rest (range))) 
                          (cycle [[1 1] [1 -1] [-1 -1] [-1 1]]))
                (mapcat (fn [[ll d]] (take ll (repeat d))))
                (reductions #(map + % %2) '(0 0))
                (take (* l l)))
        m (into {} (map vector xs (concat s (repeat "*"))))
        yxs (map #(map % xs) [first second])
        [[ymi xmi] [yma xma]] (map #(map (partial apply %) yxs)
                                [min max])]
    (vec (for [y (range ymi (inc yma))]
           (apply str (for [x (range xmi (inc xma))] 
                        (get m [y x] " ")))))))
